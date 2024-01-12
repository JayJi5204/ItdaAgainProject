package Team.project.itda.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @GetMapping("/my")
    public String userPage() {

        return "page/userPage";
    }

    @GetMapping("/login")
    public String loginPage() {

        return "page/loginPage";
    }
}
