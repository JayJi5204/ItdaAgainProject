package Team.project.itda.Controller;

import Team.project.itda.Service.FormService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class FormController {

    private final FormService formService;

    @GetMapping("/")
    public String getHomepage() {
        return "HomePage";
    }

    @PostMapping("/")
    public String postHomePage() {
        return "redirect:/";
    }
}
