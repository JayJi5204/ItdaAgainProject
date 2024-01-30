package Team.project.itda.Controller;

import Team.project.itda.Common.CurrentUser;
import Team.project.itda.DTO.PayDTO;
import Team.project.itda.Entity.PayEntity;
import Team.project.itda.Entity.UserEntity;
import Team.project.itda.Repository.PayRepository;
import Team.project.itda.Repository.UserRepository;
import Team.project.itda.Service.PayService;
import Team.project.itda.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class PayController {

    private final PayService payService;
    private final PayRepository payRepository;
    private final UserRepository userRepository;
    private final UserService userService;

    @GetMapping("/pay") //통장 페이지
    public String getPayPage(@CurrentUser UserEntity userEntity, Model model) {
        model.addAttribute("userId", userEntity.getId());
        return "page/PayPage";
    }

    @GetMapping("/pay/deposit")    //입금 페이지
    public String getPayDepositPage(@CurrentUser UserEntity userEntity, Model model, Long payId, Long depositMoney, String depositDetails, Long withdrawMoney, String withdrawDetails, LocalDateTime accountTime, Long totalMoney) {
        Long userId = userEntity.getId();
        UserEntity userIdEntity = userService.getUserById(userId);
        Long nowMoney = payService.getTotalMoney(userId);
        model.addAttribute("pay", nowMoney);
        model.addAttribute("userForm", userIdEntity);
        model.addAttribute("paySaveForm", new PayDTO(payId, depositMoney, depositDetails, withdrawMoney, withdrawDetails, accountTime, totalMoney, userEntity));
        return "page/PayDepositPage";
    }


    @PostMapping("/pay/deposit/{id}") //입금 페이지
    public String postPayDepositPage(@PathVariable("id") Long id, @Valid PayDTO payDTO, Model model) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow();
        PayDTO payForm = new PayDTO(
                payDTO.getPayId(),
                payDTO.getDepositMoney(),
                payDTO.getDepositDetails(),
                payDTO.getWithdrawMoney(),
                payDTO.getWithdrawDetails(),
                payDTO.getAccountTime(),
                payDTO.getTotalMoney(),
                userEntity
        );
        model.addAttribute("userForm", userEntity);
        payService.savePay(payForm, id);
        return "redirect:/pay/complete";
    }


    @GetMapping("/pay/withdraw")    //출금 페이지
    public String getPayWithdrawPage(@CurrentUser UserEntity userEntity, Model model, Long payId, Long depositMoney, String depositDetails, Long withdrawMoney, String withdrawDetails, LocalDateTime accountTime,Long totalMoney, PayEntity payEntity) {
        Long userId = userEntity.getId();
        UserEntity userIdEntity = userService.getUserById(userId);
        Long nowMoney = payService.getTotalMoney(userId);
        model.addAttribute("pay", nowMoney);
        model.addAttribute("userForm", userIdEntity);
        model.addAttribute("paySaveForm", new PayDTO(payId, depositMoney, depositDetails, withdrawMoney, withdrawDetails, accountTime,totalMoney, userEntity));
        return "page/PayWithdrawPage";
    }

    @PostMapping("/pay/withdraw/{id}")   //출금 페이지
    public String postPayWithdrawPage(@PathVariable("id") Long id, @Valid PayDTO payDTO, Model model) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow();
        PayDTO payForm = new PayDTO(
                payDTO.getPayId(),
                payDTO.getDepositMoney(),
                payDTO.getDepositDetails(),
                payDTO.getWithdrawMoney(),
                payDTO.getWithdrawDetails(),
                payDTO.getAccountTime(),
                payDTO.getTotalMoney(),
                userEntity
        );
        model.addAttribute("userForm", userEntity);
        payService.savePay(payForm, id);
        return "redirect:/pay/complete";
    }


    @GetMapping("/pay/detail/{id}")//통장 관리 페이지
    public String getPayDetailPage(@PathVariable("id") Long id, @CurrentUser UserEntity userEntity, Model model) {
        try {
            // URL로 받은 id와 로그인 유저 id 검증
            userService.isChkUser(id, userEntity.getId());
            model.addAttribute("userForm", userEntity);
            List<PayEntity> payForm = payService.getPayEntitiesByUser(userEntity);
            model.addAttribute("payForm", payForm);

        } catch (AccessDeniedException e) { // 검증 실패할 경우
            e.printStackTrace();
            model.addAttribute("errorMessage", e.getMessage());
            return "redirect:/";
        }
        return "page/PayDetailPage";
    }


    @GetMapping("/pay/complete")
    public String getPayCompletePage(@CurrentUser UserEntity userEntity, Model model) {
        model.addAttribute("userId", userEntity.getId());
        return "page/PayCompletePage";
    }


}
