package Team.project.itda.Service;

import Team.project.itda.DTO.UserFormDTO;
import Team.project.itda.DTO.UserUpdateFormDTO;
import Team.project.itda.Entity.UserEntity;
import Team.project.itda.Entity.UserRole;
import Team.project.itda.Repository.UserRepository;
import jakarta.validation.constraints.Null;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Optional;

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

        UserEntity user = userRepository.save(userFormDto.toEntity());
        user.passwordEncode(bCryptPasswordEncoder);
        user.addUserAuthority();

        return user.getId();

//        return userRepository.save(UserEntity.builder()
//                .username(userFormDto.getUsername())
//                .password(bCryptPasswordEncoder.encode(userFormDto.getPassword()))
//                .name(userFormDto.getName())
//                .role(UserRole.ROLE_USER)
//                .build()).getId();
    }

    // 유저정보 비밀번호 수정
    @Transactional
    public void update(Long id, UserUpdateFormDTO updateFormDTO){
        UserEntity userEntity = userRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 회원이 존재하지 않습니다."));

        userEntity.updateUserPassword(bCryptPasswordEncoder.encode(updateFormDTO.getNewPassword()));

    }

    public UserEntity getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow();
    }

    // 패스워드 체크
    public boolean isChkPassword(String currPassword, String chkPassword){

        return bCryptPasswordEncoder.matches(chkPassword, currPassword);
    }

    // 로그인한 유저와 데이터 작성한 유저가 같은지 검증
    public boolean isChkUser(Long pathId, Long userId) {

        if(!pathId.equals(userId)) {
            return false;
        }

        return true;
    }

}