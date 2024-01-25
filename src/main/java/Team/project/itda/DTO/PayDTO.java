package Team.project.itda.DTO;


import Team.project.itda.Entity.PayEntity;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PayDTO {

    private Long payId;
    private Long depositMoney;
    private String depositDetails;
    private LocalDateTime depositTime;
    private Long withdrawMoney;
    private String withdrawDetails;
    private LocalDateTime withdrawTime;
    private Long totalMoney;
    private Long userId;


    public PayDTO(Long payId, Long depositMoney, String depositDetails, LocalDateTime depositTime, Long withdrawMoney, String withdrawDetails, LocalDateTime withdrawTime, Long totalMoney,Long userId) {
        this.payId = payId;
        this.depositMoney = depositMoney;
        this.depositDetails = depositDetails;
        this.depositTime = depositTime;
        this.withdrawMoney = withdrawMoney;
        this.withdrawDetails = withdrawDetails;
        this.withdrawTime = withdrawTime;
        this.totalMoney = totalMoney;
        this.userId=userId;
    }


    public static PayDTO toPay(PayEntity payEntity) {
        return new PayDTO(
                payEntity.getPayId(),
                payEntity.getDepositMoney(),
                payEntity.getDepositDetails(),
                payEntity.getDepositTime(),
                payEntity.getWithdrawMoney(),
                payEntity.getWithdrawDetails(),
                payEntity.getWithdrawTime(),
                payEntity.getTotalMoney(),
                payEntity.getUserEntity().getId()
        );
    }

}
