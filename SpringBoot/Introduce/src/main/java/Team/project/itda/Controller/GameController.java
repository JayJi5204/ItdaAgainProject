package Team.project.itda.Controller;

import Team.project.itda.Service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class GameController {

    private final GameService gameService;

    @GetMapping("/gamepage")
    public String getHomepage() {
        return "page/GamePage";
    }
    @PostMapping("/gamepage")
    public String postHomePage() {
        return "redirect:/gamepage";
    }

}
