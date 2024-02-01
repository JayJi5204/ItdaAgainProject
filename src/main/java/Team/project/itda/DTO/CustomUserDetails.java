package Team.project.itda.DTO;

import Team.project.itda.Entity.UserEntity;
import lombok.Getter;
import org.springframework.security.core.userdetails.User;

@Getter
public class CustomUserDetails extends User {

    private final UserEntity userEntity;

    public CustomUserDetails(UserEntity userEntity) {
        super(userEntity.getUsername(),
                userEntity.getPassword(),
                userEntity.getAuthorities());
        this.userEntity = userEntity;
    }


}
