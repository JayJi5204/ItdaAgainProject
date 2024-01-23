package Team.project.itda.DTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserFormDTO {

    @Size(min=6, max=12, message="ID의 길이는 6~12글자입니다.")
    @NotEmpty(message = "ID는 필수항목입니다.")
    private String username;

    @NotEmpty(message = "비밀번호는 필수항목입니다.")
    @Pattern(regexp="^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,12}$", message ="비밀번호는 대소문자와 숫자, 특수문자를 포함한 8~12글자입니다.")
    private String password;

    @NotEmpty(message = "비밀번호 확인은 필수항목입니다.")
    private String password2; // 비밀번호 확인을 위한 변수

    @NotEmpty(message = "이름은 필수항목입니다.")
    private String name;
}
