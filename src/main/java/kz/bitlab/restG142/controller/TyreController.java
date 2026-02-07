package kz.bitlab.restG142.controller;

import kz.bitlab.restG142.dto.TyreRequestDTO;
import kz.bitlab.restG142.dto.TyreResponseDTO;
import kz.bitlab.restG142.dto.TyreResponseShortDTO;
import kz.bitlab.restG142.model.Tyres;
import kz.bitlab.restG142.service.TyreService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TyreController {
    private final TyreService tyreService;

    @GetMapping("/{id}")
    @SneakyThrows
    public ResponseEntity<TyreResponseShortDTO> get(@PathVariable(name = "id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(tyreService.getById(id));
    }

    @GetMapping("/")
    public ResponseEntity<List<TyreResponseShortDTO>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(tyreService.getAll());
    }

    @PostMapping("/")
    public ResponseEntity<TyreResponseDTO> add(@RequestBody TyreRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(tyreService.add(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable(name = "id") Long id) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(tyreService.delete(id));
    }

    @PutMapping("/")
    public ResponseEntity<TyreResponseDTO> update(@RequestBody TyreRequestDTO dto,
                                        @RequestParam(name = "id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(tyreService.update(dto, id));
    }
}
