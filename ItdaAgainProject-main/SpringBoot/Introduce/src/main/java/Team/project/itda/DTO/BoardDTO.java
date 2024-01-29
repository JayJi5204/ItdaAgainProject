package Team.project.itda.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardDTO {

    private Long bno; //글번호
    private String title; //제목
    private String category; //카테고리
    private String content; //내용
    private String writer; //작성자
    private LocalDateTime createDt, updateDt; //작성일
//    private int viewCount; //조회수
//    private int likeCount; //좋아요 수
//    private List<CommentDto> comments; //댓글
//    private List<FileDto> attachedFiles; //첨부파일
}
