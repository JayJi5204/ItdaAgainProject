package Team.project.itda.Controller;

import Team.project.itda.DTO.AccountDTO;
import Team.project.itda.Service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;

@Controller
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/account") //통장 관리 페이지
    public String getAccountPage() {
        return "page/AccountPage";
    }
    @GetMapping("/account/complete") //통장 관리 페이지
    public String getAccountCompletePage() {
        return "page/AccountCompletePage";
    }

    @GetMapping("/account/deposit")  //입금 페이지
    public String getAccountDepositPage(Model model, Pageable pageable, Long id, Long depositMoney, String depositDetails, LocalDateTime depositTime, Long withdrawMoney, String withdrawDetails, LocalDateTime withdrawTime, Long totalMoney) {
        model.addAttribute("accountSaveForm", new AccountDTO(id, depositMoney, depositDetails, depositTime, withdrawMoney, withdrawDetails, withdrawTime, totalMoney));
        return "page/AccountDepositPage";
    }

    @PostMapping("/account/deposit") //입금 페이지
    public String postAccountDepositPage(@Valid AccountDTO accountDTO) {
        accountService.saveAccount(accountDTO);
        return "redirect:/account/complete";
    }

    @GetMapping("/account/withdraw")    //출금 페이지
    public String getAccountWithdrawPage(Model model, Pageable pageable, Long id, Long depositMoney, String depositDetails, LocalDateTime depositTime, Long withdrawMoney, String withdrawDetails, LocalDateTime withdrawTime, Long totalMoney) {
        Page<AccountDTO> accountDTOPage = accountService.getAccount(pageable);
        model.addAttribute("accountForm", accountDTOPage);
        model.addAttribute("accountSaveForm", new AccountDTO(id, depositMoney, depositDetails, depositTime, withdrawMoney, withdrawDetails, withdrawTime, totalMoney));
        return "page/AccountWithdrawPage";
    }

    @PostMapping("/account/withdraw")   //출금 페이지
    public String postAccountWithdrawPage(@Valid AccountDTO accountDTO) {
        accountService.saveAccount(accountDTO);
        return "redirect:/account/complete";
    }


    @GetMapping("/account/detail")  //관리 페이지
    public String getAccountDetailPage(Model model, Pageable pageable) {
        Page<AccountDTO> accountDTOPage = accountService.getAccount(pageable);
        model.addAttribute("accountForm", accountDTOPage);
        return "page/AccountDetailPage";
    }

    @PostMapping("/account/{id}/delete")
    public String deleteAccount(@PathVariable Long id) {
        accountService.deleteAccount(id);
        return "redirect:/account/complete";
    }

}
