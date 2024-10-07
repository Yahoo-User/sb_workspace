package org.zerock.myapp;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.myapp.domain.Board;
import org.zerock.myapp.persistence.BoardRepository;

import java.util.concurrent.TimeUnit;


@Log4j2
@NoArgsConstructor

@SpringBootTest
public class BoardRepositoryTests {
    @Autowired
    private BoardRepository boardRepo;



//    @Disabled
    @Test
    @Order(1)
    @DisplayName("testInsertBoard")
    @Timeout(1L)
    void testInsertBoard() {
        log.trace("testInsertBoard() invoked.");

        Board newBoard = new Board();
        newBoard.setTitle("NEW_TITLE");
        newBoard.setWriter("NEW_WRITER");
        newBoard.setContent("NEW_CONTENT");

        boardRepo.save(newBoard);
    } // testInsertBoard


} // end class
