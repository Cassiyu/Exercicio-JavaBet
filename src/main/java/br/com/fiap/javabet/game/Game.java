package br.com.fiap.javabet.game;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
@Table(name = "games")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String homeTeam;
    private String awayTeam;

    private int homeTeamScore;
    private int awayTeamScore;


}
