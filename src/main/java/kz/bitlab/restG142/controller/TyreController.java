package kz.bitlab.restG142.controller;

import kz.bitlab.restG142.model.Tyres;
import kz.bitlab.restG142.service.TyreService;
import lombok.RequiredArgsConstructor;
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
    public ResponseEntity<Tyres> get(@PathVariable(name = "id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(tyreService.getById(id));
    }

    @GetMapping("/")
    public ResponseEntity<List<Tyres>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(tyreService.getAll());
    }

    @PostMapping("/")
    public ResponseEntity<Tyres> add(@RequestBody Tyres tyre) {
        return ResponseEntity.status(HttpStatus.CREATED).body(tyreService.add(tyre));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable(name = "id") Long id) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(tyreService.delete(id));
    }

    @PutMapping("/")
    public ResponseEntity<Tyres> update(@RequestBody Tyres tyre) {
        return ResponseEntity.status(HttpStatus.OK).body(tyreService.update(tyre));
    }
}
