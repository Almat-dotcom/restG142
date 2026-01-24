package kz.bitlab.restG142.service.impl;

import kz.bitlab.restG142.dto.TyreRequestDTO;
import kz.bitlab.restG142.dto.TyreResponseDTO;
import kz.bitlab.restG142.dto.TyreResponseShortDTO;
import kz.bitlab.restG142.mapper.TyreMapper;
import kz.bitlab.restG142.model.Tyres;
import kz.bitlab.restG142.repository.TyresRepository;
import kz.bitlab.restG142.service.TyreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TyreServiceImpl implements TyreService {
    private final TyresRepository tyresRepository;
    private final TyreMapper tyreMapper;

    @Override
    public TyreResponseShortDTO getById(Long id) {
        Tyres tyre = tyresRepository.findById(id).orElseThrow(() -> new RuntimeException("Tyre not found"));
        return tyreMapper.toShortDTO(tyre);
    }

    @Override
    public List<TyreResponseShortDTO> getAll() {
        List<Tyres> tyresList = tyresRepository.findAll();
        return tyreMapper.toShortDTO(tyresList);
    }

    @Override
    public TyreResponseDTO add(TyreRequestDTO dto) {
        Tyres tyre = tyreMapper.toEntity(dto);
        Tyres saved = tyresRepository.save(tyre);
        return tyreMapper.toDTO(saved);
//        return tyreMapper.toDTO(tyresRepository.save(tyreMapper.toEntity(dto)));
    }

    @Override
    public Void delete(Long id) {
        tyresRepository.deleteById(id);
        return null;
    }

    @Override
    public TyreResponseDTO update(TyreRequestDTO dto, Long id) {
        Tyres tyre = tyresRepository.findById(id).orElseThrow(() -> new RuntimeException("Tyre not found"));
        tyreMapper.map(dto, tyre);
        Tyres saved = tyresRepository.save(tyre);
        return tyreMapper.toDTO(saved);
    }
}
