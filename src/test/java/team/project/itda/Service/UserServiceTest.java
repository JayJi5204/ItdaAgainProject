package team.project.itda.Service;

import Team.project.itda.DTO.UserFormDTO;
import Team.project.itda.DTO.UserUpdateFormDTO;
import Team.project.itda.Repository.UserRepository;
import Team.project.itda.Service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
//@TestPropertySource(locations = "classpath:application.yml")
@Transactional
class UserServiceTest {

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @DisplayName("회원 가입 테스트")
    @Test
    public void joinTest() throws Exception {
        //given
        UserFormDTO user1 = new UserFormDTO("user1", "1234", "1234", "테스터1");

        //when
        Long saveId = userService.save(user1);

        //then
        Assertions.assertEquals(userService.getUserById(saveId).getUsername(), user1.getUsername());

    }

    @DisplayName("중복 회원 예외 테스트")
    @Test
    public void duplicationJoinTest() throws Exception {
        //given
        UserFormDTO user1 = new UserFormDTO("user1", "1234", "1234", "테스터1");
        UserFormDTO user2 = new UserFormDTO("user1", "1234", "1234", "테스터2");

        //when
        userService.save(user1);

        //then
        Assertions.assertThrows(IllegalStateException.class, () -> {
            userService.save(user2);
        });
    }

    @DisplayName("유저 정보 수정 테스트")
    @Test
    public void update() throws Exception {
        //given
        Long saveId = userService.save(new UserFormDTO("user1", "1234", "1234", "테스터1"));

        UserUpdateFormDTO updateUser = UserUpdateFormDTO.builder()
                .oldPassword("1234")
                .newPassword("1111")
                .newPassword2("1111")
                .build();
//        UserUpdateFormDTO updateUser = new UserUpdateFormDTO("1234", "1111", "1111");

        //when
        String currPassword = userRepository.findById(saveId).orElseThrow().getPassword();
        String updatedPassword = "";
        if(userService.isChkPassword(currPassword, updateUser.getOldPassword())){

            userService.update(saveId, updateUser);
            updatedPassword = userRepository.findById(saveId).orElseThrow().getPassword();
        }

        //then
        Assertions.assertTrue(bCryptPasswordEncoder.matches(updateUser.getNewPassword(), updatedPassword));
    }

}