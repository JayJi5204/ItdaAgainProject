package Team.project.itda.API;


import Team.project.itda.DTO.PayDTO;
import Team.project.itda.Service.PayService;
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
public class PayAPI {

    private final PayService payService;

    @PostMapping("/api/account")
    public CreatePayResponse createAccountResponse(@RequestBody @Valid CreatePayRequest request) {
        // 계산된 totalMoney로 AccountDTO 생성
        PayDTO payDTO = new PayDTO(
                null,
                request.getDepositMoney(),
                request.getDepositDetails(),
                null,
                request.getWithdrawMoney(),
                request.getWithdrawDetails(),
                null,
                request.getTotalMoney()  // 계산된 totalMoney로 설정
        );

        // 계좌를 저장하고 생성된 ID를 가져옴
        Long id = payService.saveApiPay(payDTO);

        // 응답에 ID 반환
        return new CreatePayResponse(id);
    }


    @GetMapping("/api/account")
    public List<PayDTO> getPay() {
        return payService.getApiPay();
    }

    @Data
    @AllArgsConstructor
    static class CreatePayResponse {
        private Long id;

    }

    @Data
    @AllArgsConstructor
    static class CreatePayRequest {
        private Long depositMoney;
        private String depositDetails;
        private Long withdrawMoney;
        private String withdrawDetails;
        private Long totalMoney;
    }

}
