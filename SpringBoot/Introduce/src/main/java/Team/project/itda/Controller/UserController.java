package Team.project.itda.Controller;

import Team.project.itda.DTO.UserFormDTO;
import Team.project.itda.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Log4j2
@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @GetMapping("/login")
    public String loginPage() {

        return "page/loginPage";
    }


    @GetMapping("/join")
    public String joinPage(UserFormDTO userFormDTO) {

        return "page/JoinPage";
    }

    @PostMapping("/joinProcess")
    public String joinProcess(Model model, @Valid UserFormDTO userFormDTO, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) { // joinDTO 검증 실패 시
            return "page/joinPage";
        }

        if (!userFormDTO.getPassword().equals(userFormDTO.getPassword2())) { // 비밀번호가 다를경우
            bindingResult.rejectValue("password2", "passwordInCorrect", "패스워드가 일치하지 않습니다.");

            return "page/joinPage";
        }

        try {
            userService.save(userFormDTO);

        } catch (RuntimeException e) {
            e.printStackTrace();
            log.info("-----------" + e.getMessage() + "------------------");

            model.addAttribute("errorMessage", e.getMessage());

            return "page/joinPage";
        } catch (Exception e){
            e.printStackTrace();

            return "page/joinPage";
        }

        return "redirect:/login";
    }
}
