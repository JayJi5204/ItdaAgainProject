package Team.project.itda.Service;

import Team.project.itda.DTO.PayDTO;
import Team.project.itda.Entity.PayEntity;
import Team.project.itda.Entity.UserEntity;
import Team.project.itda.Repository.PayRepository;
import Team.project.itda.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PayService {

    private final PayRepository payRepository;
    private final UserRepository userRepository;

    public void savePay(PayDTO payDTO, Long id) {   //입출금 저장
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow();

        int depositMoney = (payDTO.getDepositMoney() != null) ? payDTO.getDepositMoney() : 0;
        int withdrawMoney = (payDTO.getWithdrawMoney() != null) ? payDTO.getWithdrawMoney() : 0;
        PayEntity lastPayEntity = payRepository.findFirstByUserEntityOrderByPayIdDesc(userEntity);
        int lastTotalMoney = (lastPayEntity != null) ? lastPayEntity.getTotalMoney() : 0;
        int totalMoney = lastTotalMoney + depositMoney - withdrawMoney;

        String depositDetails = payDTO.getDepositDetails();
        String withdrawDetails = payDTO.getWithdrawDetails();

        if (totalMoney < 0) {
            throw new RuntimeException("출금액이 현재 금액보다 큽니다.");
        }
        ;

        if (withdrawMoney == 0 && depositMoney < 1) {
            throw new RuntimeException("1이상의 양수를 입력해주세요.");
        }
        ;
        if (depositMoney == 0 && withdrawMoney < 1) {
            throw new RuntimeException("1이상의 양수를 입력해주세요.");
        }
        ;

        if (depositDetails != null && depositDetails.length() > 30) {
            throw new RuntimeException("15자 이내로 작성해주세요.");
        }
        if (withdrawDetails != null && withdrawDetails.length() > 30) {
            throw new RuntimeException("15자 이내로 작성해주세요.");
        }
        ;

        PayEntity payEntity = new PayEntity(
                payDTO.getPayId(),
                depositMoney,
                depositDetails,
                withdrawMoney,
                withdrawDetails,
                payDTO.getAccountTime(),
                totalMoney,
                userEntity
        );
        payRepository.save(payEntity);
    }

    public List<PayEntity> getPayEntitiesByUser(UserEntity userEntity) {    //user의 id값에 따른 정보 가져오기
     List<PayEntity> payEntities= payRepository.findByUserEntityOrderByPayIdDesc(userEntity);
         return payEntities.stream().limit(23).collect(Collectors.toList());
    }

    public int getTotalMoney(Long userId) { //user의 id값에 따른 현재 금액 가져오기
        UserEntity userEntity = userRepository.findById(userId).orElseThrow();
        PayEntity lastPayEntity = payRepository.findFirstByUserEntityOrderByPayIdDesc(userEntity);
        if (lastPayEntity == null) {
            return 0;
        } else {
            return lastPayEntity.getTotalMoney();
        }
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