package com.andrey.dotamanager.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "matches")
public class Match {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "team1_id")
    private Team team1;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "team2_id")
    private Team team2;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "winner_id")
    private Team winner;

}
