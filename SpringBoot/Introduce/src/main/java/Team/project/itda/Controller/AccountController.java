package Team.project.itda.Controller;

import Team.project.itda.DTO.AccountDTO;
import Team.project.itda.Service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/account")
    public String getHomepage(Model model, Long id, Long depositMoney, String depositDetails, LocalDateTime depositTime, Long withdrawMoney, String withdrawDetails, LocalDateTime withdrawTime, Long totalMoney) {
        List<AccountDTO> accountEntities = accountService.getAccount();
        model.addAttribute("accountForm", new AccountDTO(id, depositMoney, depositDetails, depositTime, withdrawMoney, withdrawDetails, withdrawTime, totalMoney));
        return "page/AccountPage";
    }

    @PostMapping("/account")
    public String postStartGame(@ModelAttribute @Valid AccountDTO accountDTO, Model model) {
        accountService.saveAccount(accountDTO);
        model.addAttribute("accountSaveForm", accountDTO);
        return "redirect:/account";
    }

    @PostMapping("/account/{id}/delete")
    public String deleteAccount(@PathVariable Long id) {
        accountService.deleteAccount(id);
        return "redirect:/account";
    }

}
