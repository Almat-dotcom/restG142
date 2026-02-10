package kz.bitlab.restG142.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TyreResponseShortDTO {
    public Long id;

    private String name;

    private String profile;

    private SeasonDTO season;
}
