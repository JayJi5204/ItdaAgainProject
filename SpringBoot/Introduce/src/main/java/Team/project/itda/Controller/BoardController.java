package Team.project.itda.Controller;


import Team.project.itda.DTO.BoardDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/board")
@Log4j2
//Lombok의 기능으로 스프링 부트가 로그 라이브러리 중에 log4j2를 기본으로 사용하고 있기 때문에 별도의 설정 없이 적용이 가능합니다(다만 인텔리제이 경우에는 테스트 코드에 반영하기 위해서는 추가적인 조정이 필요합니다.)
public class BoardController {
    @GetMapping("")
    public String board(Model model) {
        List<BoardDTO> list = IntStream.rangeClosed(1, 20).asLongStream().mapToObj(i -> {
            BoardDTO dto = BoardDTO.builder()
                    .no(i)
                    .title("제목")
                    .category("카테고리")
                    .content("내용")
                    .writer("작성자")
                    .createDt(LocalDateTime.now())
//                    .viewCount((int) i)
//                    .likeCount((int) i)
//                    .comments(i)
//                    .attachedFiles()
                    .build();
            return dto;
        }).collect(Collectors.toList());

        model.addAttribute("list", list);

        return "page/BoardPage"; //템플릿 경로
    }
    @GetMapping("/{i}")
    public String boardView(Model model) {
        List<BoardDTO> list = IntStream.rangeClosed(1, 20).asLongStream().mapToObj(i -> {
            BoardDTO dto = BoardDTO.builder()
                    .no(i)
//                    .title("제목"+i)
//                    .category("카테고리"+i)
//                    .content("내용"+i)
//                    .writer("작성자"+i)
//                    .createdDate(LocalDateTime.now())
//                    .viewCount((int) i)
//                    .likeCount((int) i)
//                    .comments(i)
//                    .attachedFiles()
                    .build();
            return dto;
        }).collect(Collectors.toList());

        model.addAttribute("list", list);

        return "page/BoardViewPage";
    }

    @PostMapping("write")
    public String boardWrite() {

        return "";
    }
}

