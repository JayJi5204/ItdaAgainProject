package Team.project.itda.Controller;

import Team.project.itda.Common.CurrentUser;
import Team.project.itda.Entity.UserEntity;
import Team.project.itda.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {


    private final UserRepository userRepository;

//    @CurrentUser 를 쓰는 이유
//    인증된 유저의 name 외에도 가져오기 위해 @AuthenticationPrincipal 어노테이션을 사용하고,
//    익명 유저를 제외하는 SpEL을 줄여 간편하게 쓰기 위한 커스텀 어노테이션이다.
//    즉 유저의 id,username,password,role을 가져올 수 있는 어노테이션
    @GetMapping("/")
    public String getHomePage(@CurrentUser UserEntity userEntity, Model model) {

        if (userEntity != null) {
            Long userId = userEntity.getId();
            model.addAttribute("userId", userId);
        }
        return "page/HomePage";
    }


    @PostMapping("/")
    public String postHomePage() {

        return "redirect:/";
    }

    @GetMapping("/introduce")
    public String getIntroducePage() {

        return "page/IntroducePage";
    }

    @GetMapping("/goal")
    public String getGoalPage() {


        return "page/GoalPage";
    }
    @GetMapping("/order")
    public String getOrderPage() {


        return "page/OrderPage";
    }


}
