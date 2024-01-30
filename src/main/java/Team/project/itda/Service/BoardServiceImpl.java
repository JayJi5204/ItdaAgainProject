package Team.project.itda.Service;

import Team.project.itda.DTO.BoardDTO;
import Team.project.itda.DTO.BoardPageRequestDTO;
import Team.project.itda.DTO.BoardPageResultDTO;
import Team.project.itda.Entity.Board;
import Team.project.itda.Entity.QBoard;
import Team.project.itda.Repository.BoardRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Function;

@Service
@Log4j2
@RequiredArgsConstructor //의존성 자동 주입
public class BoardServiceImpl implements BoardService{

    private final BoardRepository repository;

    @Override
    public Long register(BoardDTO dto) {

        log.info("DTO-----------");
        log.info(dto);

        Board entity = dtoToEntity(dto);

        log.info(entity);

        repository.save(entity);

        return entity.getBno();
    }

    @Override
    public BoardPageResultDTO<BoardDTO, Board> getList(BoardPageRequestDTO requestDTO) {
        Pageable pageable = requestDTO.getPageable(Sort.by("bno").descending());

        BooleanBuilder booleanBuilder = getSearch(requestDTO);

        Page<Board> result = repository.findAll(booleanBuilder, pageable);

        Function<Board, BoardDTO> fn = (entity ->
                entityToDto(entity));

        return new BoardPageResultDTO<>(result, fn);
    }

    @Override
    public BoardDTO read(Long bno) {
        Optional<Board> result = repository.findById(bno); //만약 엔티티 객체를 가지고 왔다면
        return result.isPresent()? entityToDto(result.get()) : null; //엔티티 객체를 DTO로 변환
    }

    @Override
    public void remove(Long bno) {
        repository.deleteById(bno);
    }

    @Override
    public void modify(BoardDTO dto) {
        Optional<Board> result = repository.findById(dto.getBno());
        if (result.isPresent()) {
            Board entity = result.get();

            entity.changeTitle(dto.getTitle());
            entity.changeCategory(dto.getCategory());
            entity.changeContent(dto.getContent());

            repository.save(entity);
        }
    }

    private BooleanBuilder getSearch(BoardPageRequestDTO requestDTO) {
        String type = requestDTO.getType();

        BooleanBuilder booleanBuilder = new BooleanBuilder();

        QBoard qBoard = QBoard.board;

        String keyword = requestDTO.getKeyword();

        BooleanExpression expression = qBoard.bno.gt(0L); // bno > 0

        booleanBuilder.and(expression);

        if (type == null || type.trim().length() == 0) {
            return booleanBuilder;
        }

        BooleanBuilder conditionBuilder = new BooleanBuilder();

        if (type.contains("t")) {
            conditionBuilder.or(qBoard.title.contains(keyword));
        }
        if (type.contains("c")) {
            conditionBuilder.or(qBoard.content.contains(keyword));
        }
        if (type.contains("w")) {
            conditionBuilder.or(qBoard.writer.contains(keyword));
        }

        booleanBuilder.and(conditionBuilder);

        return booleanBuilder;

    }
}