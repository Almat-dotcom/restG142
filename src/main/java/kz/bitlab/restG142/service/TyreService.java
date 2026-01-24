package kz.bitlab.restG142.service;

import kz.bitlab.restG142.dto.TyreRequestDTO;
import kz.bitlab.restG142.dto.TyreResponseDTO;
import kz.bitlab.restG142.dto.TyreResponseShortDTO;
import kz.bitlab.restG142.model.Tyres;

import java.util.List;

public interface TyreService {
    TyreResponseShortDTO getById(Long id);
    List<TyreResponseShortDTO> getAll();
    TyreResponseDTO add(TyreRequestDTO dto);
    Void delete(Long id);
    TyreResponseDTO update(TyreRequestDTO dto, Long id);
}
