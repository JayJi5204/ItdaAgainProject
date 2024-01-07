package Team.project.itda.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GameEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Game_Id")
    @SequenceGenerator(name = "Game_Id", allocationSize = 1)
    @Column(name = "GameEntity_Id")
    private Long id;    //게임 진행자 번호

    @Column(name = "totalPoint")
    private int totalPoint; //총 보유 포인트

    @Column(name = "nowPoint")
    private int nowPoint;  //현재 보유 포인트

    @Column(name = "point")
    private int account;      //그동안 투자한 게임 포인트를 가지고 있는 포인트

    @Column(name = "Account")
    private int point;   //게임 포인트


}
