package Team.project.itda.Controller;

import Team.project.itda.Common.CurrentUser;
import Team.project.itda.DTO.CustomUserDetails;
import Team.project.itda.DTO.UserFormDTO;
import Team.project.itda.DTO.UserUpdateFormDTO;
import Team.project.itda.Entity.UserEntity;
import Team.project.itda.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Log4j2
@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    // 로그인 폼
    @GetMapping("/login")
    public String loginPage() {

        return "page/loginPage";
    }


    // 회원가입 폼
    @GetMapping("/join")
    public String joinPage(UserFormDTO userFormDTO) {

        return "page/JoinPage";
    }

    // 회원가입
    @PostMapping("/joinProcess")
    public String joinProcess(Model model, @Valid UserFormDTO userFormDTO, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) { // joinDTO 검증 실패 시

            return "page/joinPage";
        } else if (!userFormDTO.getPassword().equals(userFormDTO.getPassword2())) { // 비밀번호가 다를경우
            bindingResult.rejectValue("password2", "passwordInCorrect", "패스워드가 일치하지 않습니다.");

            return "page/joinPage";
        }

        try {
            userService.save(userFormDTO);

        } catch (IllegalStateException e) {
            e.printStackTrace();
            log.info("-----------" + e.getMessage() + "------------------");

            model.addAttribute("errorMessage", e.getMessage());

            return "page/joinPage";
        } catch (Exception e) {
            e.printStackTrace();

            return "page/joinPage";
        }

        return "redirect:/login";
    }

    // 회원정보 수정 페이지
    @GetMapping("/user/update")
    public String userUpdatePage(UserUpdateFormDTO userUpdateFormDTO) {

        return "page/UserUpdatePage";
    }

    // 회원정보 수정
    @PostMapping("/user/update")
    public String userUpdate(@CurrentUser UserEntity userEntity, @Valid UserUpdateFormDTO userUpdateFormDTO, BindingResult bindingResult, Model model) {

        String crrPassword = userEntity.getPassword();
        String chkPassword = userUpdateFormDTO.getOldPassword();

        if(!userService.isChkPassword(crrPassword, chkPassword)) {
            bindingResult.rejectValue("oldPassword", "passwordInCorrect", "잘못된 비밀번호입니다.");

            return "page/UserUpdatePage";
        }
        if (bindingResult.hasErrors()) { // joinDTO 검증 실패 시
            return "page/UserUpdatePage";

        } else if (!userUpdateFormDTO.getNewPassword().equals(userUpdateFormDTO.getNewPassword2())) { // 비밀번호가 다를경우
            bindingResult.rejectValue("newPassword2", "passwordInCorrect", "패스워드가 일치하지 않습니다.");

            return "page/UserUpdatePage";
        }

        try {
            userService.update(userEntity.getId(), userUpdateFormDTO);

            return "redirect:/";
        } catch (IllegalArgumentException e){
            model.addAttribute("errorMessage", e.getMessage());
            return "page/UserUpdatePage";
        }

    }


    // 인증 거부 에러 메세지
    @GetMapping("/denied")
    public String accessDenied(@RequestParam(value = "exception", required = false) String exception, Model model){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        model.addAttribute("username", customUserDetails.getUsername());
        model.addAttribute("exception", exception);

        return "page/Denied";
    }
}
