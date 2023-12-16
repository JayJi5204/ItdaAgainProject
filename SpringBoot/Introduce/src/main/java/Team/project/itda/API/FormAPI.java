package Team.project.itda.API;


import Team.project.itda.Service.FormService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class FormAPI {

    private final FormService formService;

    @PostMapping("/api/")
    public CreateFormResponse createFormResponse(@RequestBody @Valid CreateFormRequset createFormRequset) {
        return null;
    }

    @Data
    @AllArgsConstructor
    static class CreateFormResponse {
        private Long id;
    }

    @Data
    @AllArgsConstructor
    static class CreateFormRequset {
        private String ex;
    }


}
