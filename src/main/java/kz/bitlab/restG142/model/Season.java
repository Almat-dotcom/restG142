package kz.bitlab.restG142.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "t_season")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Season {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "name")
    private String name;
}
