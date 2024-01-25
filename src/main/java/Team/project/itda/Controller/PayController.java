package Team.project.itda.Controller;

import Team.project.itda.DTO.PayDTO;
import Team.project.itda.Entity.UserEntity;
import Team.project.itda.Repository.UserRepository;
import Team.project.itda.Service.PayService;
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
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class PayController {

    private final PayService payService;
    private final UserRepository userRepository;

    @GetMapping("/pay/{id}") //통장 페이지
    public String getPayPage(@PathVariable("id") Long id, Model model) {
        Optional<UserEntity> userEntity = userRepository.findById(id);
        model.addAttribute("userForm", userEntity);
        return "page/PayPage";
    }


//    @GetMapping("/pay/{userId}/deposit")  //입금 페이지
//    public String getPayDepositPage(@PathVariable("userId") Long userId, Model model, Long payId, Long depositMoney, String depositDetails, LocalDateTime depositTime, Long withdrawMoney, String withdrawDetails, LocalDateTime withdrawTime, Long totalMoney) {
//        model.addAttribute("paySaveForm", new PayDTO(payId, depositMoney, depositDetails, depositTime, withdrawMoney, withdrawDetails, withdrawTime, totalMoney, userId));
//        return "page/PayDepositPage";
//    }
    @GetMapping("/pay/{userId}/deposit")
    public String getPayDepositPage(@PathVariable("userId") Long userId, Model model, Long payId, Long depositMoney, String depositDetails, LocalDateTime depositTime, Long withdrawMoney, String withdrawDetails, LocalDateTime withdrawTime, Long totalMoney) {
        Optional<UserEntity> userEntity = userRepository.findById(userId);
        model.addAttribute("userForm", userEntity.orElse(null));
        model.addAttribute("paySaveForm", new PayDTO(payId, depositMoney, depositDetails, depositTime, withdrawMoney, withdrawDetails, withdrawTime, totalMoney, userId));
        return "page/PayDepositPage";
    }


    @PostMapping("/pay/{userId}/deposit") //입금 페이지
    public String postPayDepositPage(@PathVariable("userId") Long userId, @Valid PayDTO payDTO, Model model) {
        Optional<UserEntity> userEntity = userRepository.findById(userId);
        model.addAttribute("userForm", userEntity.get());
        payService.savePay(payDTO);
        return "redirect:/pay/complete";
    }


    @GetMapping("/pay/withdraw")    //출금 페이지
    public String getPayWithdrawPage(@PathVariable("id") Long userId, Model model, Pageable pageable, Long id, Long depositMoney, String depositDetails, LocalDateTime depositTime, Long withdrawMoney, String withdrawDetails, LocalDateTime withdrawTime, Long totalMoney) {
        Page<PayDTO> payDTOPage = payService.getPay(pageable);
        model.addAttribute("payForm", payDTOPage);
        model.addAttribute("paySaveForm", new PayDTO(id, depositMoney, depositDetails, depositTime, withdrawMoney, withdrawDetails, withdrawTime, totalMoney, userId));
        return "page/PayWithdrawPage";
    }

    @PostMapping("/pay/withdraw")   //출금 페이지
    public String postPayWithdrawPage(@PathVariable("id") Long userId, @Valid PayDTO payDTO) {
        payService.savePay(payDTO);
        return "redirect:/pay/complete";
    }


    @GetMapping("/pay/{id}/detail")  //관리 페이지
    public String getPayDetailPage(@PathVariable("id") Long id, Model model, Pageable pageable) {
        Page<PayDTO> payDTOPage = payService.getPay(pageable);
        model.addAttribute("payForm", payDTOPage);
        Optional<UserEntity> userEntity = userRepository.findById(id);
        model.addAttribute("userForm", userEntity);
        return "page/PayDetailPage";
    }

    @GetMapping("/pay/complete") //통장 관리 페이지
    public String getPayCompletePage(Model model) {

        return "page/PayCompletePage";
    }


}
