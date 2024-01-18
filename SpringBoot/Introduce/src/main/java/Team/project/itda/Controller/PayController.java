package Team.project.itda.Controller;

import Team.project.itda.Common.CurrentUser;
import Team.project.itda.DTO.CustomUserDetails;
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
    public String getPayPage(@CurrentUser CustomUserDetails customUserDetails, Model model) {

        if(customUserDetails != null){
            model.addAttribute("currentUser",customUserDetails.getUserEntity());
        }
        return "page/PayPage";
    }
    @GetMapping("/pay/complete") //통장 관리 페이지
    public String getPayCompletePage() {
        return "page/PayCompletePage";
    }

    @GetMapping("/pay/deposit")  //입금 페이지
    public String getPayDepositPage(Model model, Pageable pageable, Long id, Long depositMoney, String depositDetails, LocalDateTime depositTime, Long withdrawMoney, String withdrawDetails, LocalDateTime withdrawTime, Long totalMoney) {
        model.addAttribute("paySaveForm", new PayDTO(id, depositMoney, depositDetails, depositTime, withdrawMoney, withdrawDetails, withdrawTime, totalMoney));
        return "page/PayDepositPage";
    }

    @PostMapping("/pay/deposit") //입금 페이지
    public String postPayDepositPage(@Valid PayDTO payDTO) {
        payService.savePay(payDTO);
        return "redirect:/pay/complete";
    }

    @GetMapping("/pay/withdraw")    //출금 페이지
    public String getPayWithdrawPage(Model model, Pageable pageable, Long id, Long depositMoney, String depositDetails, LocalDateTime depositTime, Long withdrawMoney, String withdrawDetails, LocalDateTime withdrawTime, Long totalMoney) {
        Page<PayDTO> payDTOPage = payService.getPay(pageable);
        model.addAttribute("payForm", payDTOPage);
        model.addAttribute("paySaveForm", new PayDTO(id, depositMoney, depositDetails, depositTime, withdrawMoney, withdrawDetails, withdrawTime, totalMoney));
        return "page/PayWithdrawPage";
    }

    @PostMapping("/pay/withdraw")   //출금 페이지
    public String postPayWithdrawPage(@Valid PayDTO payDTO) {
        payService.savePay(payDTO);
        return "redirect:/pay/complete";
    }


    @GetMapping("/pay/detail")  //관리 페이지
    public String getPayDetailPage(Model model, Pageable pageable) {
        Page<PayDTO> payDTOPage = payService.getPay(pageable);
        model.addAttribute("payForm", payDTOPage);
        return "page/PayDetailPage";
    }

    @PostMapping("/pay/{id}/delete")
    public String deletePay(@PathVariable Long id) {
        payService.deletePay(id);
        return "redirect:/pay/complete";
    }
}
