package Team.project.itda.Service;

import Team.project.itda.DTO.AccountDTO;
import Team.project.itda.Entity.AccountEntity;
import Team.project.itda.Repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;


    public void saveAccount(AccountDTO accountDTO) { //계좌 저장
        AccountEntity accountEntity = new AccountEntity(
                accountDTO.getId(),
                accountDTO.getDepositMoney(),
                accountDTO.getDepositDetails(),
                accountDTO.getDepositTime(),
                accountDTO.getWithdrawMoney(),
                accountDTO.getWithdrawDetails(),
                accountDTO.getWithdrawTime(),
                accountDTO.getTotalMoney()
        );
        accountRepository.save(accountEntity);
    }

    public List<AccountDTO> getAccount() {    //계좌 불러오기
        List<AccountEntity> accountEntity = accountRepository.findAll();
        return accountEntity.stream().map(AccountDTO::toAccount).collect(Collectors.toList());
    }

    public List<AccountDTO> updateAccount() {
        return null;
    }

    public void deleteAccount(Long id) { //계좌 내역 삭제
        accountRepository.deleteById(id);
    }

    //Api 설계를 위한 Service
    public Long saveApiAccount(AccountDTO accountDTO) {

        AccountEntity accountEntity = new AccountEntity(
                accountDTO.getId(),
                accountDTO.getDepositMoney(),
                accountDTO.getDepositDetails(),
                accountDTO.getDepositTime(),
                accountDTO.getWithdrawMoney(),
                accountDTO.getWithdrawDetails(),
                accountDTO.getWithdrawTime(),
                accountDTO.getTotalMoney()
        );
        accountRepository.save(accountEntity);
        return accountEntity.getId();
    }
}