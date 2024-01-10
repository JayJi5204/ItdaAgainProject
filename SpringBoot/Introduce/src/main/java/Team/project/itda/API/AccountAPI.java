package Team.project.itda.API;


import Team.project.itda.DTO.AccountDTO;
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
        // 계산된 totalMoney로 AccountDTO 생성
        AccountDTO accountDTO = new AccountDTO(
                null,
                request.getDepositMoney(),
                request.getDepositDetails(),
                null,
                request.getWithdrawMoney(),
                request.getWithdrawDetails(),
                null,
                request.totalMoney  // 계산된 totalMoney로 설정
        );

        // 계좌를 저장하고 생성된 ID를 가져옴
        Long id = accountService.saveApiAccount(accountDTO);

        // 응답에 ID 반환
        return new CreateAccountResponse(id);
    }


    @GetMapping("/api/account")
    public List<AccountDTO> getAccount() {
        return accountService.getApiAccount();
    }

    @Data
    @AllArgsConstructor
    static class CreateAccountResponse {
        private Long id;

    }

    @Data
    @AllArgsConstructor
    static class CreateAccountRequest {
        private Long depositMoney;
        private String depositDetails;
        private Long withdrawMoney;
        private String withdrawDetails;
        private Long totalMoney;
    }

}
