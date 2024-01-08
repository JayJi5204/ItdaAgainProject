package Team.project.itda.Controller;

import Team.project.itda.Service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final AccountService accountService;

    @GetMapping("/")
    public String getHomepage() {
        return "page/HomePage";
    }
    @PostMapping("/")
    public String postHomePage() {
        return "redirect:/";
    }

}
