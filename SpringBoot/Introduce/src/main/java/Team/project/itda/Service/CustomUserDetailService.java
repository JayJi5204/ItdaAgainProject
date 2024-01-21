package Team.project.itda.Service;

import Team.project.itda.DTO.CustomUserDetails;
import Team.project.itda.Entity.UserEntity;
import Team.project.itda.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
// 스프링 시큐리티에서 사용자 정보를 가져오는 인터페이스
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override   // 아이디로 사용자 정보를 가져오는 메서드
    public CustomUserDetails loadUserByUsername(String username) {
        UserEntity userEntity = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username + "해당하는 아이디가 존재하지 않습니다."));

        return new CustomUserDetails(userEntity);
    }
}
