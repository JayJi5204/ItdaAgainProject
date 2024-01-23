package Team.project.itda.Service;

import Team.project.itda.DTO.UserFormDTO;
import Team.project.itda.Entity.UserEntity;
import Team.project.itda.Entity.UserRole;
import Team.project.itda.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public Long save(UserFormDTO userFormDto) {

        // 아이디 중복검사
        if(userRepository.existsByUsername(userFormDto.getUsername())){
            throw new RuntimeException("이미 가입되어 있는 유저입니다");
        }

       return userRepository.save(UserEntity.builder()
               .username(userFormDto.getUsername())
               .password(bCryptPasswordEncoder.encode(userFormDto.getPassword()))
               .name(userFormDto.getName())
               .role(UserRole.USER)
               .build()).getId();
    }
}