package kz.bitlab.restG142.mapper;

import kz.bitlab.restG142.model.Season;
import kz.bitlab.restG142.repository.SeasonRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MapperHelper {
    private final SeasonRepository seasonRepository;

    @Named("toSeason")
    public Season toSeason(Long id) {
        return seasonRepository.findById(id).orElseThrow(()->new RuntimeException("Season not found"));
    }
}
