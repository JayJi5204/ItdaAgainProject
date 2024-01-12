package Team.project.itda.Service;

import Team.project.itda.DTO.JoinDTO;
import Team.project.itda.Entity.UserEntity;
import Team.project.itda.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JoinService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    // Todo : 추후 회원가입 성공 여부를 알려주기 위해 Boolean으로 변경 할 것
    public void joinProcess(JoinDTO joinDTO){

        // todo: db에 동일한 loginId를 가진 회원이 존재하는지 검증 로직 필요

        UserEntity data = new UserEntity();

        data.setLoginId(joinDTO.getLoginId());
        data.setPassword(bCryptPasswordEncoder.encode(joinDTO.getPassword()));
        data.setUserName(joinDTO.getUserName());
        data.setRole("ROLE_USER");

        userRepository.save(data);
    }
}
