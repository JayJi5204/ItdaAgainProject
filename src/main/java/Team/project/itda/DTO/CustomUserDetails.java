package Team.project.itda.DTO;

import Team.project.itda.Entity.UserEntity;
import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.List;

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
