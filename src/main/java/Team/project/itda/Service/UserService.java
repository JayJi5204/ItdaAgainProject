package Team.project.itda.Service;

import Team.project.itda.DTO.UserFormDTO;
import Team.project.itda.Entity.UserEntity;
import Team.project.itda.Entity.UserRole;
import Team.project.itda.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    // 회원가입
    @Transactional
    public Long save(UserFormDTO userFormDto) {

        // 아이디 중복검사
        if (userRepository.existsByUsername(userFormDto.getUsername())) {
            throw new IllegalStateException("이미 가입되어 있는 유저입니다");
        }

        return userRepository.save(UserEntity.builder()
                .username(userFormDto.getUsername())
                .password(bCryptPasswordEncoder.encode(userFormDto.getPassword()))
                .name(userFormDto.getName())
                .role(UserRole.USER)
                .build()).getId();
    }

    public UserEntity getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow();
    }

    // 로그인한 유저와 데이터 작성한 유저가 같은지 검증
    public boolean isChkUser(Long pathId, Long userId) {

        if(!pathId.equals(userId)) {
            throw  new AccessDeniedException("인증된 유저가 아닙니다.");
        }

        return true;
    }

}