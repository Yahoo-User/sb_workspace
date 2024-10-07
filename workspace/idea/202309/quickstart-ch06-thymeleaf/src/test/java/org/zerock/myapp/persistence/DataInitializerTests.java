package org.zerock.myapp.persistence;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.zerock.myapp.domain.Board;
import org.zerock.myapp.domain.Member;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@Log4j2
@NoArgsConstructor

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

@AutoConfigureMockMvc
@SpringBootTest
public class DataInitializerTests {
    @Autowired private MockMvc mockMvc;
    @Autowired private BoardRepository boardRepository;
    @Autowired private MemberRepository memberRepository;


    @BeforeAll
    void beforeAll() {
        log.trace("beforeAll() invoked.");

        assertThat(this.mockMvc).isNotNull();
        log.info("\t+ this.mockMvc: {}", this.mockMvc);

        assertThat(this.boardRepository).isNotNull();
        log.info("\t+ this.boardRepository: {}", this.boardRepository);

        assertThat(this.memberRepository).isNotNull();
        log.info("\t+ this.memberRepository: {}", this.memberRepository);
    } // beforeAll

    @BeforeEach
    void beforeEach() {
        log.trace("beforeEach() invoked.");

        this.memberRepository.deleteAll();
        this.boardRepository.deleteAll();
    } // beforeEach

//    @Disabled
    @Tag("fast")
    @Test
    @Order(1)
    @DisplayName("testDataInsert")
    @Timeout(1L)
    void testDataInsert() {
        log.trace("testDataInsert() invoked.");

        Member member1 = new Member();
        member1.setId("member1");
        member1.setName("둘리");
        member1.setPassword("member111");
        member1.setRole("ROLE_USER");

        this.memberRepository.save(member1);
        log.info("\t+ member1 saved: {}", member1);

//        ---
        Member member2 = new Member();
        member2.setId("member2");
        member2.setName("도우너");
        member2.setPassword("member222");
        member2.setRole("ROLE_ADMIN");

        this.memberRepository.save(member2);
        log.info("\t+ member2 saved: {}", member2);

//        ---
        for(int i=1; i<=3; i++) {
            Board board = new Board();
            board.setWriter("둘리");
            board.setTitle("둘리가 등록한 게시글 " + i);
            board.setContent("둘리가 등록한 게시글 내용 " + i);

            this.boardRepository.save(board);
            log.info("\t+ New board registered: {}", board);
        } // for

//        ---
        for(int i = 1; i <= 3; i++) {
            Board board = new Board();
            board.setWriter("도우너");
            board.setTitle("도우너가 등록한 게시글 " + i);
            board.setContent("도우너가 등록한 게시글 내용 " + i);

            this.boardRepository.save(board);
            log.info("\t+ New board registered: {}", board);
        } // for

    } // testDataInsert


} // end class
