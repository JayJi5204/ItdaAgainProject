package Team.project.itda.Controller;

import Team.project.itda.DTO.PayDTO;
import Team.project.itda.Service.PayService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;

@Controller
@RequiredArgsConstructor
public class PayController {

    private final PayService payService;

    @GetMapping("/pay") //통장 관리 페이지
    public String getPayPage(Model model) {
        loginSetting(model);
        return "page/PayPage";
    }
    @GetMapping("/pay/complete") //통장 관리 페이지
    public String getPayCompletePage() {
        return "page/PayCompletePage";
    }

    @GetMapping("/pay/deposit")  //입금 페이지
    public String getPayDepositPage(Model model, Pageable pageable, Long id, Long depositMoney, String depositDetails, LocalDateTime depositTime, Long withdrawMoney, String withdrawDetails, LocalDateTime withdrawTime, Long totalMoney) {
        model.addAttribute("accountSaveForm", new PayDTO(id, depositMoney, depositDetails, depositTime, withdrawMoney, withdrawDetails, withdrawTime, totalMoney));
        return "page/PayDepositPage";
    }

    @PostMapping("/pay/deposit") //입금 페이지
    public String postPayDepositPage(@Valid PayDTO payDTO) {
        payService.savePay(payDTO);
        return "redirect:/pay/complete";
    }

    @GetMapping("/pay/withdraw")    //출금 페이지
    public String getPayWithdrawPage(Model model, Pageable pageable, Long id, Long depositMoney, String depositDetails, LocalDateTime depositTime, Long withdrawMoney, String withdrawDetails, LocalDateTime withdrawTime, Long totalMoney) {
        Page<PayDTO> accountDTOPage = payService.getPay(pageable);
        model.addAttribute("accountForm", accountDTOPage);
        model.addAttribute("accountSaveForm", new PayDTO(id, depositMoney, depositDetails, depositTime, withdrawMoney, withdrawDetails, withdrawTime, totalMoney));
        return "page/PayWithdrawPage";
    }

    @PostMapping("/pay/withdraw")   //출금 페이지
    public String postPayWithdrawPage(@Valid PayDTO payDTO) {
        payService.savePay(payDTO);
        return "redirect:/pay/complete";
    }


    @GetMapping("/pay/detail")  //관리 페이지
    public String getPayDetailPage(Model model, Pageable pageable) {
        Page<PayDTO> accountDTOPage = payService.getPay(pageable);
        model.addAttribute("accountForm", accountDTOPage);
        return "page/PayDetailPage";
    }

    @PostMapping("/pay/{id}/delete")
    public String deletePay(@PathVariable Long id) {
        payService.deletePay(id);
        return "redirect:/pay/complete";
    }
    
    private void loginSetting(Model model) {    //로그인 세팅
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String id = authentication.getName();
        String role = authentication.getAuthorities().iterator().next().getAuthority();

        model.addAttribute("id", id);
        model.addAttribute("role", role);
    }



}
