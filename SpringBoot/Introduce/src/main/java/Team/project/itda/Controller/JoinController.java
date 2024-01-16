package Team.project.itda.Controller;

import Team.project.itda.DTO.JoinDTO;
import Team.project.itda.Service.JoinService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class JoinController {

    private final JoinService joinService;


    @GetMapping("/join")
    public String joinPage(JoinDTO joinDTO) {

        return "page/joinPage";
    }

    @PostMapping("/joinProcess")
    public String joinProcess(@Valid JoinDTO joinDTO, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) { // joinDTO 검증 실패 시
            return "page/joinPage";
        }

        if (!joinDTO.getPassword().equals(joinDTO.getPassword2())) { // 비밀번호가 다를경우
            bindingResult.rejectValue("password2", "passwordInCorrect", "패스워드가 일치하지 않습니다.");

            return "page/joinPage";
        }

        try {
            joinService.joinProcess(joinDTO);
        } catch (DataIntegrityViolationException e) { // 중복 ID일 경우
            e.printStackTrace();
            bindingResult.reject("signupFailed", "이미 등록된 사용자입니다.");

            return "page/joinPage";
        } catch (Exception e) {
            e.printStackTrace();
            bindingResult.reject("signupFailed", e.getMessage());

            return "page/joinPage";
        }

//        joinService.joinProcess(joinDTO);


        return "redirect:/login";
    }
}

