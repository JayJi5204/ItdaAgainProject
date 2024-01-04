package Team.project.itda.Service;

import Team.project.itda.DTO.GameDTO;
import Team.project.itda.Entity.GameEntity;
import Team.project.itda.Repository.GameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class GameService {

    private final GameRepository gameRepository;

    private GameDTO gameDTO = new GameDTO();

    private boolean gameFinish = false;

    @Scheduled(fixedRate = 5000)
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
        public void saveGame(GameDTO gameDTO){
            GameEntity gameEntity=new GameEntity(gameDTO.getId(), gameDTO.getNowPoint(), gameDTO.getAccount());
            gameRepository.save(gameEntity);
    }
}