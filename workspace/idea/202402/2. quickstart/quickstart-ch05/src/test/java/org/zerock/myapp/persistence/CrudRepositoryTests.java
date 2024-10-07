package org.zerock.myapp.persistence;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.myapp.domain.Board;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;


@Log4j2
@NoArgsConstructor

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

// ================================
// @Commit
// ================================
// @Target({ElementType.TYPE, ElementType.METHOD})
//
// `@Commit` is a test annotation that is used to indicate that a `test-managed transaction` should be committed
// after the test method has completed.
//@Commit

// ================================
// @Rollback
// ================================
// @Target({ElementType.TYPE, ElementType.METHOD})
//
// `@Rollback` is a test annotation that is used to indicate
// whether a `test-managed transaction` should be rolled back after the test method has completed.
//@Rollback

// ================================
// @AutoConfigureMockMvc
// ================================
// (Caution) If `spring.main.web-application-type = none` in `application.properties' file,
// Then, `MockMvc` Bean Not Created and Injection Failed.
@AutoConfigureMockMvc

@SpringBootTest                                                                 // Without Embedded WAS (Mocking).
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)  // With    Embedded WAS.
public class CrudRepositoryTests {
    @Setter(onMethod_ = @Autowired)
    private MockMvc mockMvc;

    @Setter(onMethod_ = @Autowired)
    private BoardRepository boardRepository;


    @BeforeAll
    void beforeAll() {
        log.trace("beforeAll() invoked.");

        assertNotNull(this.mockMvc);
        log.info("\t+ this.mockMvc: {}", this.mockMvc);

        assertNotNull(this.boardRepository);
        log.info("\t+ this.boardRepository: {}", this.boardRepository);
    } // beforeAll

    @Disabled
    @Tag("fast")
    @Test
    @Order(1)
    @DisplayName("0. prepareDummyData")
    @Timeout(10L)
    void prepareDummyData() {
        log.trace("prepareDummyData() invoked.");

        for(int i=1; i<=200; i++) {
            Board newBoard = new Board();										// *New*
            newBoard.setTitle("TITLE_" + i);
            newBoard.setWriter("Yoseph");
            newBoard.setContent("CONTENT_" + i);

            this.boardRepository.save(newBoard);								// *Managed*
            log.info("\t+ newBoard: {}", newBoard);
        } // classical for
    } // prepareDummyData

//    @Disabled
    @Tag("fast")
    @Test
    @Order(2)
    @DisplayName("1. testCount")
    @Timeout(1L)
    void testCount() {
        log.trace("testCount() invoked.");

        // Returns the number of entities available.
        long totalNumberOfEntities = this.boardRepository.count();
        log.info("\t+ totalNumberOfEntities: {}", totalNumberOfEntities);
    } // testCount

//    @Disabled
    @Tag("fast")
    @Test
    @Order(3)
    @DisplayName("2. testDeleteById")
    @Timeout(1L)
    void testDeleteById() {
        log.trace("testDeleteById() invoked.");

        long seq = 22;

        // Deletes the entity with the given id.
        // If the entity is not found in the persistence store it is silently ignored.
        this.boardRepository.deleteById(seq);
    } // testDeleteById

//    @Disabled
    @Tag("fast")
    @Test
    @Order(4)
    @DisplayName("3. testDelete")
    @Timeout(1L)
    void testDelete() {
        log.trace("testDelete() invoked.");

        Board boardToBeDeleted = new Board();
        boardToBeDeleted.setSeq(11L);

        // Deletes a given entity.
        this.boardRepository.delete(boardToBeDeleted);
    } // testDeleteWithID

//    @Disabled
    @Tag("fast")
    @Test
    @Order(5)
    @DisplayName("4. testDeleteAll")
    @Timeout(10L)
    void testDeleteAll() {
        log.trace("testDeleteAll() invoked.");

        // Deletes all entities managed by the repository.
        this.boardRepository.deleteAll();
    } // testDeleteAll

//    @Disabled
    @Tag("fast")
    @Test
    @Order(6)
    @DisplayName("5. testExistsById")
    @Timeout(10L)
    void testExistsById() {
        log.trace("testExistsById() invoked.");

        Long seq = 33L;

        // Returns whether an entity with the given id exists.
        boolean isExists = this.boardRepository.existsById(seq);
        log.info("\t+ isExists: {}", isExists);
    } // testExistsById

//    @Disabled
    @Tag("fast")
    @Test
    @Order(7)
    @DisplayName("6. testFindAll")
    @Timeout(10L)
    void testFindAll() {
        log.trace("testFindAll() invoked.");

        // Returns all instances of the type.
        Iterable<Board> list = this.boardRepository.findAll();
        list.forEach(log::info);
    } // testFindAll

//    @Disabled
    @Tag("fast")
    @Test
    @Order(8)
    @DisplayName("6. testFindAllByIDs")
    @Timeout(10L)
    void testFindAllByIDs() {
        log.trace("testFindAllByIDs() invoked.");

        List<Long> seqs = Arrays.asList(10L, 20L, 30L);

        // Returns all instances of the type T with the given IDs.
        // If some or all ids are not found, no entities are returned for these IDs.
        // Note that the order of elements in the result is not guaranteed.
        Iterable<Board> list = this.boardRepository.findAllById(seqs);

        list.forEach(log::info);
    } // testFindAllByIDs

//    @Disabled
    @Transactional(readOnly = true)
    @Tag("fast")
    @Test
    @Order(9)
    @DisplayName("6. testSave")
    @Timeout(10L)
    void testSave() {
        log.trace("testSave() invoked.");

        Board board = new Board();
        board.setTitle("NEW_TITLE");
        board.setWriter("Yoseph");
        board.setContent("NEW_CONTENT");

        // Saves a given entity. Use the returned instance for further operations
        // as the save operation might have changed the entity instance completely.
        // Returns: the saved entity; will never be null.
        Board savedBoard = this.boardRepository.save(board);
        log.info("\t+ savedBoard: {}", savedBoard);
    } // testSave

} // end class
