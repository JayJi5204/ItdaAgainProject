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
        // 바인딩 결과에 오류가 있을 경우 회원가입 페이지로 반환합니다.
        if (bindingResult.hasErrors()) {
            return "page/joinPage";
        }

        // 비밀번호와 비밀번호 확인 필드가 일치하지 않을 경우, 유효성 검증 오류를 추가합니다.
        if (!userFormDTO.getPassword().equals(userFormDTO.getPassword2())) {
            bindingResult.rejectValue("password2", "passwordInCorrect", "패스워드가 일치하지 않습니다.");
            return "page/joinPage";
        }

        // 사용자 저장 과정에서 오류가 발생하면 예외를 처리합니다.
        try {
            userService.save(userFormDTO);
        } catch (IllegalStateException e) {
            log.error(e.getMessage(), e);
            model.addAttribute("errorMessage", e.getMessage());

            return "page/joinPage";
        } catch (Exception e) {
            log.error(e.getMessage(), e);

            return "page/joinPage";
        }

        // 모든 과정이 성공적으로 마무리되면 로그인 페이지로 리다이렉트합니다.
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

        // 기존 비밀번호 검증 로직 실행
        if (!bindingResult.hasErrors() &&
                !userService.isChkPassword(crrPassword, chkPassword)) {
            bindingResult.rejectValue("oldPassword", "passwordInCorrect", "잘못된 비밀번호입니다.");
        }

        // 새 비밀번호 동일 여부 검증 로직 실행
        if (!bindingResult.hasErrors() &&
                !userUpdateFormDTO.getNewPassword().equals(userUpdateFormDTO.getNewPassword2())) {
            bindingResult.rejectValue("newPassword2", "passwordInCorrect", "패스워드가 일치하지 않습니다.");
        }

        // 검증 오류가 있을 경우, 사용자 수정 페이지로 리디렉션
        if (bindingResult.hasErrors()) {
            return "page/UserUpdatePage";
        }

        // 사용자 정보 업데이트 시도
        try {
            userService.update(userEntity.getId(), userUpdateFormDTO);
            return "redirect:/";
        } catch (IllegalArgumentException e) {
            log.error(e.getMessage(), e);
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
