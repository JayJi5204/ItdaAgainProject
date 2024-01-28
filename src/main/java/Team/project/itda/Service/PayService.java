package Team.project.itda.Service;

import Team.project.itda.DTO.PayDTO;
import Team.project.itda.Entity.PayEntity;
import Team.project.itda.Entity.UserEntity;
import Team.project.itda.Repository.PayRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PayService {

    private final PayRepository payRepository;

    public void savePay(PayDTO payDTO) {
        Long depositMoney = (payDTO.getDepositMoney() != null) ? payDTO.getDepositMoney() : 0;
        Long withdrawMoney = (payDTO.getWithdrawMoney() != null) ? payDTO.getWithdrawMoney() : 0;
        PayEntity lastPayEntity = payRepository.findFirstByOrderByPayIdDesc();
        Long lastTotalMoney = (lastPayEntity != null) ? lastPayEntity.getTotalMoney() : 0;
        Long totalMoney = lastTotalMoney + depositMoney - withdrawMoney;

        PayEntity payEntity = new PayEntity(
                payDTO.getPayId(),
                depositMoney,
                payDTO.getDepositDetails(),
                payDTO.getDepositTime(),
                withdrawMoney,
                payDTO.getWithdrawDetails(),
                payDTO.getWithdrawTime(),
                totalMoney,
                payDTO.getUserEntity()
        );
        payRepository.save(payEntity);
    }

    public List<PayEntity> getPayEntitiesByUser(UserEntity userEntity) {
        return payRepository.findByUserEntity(userEntity);
    }

    //Api 설계를 위한 Service
//    public Long saveApiPay(PayDTO payDTO) {
//        PayEntity lastPayEntity = payRepository.findFirstByOrderByPayIdDesc();
//        Long lastTotalMoney = (lastPayEntity != null) ? lastPayEntity.getTotalMoney() : 0;
//        Long totalMoney = lastTotalMoney + payDTO.getDepositMoney() - payDTO.getWithdrawMoney();
//
//        PayEntity payEntity = new PayEntity(
//                payDTO.getPayId(),
//                payDTO.getDepositMoney(),
//                payDTO.getDepositDetails(),
//                payDTO.getDepositTime(),
//                payDTO.getWithdrawMoney(),
//                payDTO.getWithdrawDetails(),
//                payDTO.getWithdrawTime(),
//                totalMoney,
//                UserEntity.builder().build()
//        );
//        payRepository.save(payEntity);
//        return payEntity.getPayId();
//    }
//
//    public List<PayDTO> getApiPay() {    //계좌 불러오기
//        List<PayEntity> payEntity = payRepository.findAll();
//        return payEntity.stream().map(PayDTO::toPay).collect(Collectors.toList());
//    }
}