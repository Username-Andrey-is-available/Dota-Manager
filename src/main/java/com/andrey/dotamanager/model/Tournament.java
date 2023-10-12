package com.andrey.dotamanager.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Tournament {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tournamentName;
    private String country;
    private Date startDate;
    private Date endDate;
    private int numberOfTeams;
    private double prizePool;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "team_tournament_ids",
            joinColumns = @JoinColumn(name = "tournament_id"),
            inverseJoinColumns = @JoinColumn(name = "team_id")
    )
    @Column(name = "team_ids")
    private Set<Team> teams = new HashSet<>();

}
