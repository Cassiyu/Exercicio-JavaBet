package br.com.fiap.javabet.prediction;

import br.com.fiap.javabet.game.Game;
import br.com.fiap.javabet.user.User;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "predictions")
public class Prediction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Game game;

    private int predictedHomeTeamScore;
    private int predictedAwayTeamScore;


}
