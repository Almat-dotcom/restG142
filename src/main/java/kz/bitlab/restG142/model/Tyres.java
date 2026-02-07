package kz.bitlab.restG142.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "t_tyres")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Tyres {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "profile")
    private String profile;

    @Column(name = "price")
    private int price;

    @Column(name = "manufacturer")
    private String manufacturer;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "season_id")
    private Season season;
}
