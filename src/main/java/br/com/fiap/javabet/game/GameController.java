package br.com.fiap.javabet.game;

import br.com.fiap.javabet.prediction.Prediction;
import br.com.fiap.javabet.prediction.PredictionService;
import br.com.fiap.javabet.user.User;
import br.com.fiap.javabet.user.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@Slf4j
public class GameController {

    private final GameService gameService;
    private final PredictionService predictionService;
    private final UserRepository userRepository;

    public GameController(GameService gameService, PredictionService predictionService, UserRepository userRepository) {
        this.gameService = gameService;
        this.predictionService = predictionService;
        this.userRepository = userRepository;
    }

    @GetMapping("/")
    public String getAllGames(Model model) {
        List<Game> games = gameService.getAllGames();
        model.addAttribute("games", games);
        return "games";
    }

    @GetMapping("/games/{id}")
    public String getGameDetails(@PathVariable("id") Long id, Model model) {
        Game game = gameService.getGameById(id);
        List<Prediction> predictions = predictionService.getByGameId(id);
        model.addAttribute("game", game);
        model.addAttribute("predictions", predictions);
        model.addAttribute("prediction", new Prediction());
        return "game_details";
    }

    @PostMapping("/games/{id}/predict")
    public String submitPrediction(@PathVariable("id") Long gameId, Prediction prediction, @AuthenticationPrincipal OAuth2User principal) {
        User user = userRepository.findByEmail(principal.getAttribute("email")).orElseGet(User::new);
        predictionService.savePrediction(prediction, gameId, user);
        return "redirect:/games/" + gameId;
    }


}