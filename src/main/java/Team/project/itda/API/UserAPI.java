package Team.project.itda.API;


import Team.project.itda.DTO.UserFormDTO;
import Team.project.itda.DTO.UserUpdateFormDTO;
import Team.project.itda.Entity.UserEntity;
import Team.project.itda.Repository.UserRepository;
import Team.project.itda.Service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserAPI {

    private final UserService userService;
    private final UserRepository userRepository;

    // 회원가입
    @PostMapping("/api/users")
    public CreateUserResponse saveUser(@RequestBody @Valid UserFormDTO request) {
        Long id = userService.save(request);

        return new CreateUserResponse(id);
    }

    // 회원 수정
    @PutMapping("/api/users/{id}")
    public UpdateUserResponse updateUser(@PathVariable("id") Long id, @RequestBody @Valid UserUpdateFormDTO request) {

        UserEntity findUser = userRepository.findById(id).orElseThrow();
        if(userService.isChkPassword(findUser.getPassword(), request.getOldPassword())){

            userService.update(id, request);
        }


        return new UpdateUserResponse(findUser.getId(), findUser.getPassword());
    }


    @Data
    @AllArgsConstructor
    static class UpdateUserResponse {
        private Long id;
        private String password;
    }

    @Data
    static class CreateUserResponse {
        private Long id;

        public CreateUserResponse(Long id){
            this.id = id;
        }
    }

}
