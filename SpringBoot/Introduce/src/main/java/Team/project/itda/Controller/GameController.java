package Team.project.itda.Controller;

import Team.project.itda.DTO.GameDTO;
import Team.project.itda.Service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class GameController {

    private final GameService gameService;

    @GetMapping("/game")
    public String getHomepage(Model model) {
        List<GameDTO> gameForm=gameService.getGame();
        model.addAttribute("gameForm",gameForm);
        return "page/GamePage";
    }

    @PostMapping("/game")
    public String postHomePage() {
        return "redirect:/game";
    }
    @GetMapping("/ready")
    public String getStartGame(){
        return "page/GameReadyPage";
    }
    @PostMapping("/ready")
    public String postStartGame() {
        gameService.startGame();
        return "redirect:/game";
    }

}
