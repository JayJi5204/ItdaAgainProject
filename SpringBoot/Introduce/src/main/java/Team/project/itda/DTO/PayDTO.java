package Team.project.itda.DTO;


import Team.project.itda.Entity.PayEntity;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PayDTO {

    private Long id;
    private Long depositMoney;
    private String depositDetails;
    private LocalDateTime depositTime;
    private Long withdrawMoney;
    private String withdrawDetails;
    private LocalDateTime withdrawTime;
    private Long totalMoney;


    public PayDTO(Long id, Long depositMoney, String depositDetails, LocalDateTime depositTime, Long withdrawMoney, String withdrawDetails, LocalDateTime withdrawTime, Long totalMoney) {
        this.id = id;
        this.depositMoney = depositMoney;
        this.depositDetails = depositDetails;
        this.depositTime = depositTime;
        this.withdrawMoney = withdrawMoney;
        this.withdrawDetails = withdrawDetails;
        this.withdrawTime = withdrawTime;
        this.totalMoney = totalMoney;
    }


    public static PayDTO toPay(PayEntity payEntity) {
        return new PayDTO(
                payEntity.getId(),
                payEntity.getDepositMoney(),
                payEntity.getDepositDetails(),
                payEntity.getDepositTime(),
                payEntity.getWithdrawMoney(),
                payEntity.getWithdrawDetails(),
                payEntity.getWithdrawTime(),
                payEntity.getTotalMoney()
        );
    }

}
