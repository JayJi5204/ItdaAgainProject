package Team.project.itda.Controller;

import Team.project.itda.DTO.JoinDTO;
import Team.project.itda.Service.JoinService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class JoinController {

    private final JoinService joinService;



    @GetMapping("/join")
    public String joinPage() {

        return "page/joinPage";
    }

    @PostMapping("/joinProcess")
    public String joinProcess(JoinDTO joinDTO){

        joinService.joinProcess(joinDTO);

        // Todo : 회원가입 성공 시 로그인페이지, 실패 시 조인페이지로 리다이렉션

        return "redirect:/login";
    }

}

