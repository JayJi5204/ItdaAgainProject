package Team.project.itda.DTO;


import Team.project.itda.Entity.AccountEntity;
import Team.project.itda.Service.AccountService;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class AccountDTO {

    private Long id;
    private Long depositMoney;
    private String depositDetails;
    private LocalDateTime depositTime;
    private Long withdrawMoney;
    private String withdrawDetails;
    private LocalDateTime withdrawTime;
    private Long totalMoney;


    public AccountDTO(Long id, Long depositMoney, String depositDetails, LocalDateTime depositTime, Long withdrawMoney, String withdrawDetails, LocalDateTime withdrawTime, Long totalMoney) {
        this.id = id;
        this.depositMoney = depositMoney;
        this.depositDetails = depositDetails;
        this.depositTime = depositTime;
        this.withdrawMoney = withdrawMoney;
        this.withdrawDetails = withdrawDetails;
        this.withdrawTime = withdrawTime;
        this.totalMoney = totalMoney;
    }


    public static AccountDTO toAccount(AccountEntity accountEntity) {
        return new AccountDTO(
                accountEntity.getId(),
                accountEntity.getDepositMoney(),
                accountEntity.getDepositDetails(),
                accountEntity.getDepositTime(),
                accountEntity.getWithdrawMoney(),
                accountEntity.getWithdrawDetails(),
                accountEntity.getWithdrawTime(),
                accountEntity.getTotalMoney()
        );
    }

}
