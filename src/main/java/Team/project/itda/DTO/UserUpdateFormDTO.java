package Team.project.itda.DTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UserUpdateFormDTO {

    @NotEmpty(message = "비밀번호는 필수항목입니다.")
    private String oldPassword;

    @NotEmpty(message = "비밀번호는 필수항목입니다.")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,12}$", message = "비밀번호는 대소문자와 숫자, 특수문자를 포함한 8~12글자입니다.")
    private String newPassword;

    @NotEmpty(message = "비밀번호 확인은 필수항목입니다.")
    private String newPassword2; // 비밀번호 확인을 위한 변수

    // 회원수정 폼을 가져오기 위한 생성자
    @Builder
    public UserUpdateFormDTO(String oldPassword, String newPassword, String newPassword2, String name) {
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
        this.newPassword2 = newPassword2;
    }

}
