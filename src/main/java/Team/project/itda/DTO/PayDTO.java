package Team.project.itda.DTO;


import Team.project.itda.Entity.PayEntity;
import Team.project.itda.Entity.UserEntity;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PayDTO {

    private Long payId;
    private Long depositMoney;
    private String depositDetails;
    private Long withdrawMoney;
    private String withdrawDetails;
    private LocalDateTime accountTime;
    private Long totalMoney;
    private UserEntity userEntity;


    public PayDTO(Long payId, Long depositMoney, String depositDetails, Long withdrawMoney, String withdrawDetails, LocalDateTime accountTime, Long totalMoney, UserEntity userEntity) {
        this.payId = payId;
        this.depositMoney = depositMoney;
        this.depositDetails = depositDetails;
        this.withdrawMoney = withdrawMoney;
        this.withdrawDetails = withdrawDetails;
        this.accountTime = accountTime;
        this.totalMoney = totalMoney;
        this.userEntity = userEntity;
    }


    public static PayDTO toPay(PayEntity payEntity) {
        return new PayDTO(
                payEntity.getPayId(),
                payEntity.getDepositMoney(),
                payEntity.getDepositDetails(),
                payEntity.getWithdrawMoney(),
                payEntity.getWithdrawDetails(),
                payEntity.getAccountTime(),
                payEntity.getTotalMoney(),
                payEntity.getUserEntity()
        );
    }

}
