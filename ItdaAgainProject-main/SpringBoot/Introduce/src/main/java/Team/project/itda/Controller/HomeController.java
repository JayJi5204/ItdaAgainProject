package Team.project.itda.Controller;

import Team.project.itda.Common.CurrentUser;
import Team.project.itda.Entity.UserEntity;
import Team.project.itda.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class HomeController {


    private final UserRepository userRepository;

    @GetMapping("/")
    public String getHomepage(@CurrentUser UserEntity userEntity, Model model) {

        if(userEntity != null){
            Long userId = userEntity.getId();
            model.addAttribute("userId", userId);
        }

        return "page/HomePage";
    }


    @PostMapping("/")
    public String postHomePage() {

        return "redirect:/";
    }


}
