package Team.project.itda.Controller;

import Team.project.itda.Common.CurrentUser;
import Team.project.itda.DTO.CustomUserDetails;
import Team.project.itda.Entity.UserEntity;
import Team.project.itda.Service.PayService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final PayService payService;

    @GetMapping("/")
    public String getHomepage() {

        return "page/HomePage";
    }

    @PostMapping("/")
    public String postHomePage() {
        return "redirect:/";
    }

}
