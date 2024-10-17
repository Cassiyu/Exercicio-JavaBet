package br.com.fiap.javabet.prediction;

import br.com.fiap.javabet.game.GameService;
import br.com.fiap.javabet.user.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class PredictionService {
    private final PredictionRepository predictionRepository;
    private final GameService gameService;

    public PredictionService(PredictionRepository predictionRepository, GameService gameService) {
        this.predictionRepository = predictionRepository;
        this.gameService = gameService;
    }

    public List<Prediction> getByGameId(Long id) {
        return predictionRepository.findByGameId(id);
    }

    public void savePrediction(Prediction prediction, Long gameId, User user) {
        var existingPrediction = predictionRepository.findByGameIdAndUserId(gameId, user.getId());
        if(existingPrediction.isPresent()) {
            log.info("Updating existing prediction");
            var existing = existingPrediction.get();
            existing.setPredictedHomeTeamScore(prediction.getPredictedHomeTeamScore());
            existing.setPredictedAwayTeamScore(prediction.getPredictedAwayTeamScore());
            predictionRepository.save(existing);
            return;
        }

        log.info("Creating new prediction");
        prediction.setId(null);
        prediction.setGame(gameService.getGameById(gameId));
        prediction.setUser(user);
        predictionRepository.save(prediction);

    }
}
