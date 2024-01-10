package Team.project.itda.Service;

import Team.project.itda.DTO.AccountDTO;
import Team.project.itda.Entity.AccountEntity;
import Team.project.itda.Repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;


    // AccountService.java
    public void saveAccount(AccountDTO accountDTO) {
        AccountEntity lastAccountEntity = accountRepository.findFirstByOrderByIdDesc();
        Long lastTotalMoney = (lastAccountEntity != null) ? lastAccountEntity.getTotalMoney() : 0;
        Long totalMoney = lastTotalMoney + accountDTO.getDepositMoney() - accountDTO.getWithdrawMoney();

        AccountEntity accountEntity = new AccountEntity(
                accountDTO.getId(),
                accountDTO.getDepositMoney(),
                accountDTO.getDepositDetails(),
                accountDTO.getDepositTime(),
                accountDTO.getWithdrawMoney(),
                accountDTO.getWithdrawDetails(),
                accountDTO.getWithdrawTime(),
                totalMoney
        );
        accountRepository.save(accountEntity);
    }


    public Page<AccountDTO> getAccount(Pageable pageable) { //계좌 불러오기
        Page<AccountEntity> accountEntityPage = accountRepository.findAll(pageable);
        return accountEntityPage.map(AccountDTO::toAccount);
    }


    public void deleteAccount(Long id) { //계좌 내역 삭제
        accountRepository.deleteById(id);
    }

    //Api 설계를 위한 Service
    public Long saveApiAccount(AccountDTO accountDTO) {
        Long totalMoney = accountDTO.getDepositMoney() - accountDTO.getWithdrawMoney();
        AccountEntity accountEntity = new AccountEntity(
                accountDTO.getId(),
                accountDTO.getDepositMoney(),
                accountDTO.getDepositDetails(),
                accountDTO.getDepositTime(),
                accountDTO.getWithdrawMoney(),
                accountDTO.getWithdrawDetails(),
                accountDTO.getWithdrawTime(),
                totalMoney
        );
        accountRepository.save(accountEntity);
        return accountEntity.getId();
    }

    public List<AccountDTO> getApiAccount() {    //계좌 불러오기
        List<AccountEntity> accountEntity = accountRepository.findAll();
        return accountEntity.stream().map(AccountDTO::toAccount).collect(Collectors.toList());
    }
}