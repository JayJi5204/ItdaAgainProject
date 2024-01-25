package team.project.itda.Repository;

import Team.project.itda.Entity.Board;
import Team.project.itda.Repository.BoardRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class BoardRepositoryTest {
    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void insertDummies() {

        IntStream.rangeClosed(1, 300).forEach(i -> {

            Board board = Board.builder()
                    .title("title..." + i)
                    .content("content..." + i)
                    .writer("user" + (i % 10))
                    .build();
            System.out.println(boardRepository.save(board));
        });

    }

    @Test
    public void updateTest() {

        Optional<Board> result = boardRepository.findById(300L); //존재하는 번호로 테스트

        if (result.isPresent()) {

            Board board = result.get();

            board.changeTitle("Change Title...");
            board.changeContent("Change Content");

            boardRepository.save(board);

        }
    }
}