package Team.project.itda.Controller;


import Team.project.itda.DTO.BoardDTO;
import Team.project.itda.DTO.BoardPageRequestDTO;
import Team.project.itda.Service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/board")
@Log4j2
@RequiredArgsConstructor
//Lombok의 기능으로 스프링 부트가 로그 라이브러리 중에 log4j2를 기본으로 사용하고 있기 때문에 별도의 설정 없이 적용이 가능합니다(다만 인텔리제이 경우에는 테스트 코드에 반영하기 위해서는 추가적인 조정이 필요합니다.)
public class BoardController {

    private final BoardService service;

    @GetMapping("")
    public String board(BoardPageRequestDTO pageRequestDTO, Model model) {

        model.addAttribute("result", service.getList(pageRequestDTO));

        return "page/BoardPage"; //템플릿 경로
    }

    @GetMapping("/{i}")
    public String boardView() {
        return "page/BoardViewPage";
    }

    //화면을 보여주고
    @GetMapping("/register")
    public String register() {
        log.info("register get...");

        return "page/register";
    }

    //처리 후 목록 페이지로 이동
    @PostMapping("/register")
    public String registerPost(BoardDTO dto, RedirectAttributes redirectAttributes) {
        log.info("dto...." + dto);

        //새로 추가된 엔티티의 번호
        Long bno = service.register(dto);

        redirectAttributes.addFlashAttribute("msg", bno); //addFlashAttribute: 한 번만 데이터를 전달하는 용도

        return "redirect:/board";
    }

    @GetMapping("/read")
    public String read(long bno, @ModelAttribute("requestDTO") BoardPageRequestDTO requestDTO, Model model) {
        log.info("bno: " + bno);

        BoardDTO dto = service.read(bno);

        model.addAttribute("dto", dto);
        return "page/read";
    }

    @GetMapping("/modify")
    public String modifyGet(long bno, @ModelAttribute("requestDTO") BoardPageRequestDTO requestDTO, Model model) {
        log.info("자 이건 수정이야" + bno);

        BoardDTO dto = service.read(bno);

        model.addAttribute("dto", dto);
        return "page/modify";
    }

    @PostMapping("/remove")
    public String remove(long bno, RedirectAttributes redirectAttributes) {
        log.info("자 이건 삭제야" + bno);

        service.remove(bno);
        redirectAttributes.addFlashAttribute("msg", bno);

        return "redirect:/board";
    }

    @PostMapping("/modify")
    public String modifyPost(BoardDTO dto, @ModelAttribute("requestDTO") BoardPageRequestDTO requestDTO, RedirectAttributes redirectAttributes) {
        service.modify(dto);

        redirectAttributes.addAttribute("page", requestDTO.getPage());
        redirectAttributes.addAttribute("type", requestDTO.getType());
        redirectAttributes.addAttribute("keyword", requestDTO.getKeyword());
        redirectAttributes.addAttribute("bno", dto.getBno());

        return "redirect:/board/read";
    }
}