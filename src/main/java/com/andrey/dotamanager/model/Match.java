package com.andrey.dotamanager.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "matches")
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "team1_id")
    private Team team1;

    @ManyToOne
    @JoinColumn(name = "team2_id")
    private Team team2;

    @ManyToOne
    @JoinColumn(name = "winner_id")
    private Team winner;


    @Override
    public String toString() {
        return "Match{" +
                "id=" + id +
                ", team1=" + team1 +
                ", team2=" + team2 +
                ", winner=" + winner +
                // Добавьте строку для других полей
                '}';
    }
}
