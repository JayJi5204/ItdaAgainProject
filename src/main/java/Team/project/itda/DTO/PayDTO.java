package Team.project.itda.DTO;


import Team.project.itda.Entity.PayEntity;
import Team.project.itda.Entity.UserEntity;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PayDTO {

    private Long payId;
    private Integer depositMoney;
    private String depositDetails;
    private Integer withdrawMoney;
    private String withdrawDetails;
    private LocalDateTime accountTime;
    private Integer totalMoney;
    private UserEntity userEntity;


    public PayDTO(Long payId, Integer depositMoney, String depositDetails, Integer withdrawMoney, String withdrawDetails, LocalDateTime accountTime, Integer totalMoney, UserEntity userEntity) {
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
