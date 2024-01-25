package Team.project.itda.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Board extends BoardBaseEntity { // BaseBoardEntity를 상속 해아함

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bno; //글번호

    @Column(length = 100, nullable = false)
    private String title; //제목

//    @Column(nullable = false)
//    private String category; //카테고리

    @Column(length = 1500, nullable = false)
    private String content; //내용

    @Column(length = 50, nullable = false)
    private String writer; //작성자

    public void changeTitle(String title) {
        this.title = title;
    }

    public void changeContent(String content) {
        this.content = content;
    }

    //특정한 엔티티를 수정한 후 save() 했을 경우 동작 (ex 제목이나, 내용을 바꿨을 경우)

//    private int viewCount; //조회수

//    private int likeCount; //좋아요 수

//    private List<CommentDto> comments; //댓글

//    private List<FileDto> attachedFiles; //첨부파일

}
