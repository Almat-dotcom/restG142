package kz.bitlab.restG142.service.impl;

import kz.bitlab.restG142.dto.*;
import kz.bitlab.restG142.mapper.TyreMapper;
import kz.bitlab.restG142.model.Season;
import kz.bitlab.restG142.model.Tyres;
import kz.bitlab.restG142.repository.TyresRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TyreServiceImplTest {

    @Mock
    private TyresRepository tyresRepository;

    @Mock
    private TyreMapper tyreMapper;

    @InjectMocks
    private TyreServiceImpl tyreService;

    @Test
    void getById_success_returnsShortDto() {
        Long id = 1L;

        Season season = Season.builder().id(2L).name("Winter").build();
        Tyres entity = Tyres.builder()
                .id(id)
                .name("Michelin X-Ice")
                .profile("225/45 R17")
                .price(50000)
                .manufacturer("Michelin")
                .season(season)
                .build();

        SeasonDTO seasonDTO = new SeasonDTO();
        seasonDTO.setId(2L);
        seasonDTO.setName("Winter");

        TyreResponseShortDTO shortDTO = new TyreResponseShortDTO();
        shortDTO.id = id;
        shortDTO.setName("Michelin X-Ice");
        shortDTO.setProfile("225/45 R17");
        shortDTO.setSeason(seasonDTO);

        when(tyresRepository.findById(id)).thenReturn(Optional.of(entity));
        when(tyreMapper.toShortDTO(entity)).thenReturn(shortDTO);

        TyreResponseShortDTO result = tyreService.getById(id);

        assertNotNull(result);
        assertEquals(id, result.id);
        assertEquals("Michelin X-Ice", result.getName());
        assertEquals("225/45 R17", result.getProfile());
        assertNotNull(result.getSeason());
        assertEquals(2L, result.getSeason().getId());
        assertEquals("Winter", result.getSeason().getName());

        verify(tyresRepository).findById(id);
        verify(tyreMapper).toShortDTO(entity);
        verifyNoMoreInteractions(tyresRepository, tyreMapper);
    }

    @Test
    void getById_notFound_throws() {
        Long id = 404L;

        when(tyresRepository.findById(id)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () -> tyreService.getById(id));
        assertEquals("Tyre not found", ex.getMessage());

        verify(tyresRepository).findById(id);
        verifyNoInteractions(tyreMapper);
    }

    @Test
    void getAll_success_returnsShortDtos() {
        List<Tyres> entities = List.of(
                Tyres.builder().id(1L).name("A").profile("P1").build(),
                Tyres.builder().id(2L).name("B").profile("P2").build()
        );

        TyreResponseShortDTO d1 = new TyreResponseShortDTO();
        d1.id = 1L;
        d1.setName("A");
        d1.setProfile("P1");

        TyreResponseShortDTO d2 = new TyreResponseShortDTO();
        d2.id = 2L;
        d2.setName("B");
        d2.setProfile("P2");

        List<TyreResponseShortDTO> mapped = List.of(d1, d2);

        when(tyresRepository.findAll()).thenReturn(entities);
        when(tyreMapper.toShortDTO(entities)).thenReturn(mapped);

        List<TyreResponseShortDTO> result = tyreService.getAll();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(1L, result.get(0).id);
        assertEquals("A", result.get(0).getName());

        verify(tyresRepository).findAll();
        verify(tyreMapper).toShortDTO(entities);
        verifyNoMoreInteractions(tyresRepository, tyreMapper);
    }

    @Test
    void add_success_savesEntity_andReturnsDto() {
        TyreRequestDTO req = new TyreRequestDTO();
        req.setFullName("Bridgestone Blizzak");
        req.setProfile("205/55 R16");
        req.setPrice(45000);
        req.setManufacturer("Bridgestone");
        req.setPassword("secret");
        req.setSeasonId(3L);

        Season season = Season.builder().id(3L).name("All-season").build();
        Tyres entityFromMapper = Tyres.builder()
                .name("Bridgestone Blizzak")
                .profile("205/55 R16")
                .price(45000)
                .manufacturer("Bridgestone")
                .season(season)
                .build();

        Tyres savedEntity = Tyres.builder()
                .id(10L)
                .name("Bridgestone Blizzak")
                .profile("205/55 R16")
                .price(45000)
                .manufacturer("Bridgestone")
                .season(season)
                .build();

        TyreResponseDTO resp = new TyreResponseDTO();
        resp.id = 10L;
        resp.setName("Bridgestone Blizzak");
        resp.setProfile("205/55 R16");
        resp.setPrice(45000);
        resp.setManufacturer("Bridgestone");

        when(tyreMapper.toEntity(req)).thenReturn(entityFromMapper);
        when(tyresRepository.save(any(Tyres.class))).thenReturn(savedEntity);
        when(tyreMapper.toDTO(savedEntity)).thenReturn(resp);

        TyreResponseDTO result = tyreService.add(req);

        assertNotNull(result);
        assertEquals(10L, result.id);
        assertEquals("Bridgestone Blizzak", result.getName());
        assertEquals("205/55 R16", result.getProfile());
        assertEquals(45000, result.getPrice());
        assertEquals("Bridgestone", result.getManufacturer());

        ArgumentCaptor<Tyres> captor = ArgumentCaptor.forClass(Tyres.class);
        verify(tyresRepository).save(captor.capture());
        Tyres toSave = captor.getValue();

        assertEquals("Bridgestone Blizzak", toSave.getName());
        assertEquals("205/55 R16", toSave.getProfile());
        assertEquals(45000, toSave.getPrice());
        assertEquals("Bridgestone", toSave.getManufacturer());
        assertNotNull(toSave.getSeason());
        assertEquals(3L, toSave.getSeason().getId());

        verify(tyreMapper).toEntity(req);
        verify(tyreMapper).toDTO(savedEntity);
        verifyNoMoreInteractions(tyresRepository, tyreMapper);
    }

    @Test
    void delete_success_callsRepositoryDelete() {
        Long id = 7L;

        doNothing().when(tyresRepository).deleteById(id);
    
        Void result = tyreService.delete(id);

        assertNull(result);
        verify(tyresRepository).deleteById(id);
        verifyNoInteractions(tyreMapper);
        verifyNoMoreInteractions(tyresRepository);
    }

    @Test
    void update_success_mapsOntoExisting_saves_andReturnsDto() {
        Long id = 5L;

        TyreRequestDTO req = new TyreRequestDTO();
        req.setFullName("Continental TS 870");
        req.setProfile("195/65 R15");
        req.setPrice(42000);
        req.setManufacturer("Continental");
        req.setSeasonId(2L);

        Tyres existing = Tyres.builder()
                .id(id)
                .name("Old Name")
                .profile("Old")
                .price(1)
                .manufacturer("OldM")
                .season(Season.builder().id(1L).name("Summer").build())
                .build();

        Tyres saved = Tyres.builder()
                .id(id)
                .name("Continental TS 870")
                .profile("195/65 R15")
                .price(42000)
                .manufacturer("Continental")
                .season(Season.builder().id(2L).name("Winter").build())
                .build();

        TyreResponseDTO resp = new TyreResponseDTO();
        resp.id = id;
        resp.setName("Continental TS 870");
        resp.setProfile("195/65 R15");
        resp.setPrice(42000);
        resp.setManufacturer("Continental");

        when(tyresRepository.findById(id)).thenReturn(Optional.of(existing));
        when(tyresRepository.save(existing)).thenReturn(saved);
        when(tyreMapper.toDTO(saved)).thenReturn(resp);

        TyreResponseDTO result = tyreService.update(req, id);

        assertNotNull(result);
        assertEquals(id, result.id);
        assertEquals("Continental TS 870", result.getName());

        verify(tyresRepository).findById(id);
        verify(tyreMapper).map(req, existing);
        verify(tyresRepository).save(existing);
        verify(tyreMapper).toDTO(saved);
        verifyNoMoreInteractions(tyresRepository, tyreMapper);
    }

    @Test
    void update_notFound_throws() {
        Long id = 999L;
        TyreRequestDTO req = new TyreRequestDTO();

        when(tyresRepository.findById(id)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () -> tyreService.update(req, id));
        assertEquals("Tyre not found", ex.getMessage());

        verify(tyresRepository).findById(id);
        verifyNoInteractions(tyreMapper);
        verify(tyresRepository, never()).save(any());
        verifyNoMoreInteractions(tyresRepository);
    }
}
