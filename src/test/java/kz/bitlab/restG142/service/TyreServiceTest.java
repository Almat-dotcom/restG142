package kz.bitlab.restG142.service;


import kz.bitlab.restG142.dto.TyreRequestDTO;
import kz.bitlab.restG142.dto.TyreResponseDTO;
import kz.bitlab.restG142.dto.TyreResponseShortDTO;
import kz.bitlab.restG142.mapper.TyreMapper;
import kz.bitlab.restG142.model.Season;
import kz.bitlab.restG142.model.Tyres;
import kz.bitlab.restG142.repository.TyresRepository;
import kz.bitlab.restG142.service.impl.TyreServiceImpl;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TyreServiceTest {

    @InjectMocks
    private TyreServiceImpl tyreService;

    @Mock
    private TyresRepository tyresRepository;

    @Mock
    private TyreMapper tyreMapper;

    //макет

    @Test
    void exampleMethod() {
        int res = tyreService.example();
        assertEquals(2, res);
    }

    @Test
    void addTest() {
        TyreRequestDTO dto = TyreRequestDTO.builder()
                .fullName("A")
                .price(1)
                .manufacturer("A")
                .seasonId(1L)
                .build();

        Tyres toMap = Tyres.builder()
                .name("A")
                .price(1)
                .manufacturer("A")
                .season(
                        Season.builder()
                                .id(1L)
                                .name("Winter")
                                .build()
                )
                .build();

        Tyres saved = Tyres.builder()
                .id(1L)
                .name("A")
                .price(1)
                .manufacturer("A")
                .season(
                        Season.builder()
                                .id(1L)
                                .name("Winter")
                                .build()
                )
                .build();

        TyreResponseDTO response = new TyreResponseDTO(1L, "A", null, 1, "A");

        when(tyreMapper.toEntity(dto)).thenReturn(toMap);
        when(tyresRepository.save(toMap)).thenReturn(saved);
        when(tyreMapper.toDTO(saved)).thenReturn(response);

        TyreResponseDTO result = tyreService.add(dto);

        assertEquals(dto.getFullName(), result.getName());
        assertEquals(dto.getPrice(), result.getPrice());
        verify(tyresRepository, times(1)).save(toMap);
    }

    @Test
    void getAllTest() {
        List<Tyres> tyres = new ArrayList<>();
        tyres.add(new Tyres(1L, "A", "a", 1, "a", null));
        tyres.add(new Tyres(2L, "B", "a", 1, "a", null));
        tyres.add(new Tyres(3L, "C", "a", 1, "a", null));

        List<TyreResponseShortDTO> dtos = new ArrayList<>();
        dtos.add(new TyreResponseShortDTO(1L, "A", "a", null));
        dtos.add(new TyreResponseShortDTO(2L, "B", "a", null));
        dtos.add(new TyreResponseShortDTO(3L, "C", "a", null));

        when(tyresRepository.findAll()).thenReturn(tyres);
        when(tyreMapper.toShortDTO(tyres)).thenReturn(dtos);

        List<TyreResponseShortDTO> result = tyreService.getAll();

        assertNotNull(result);
        assertNotEquals(0, result.size());
    }

    @Test
    void deleteTest() {
        doNothing().when(tyresRepository).deleteById(1L);

        tyreService.delete(1L);

        verify(tyresRepository, times(1)).deleteById(1L);
    }

    @Test
    void getByIdSuccessTest() {
        Tyres tyre = new Tyres(1L, "A", "a", 1, "a", null);
        TyreResponseShortDTO dto = new TyreResponseShortDTO(1L, "B", "a", null);


        when(tyresRepository.findById(1L)).thenReturn(Optional.of(tyre));
        when(tyreMapper.toShortDTO(tyre)).thenReturn(dto);

        tyreService.getById(1L);

        assertEquals(tyre.getId(), dto.getId());
    }

    @Test
    void getByIdBadTest() {
        Tyres tyre = new Tyres(1L, "A", "a", 1, "a", null);
        TyreResponseShortDTO dto = new TyreResponseShortDTO(1L, "B", "a", null);


        when(tyresRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> tyreService.getById(1L));
    }
}
