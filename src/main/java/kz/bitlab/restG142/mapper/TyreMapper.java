package kz.bitlab.restG142.mapper;

import kz.bitlab.restG142.dto.TyreRequestDTO;
import kz.bitlab.restG142.dto.TyreResponseDTO;
import kz.bitlab.restG142.dto.TyreResponseShortDTO;
import kz.bitlab.restG142.model.Tyres;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TyreMapper {
    @Mapping(source = "fullName", target = "name")
    Tyres toEntity(TyreRequestDTO dto);

    TyreResponseDTO toDTO(Tyres entity);

    TyreResponseShortDTO toShortDTO(Tyres entity);

    List<TyreResponseShortDTO> toShortDTO(List<Tyres> entity);

    @Mapping(source = "fullName", target = "name")
    void map(TyreRequestDTO dto, @MappingTarget Tyres tyre);
}
