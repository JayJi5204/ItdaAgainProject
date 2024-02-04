package Team.project.itda.Service;

import Team.project.itda.DTO.BoardDTO;
import Team.project.itda.DTO.BoardPageRequestDTO;
import Team.project.itda.DTO.BoardPageResultDTO;
import Team.project.itda.Entity.Board;
import Team.project.itda.Entity.UserEntity;

public interface BoardService {

    BoardDTO read(Long bno);
    Long register(BoardDTO dto, UserEntity user);

    void remove(Long bno, UserEntity user);

    void modify(BoardDTO dto, UserEntity user);

    BoardPageResultDTO<BoardDTO, Board> getList(BoardPageRequestDTO requestDTO);

    default Board dtoToEntity(BoardDTO dto, UserEntity user) {
        Board entity = Board.builder()
                .bno(dto.getBno())
                .title(dto.getTitle())
                .category(dto.getCategory())
                .content(dto.getContent())
                .writer(user)
                .build();

        return entity;
    }

    default BoardDTO entityToDto(Board entity) {
        BoardDTO dto = BoardDTO.builder()
                .bno(entity.getBno())
                .title(entity.getTitle())
                .category(entity.getCategory())
                .content(entity.getContent())
                .writer(entity.getWriter().getName())
                .createDt(entity.getCreateDt())
                .updateDt(entity.getUpdateDt())
                .build();

        return dto;
    }
}