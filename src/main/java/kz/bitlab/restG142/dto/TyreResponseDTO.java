package kz.bitlab.restG142.dto;

import lombok.*;

@Getter
@Setter
public class TyreResponseDTO {
    public Long id;

    private String name;

    private String profile;

    private int price;

    private String manufacturer;
}
