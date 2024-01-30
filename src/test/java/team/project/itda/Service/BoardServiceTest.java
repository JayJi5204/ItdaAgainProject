//package team.project.itda.Service;
//
//import Team.project.itda.DTO.BoardDTO;
//import Team.project.itda.DTO.BoardPageRequestDTO;
//import Team.project.itda.DTO.BoardPageResultDTO;
//import Team.project.itda.Entity.Board;
//import Team.project.itda.Service.BoardService;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//public class BoardServiceTest {
//
//    @Autowired
//    private BoardService service;
//
//    @Test
//    public void testRegister() {
//
//        BoardDTO boardDTO = BoardDTO.builder()
//                .title("title")
//                .category("category")
//                .content("content")
//                .writer("user0")
//                .build();
//
//        System.out.println(service.register(boardDTO));
//    }
//
//    @Test
//    public void testList() {
//        BoardPageRequestDTO pageRequestDTO = BoardPageRequestDTO.builder()
//                .page(1)
//                .size(10)
//                .build();
//
//        BoardPageResultDTO<BoardDTO, Board> resultDTO = service.getList(pageRequestDTO);
//
//
//        System.out.println("PREV: " + resultDTO.isPrev());
//        System.out.println("NEXT: " + resultDTO.isNext());
//        System.out.println("TOTAL: " + resultDTO.getTotalPage());
//
//        System.out.println("-----------------------------------");
//
//        for (BoardDTO boardDTO : resultDTO.getDtoList()) {
//            System.out.println(boardDTO);
//        }
//        System.out.println("===================================");
//        resultDTO.getPageList().forEach(i -> System.out.println(i));
//    }
//
//    @Test
//    public void testSearch() {
//
//        BoardPageRequestDTO requestDTO = BoardPageRequestDTO.builder()
//                .page(1)
//                .size(10)
//                .type("tc") // 검색조건 t, c, w, tc, tcw
//                .keyword("한글")
//                .build();
//
//        BoardPageResultDTO<BoardDTO, Board> resultDTO = service.getList(requestDTO);
//
//        System.out.println("PREV" + resultDTO.isPrev());
//        System.out.println("NEXT" + resultDTO.isNext());
//        System.out.println("TOTAL" + resultDTO.getTotalPage());
//
//
//        System.out.println("----------------------");
//        for (BoardDTO boardDTO : resultDTO.getDtoList()) {
//            System.out.println(boardDTO);
//
//        }
//
//        System.out.println("===========================");
//        resultDTO.getPageList().forEach(i -> System.out.println(i));
//    }
//
//}