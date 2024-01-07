package Team.project.itda.Service;

import Team.project.itda.DTO.GameDTO;
import Team.project.itda.Entity.GameEntity;
import Team.project.itda.Repository.GameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GameService {

    private final GameRepository gameRepository;

    private final GameDTO gameDTO = new GameDTO();

    private boolean gameFinish = false;

    public void startGame() {
        updateAccountValue();
    }

    public void totalValues(){

        int totalValues=0;

        gameDTO.setTotalPoint(totalValues);
    }

    public void updateAccountValue() {
        int value = 500;

        if (!gameFinish) {
            int randomValue1 = new Random().nextInt(value / 10) + 1;
            int randomValue2 = new Random().nextInt(value / 10) + 1;
            int randomValue = value + randomValue1 - randomValue2;
            gameDTO.setAccount(randomValue);
            saveGame(gameDTO);
            System.out.println("random: " + randomValue);

            if (randomValue >= 530 || randomValue <= 470) {
                System.out.println("Finish");
                gameDTO.setAccount(randomValue);
                gameFinish = true;
            }
        }
    }

    public void saveGame(GameDTO gameDTO) {
        GameEntity gameEntity = new GameEntity(gameDTO.getId(), gameDTO.getTotalPoint(), gameDTO.getNowPoint(), gameDTO.getAccount(), gameDTO.getPoint());
        gameRepository.save(gameEntity);
    }

    public List<GameDTO> getGame() {
        List<GameEntity> gameEntities = gameRepository.findAll();
        return gameEntities.stream().map(GameDTO::toGameDTO).collect(Collectors.toList());
    }

}