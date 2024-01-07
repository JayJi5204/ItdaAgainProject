package Team.project.itda.DTO;


import Team.project.itda.Entity.GameEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GameDTO {

    private Long id;
    private int totalPoint;
    private int nowPoint;
    private int account;
    private int point;

    public GameDTO(Long id, int totalPoint, int nowPoint,int account,int point) {
        this.id = id;
        this.totalPoint=totalPoint;
        this.nowPoint = nowPoint;
        this.account=account;
        this.point=point;
    }
    public static GameDTO toGameDTO(GameEntity gameEntity) {
        return new GameDTO(
                gameEntity.getId(),
                gameEntity.getTotalPoint(),
                gameEntity.getNowPoint(),
                gameEntity.getPoint(),
                gameEntity.getPoint()
        );
    }

}
