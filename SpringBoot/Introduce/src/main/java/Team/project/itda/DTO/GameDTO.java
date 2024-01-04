package Team.project.itda.DTO;


import Team.project.itda.Entity.GameEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GameDTO {

    private Long id;
    private int nowPoint;
    private int account;

    public GameDTO(Long id, int nowPoint,int account) {
        this.id = id;
        this.nowPoint = nowPoint;
        this.account=account;
    }
    public static GameDTO toGameDTO(GameEntity gameEntity) {
        return new GameDTO(
                gameEntity.getId(),
                gameEntity.getNowPoint(),
                gameEntity.getAccount()
        );
    }

}
