package Team.project.itda.Entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class PayEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pay_Id")
    private Long payId;    //계좌 순서

    @Column(name = "depositMoney")
    private Long depositMoney; //입금 금액

    @Column(name = "depositDetails")
    private String depositDetails;   //입금 내용

    @Column(name = "withdrawMoney")
    private Long withdrawMoney;  //출금 금액

    @Column(name = "withdrawDetails")
    private String withdrawDetails;   //출금 내용

    @CreatedDate
    @Column(name = "accountTime", updatable = false)
    private LocalDateTime accountTime;  // 입출금 시간

    @Column(name = "totalMoney")
    private Long totalMoney;      //총 금액

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

}
