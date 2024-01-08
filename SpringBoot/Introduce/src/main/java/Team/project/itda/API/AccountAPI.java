package Team.project.itda.API;


import Team.project.itda.DTO.AccountDTO;
import Team.project.itda.Repository.AccountRepository;
import Team.project.itda.Service.AccountService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AccountAPI {

    private final AccountService accountService;

    @PostMapping("/api/account")
    public CreateAccountResponse createAccountResponse(@RequestBody @Valid CreateAccountRequest request) {
        AccountDTO accountDTO = new AccountDTO(null, request.depositMoney, request.depositDetails, null, request.withdrawMoney, request.withdrawDetails, null, request.totalMoney);
        Long id = accountService.saveApiAccount(accountDTO);
        return new CreateAccountResponse(id);
    }

    @GetMapping("/api/account")
    public List<AccountDTO> getAccount() {
        return accountService.getAccount();
    }

    @Data
    @AllArgsConstructor
    static class CreateAccountResponse {
        private Long id;

    }

    @Data
    @AllArgsConstructor
    static class CreateAccountRequest {
        private Long id;
        private Long depositMoney;
        private String depositDetails;
        private Long withdrawMoney;
        private String withdrawDetails;
        private Long totalMoney;
    }

}
