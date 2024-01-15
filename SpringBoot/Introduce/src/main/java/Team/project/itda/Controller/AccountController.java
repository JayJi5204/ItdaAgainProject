package Team.project.itda.Controller;

import Team.project.itda.DTO.AccountDTO;
import Team.project.itda.Service.AccountService;
import jakarta.transaction.Transactional;
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

    @GetMapping("/account")
    public String getHomepage(Model model, Pageable pageable, Long id, Long depositMoney, String depositDetails, LocalDateTime depositTime, Long withdrawMoney, String withdrawDetails, LocalDateTime withdrawTime, Long totalMoney) {
        Page<AccountDTO> accountDTOPage = accountService.getAccount(pageable);
        model.addAttribute("accountForm", accountDTOPage);
        model.addAttribute("accountSaveForm", new AccountDTO(id, depositMoney, depositDetails, depositTime, withdrawMoney, withdrawDetails, withdrawTime, totalMoney));
        return "page/AccountPage";
    }

    @PostMapping("/account")
    public String postStartGame(@Valid AccountDTO accountDTO) {
        accountService.saveAccount(accountDTO);
        return "redirect:/account";
    }

    @PostMapping("/account/{id}/delete")
    public String deleteAccount(@PathVariable Long id) {
        accountService.deleteAccount(id);
        return "redirect:/account";
    }

}
