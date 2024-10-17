package br.com.fiap.javabet.prediction;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PredictionRepository extends JpaRepository<Prediction, Long> {

    List<Prediction> findByGameId(Long id);

    Optional<Prediction> findByGameIdAndUserId(Long gameId, Long id);
}
