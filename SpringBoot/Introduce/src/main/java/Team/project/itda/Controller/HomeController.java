package Team.project.itda.Controller;

import Team.project.itda.Service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collection;
import java.util.Iterator;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final AccountService accountService;

    @GetMapping("/")
    public String getHomepage(Model model) {

        String id = SecurityContextHolder.getContext().getAuthentication().getName(); // 세션 현재 사용자 아이디

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); // 세션 현재 사용자 role

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iter = authorities.iterator();
        GrantedAuthority auth = iter.next();
        String role = auth.getAuthority();

        model.addAttribute("id", id);
        model.addAttribute("role", role);

        return "page/HomePage";
    }
    @PostMapping("/")
    public String postHomePage() {
        return "redirect:/";
    }

}
