package org.zerock.myapp.persistence;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.myapp.domain.Posts;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertNotNull;


@Log4j2
@NoArgsConstructor

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

@SpringBootTest
public class PostsRepositoryTests {
    @Autowired private PostsRepository postsRepository;


    @BeforeAll
    void beforeAll() {
        log.trace("beforeAll() invoked.");

        assertNotNull(this.postsRepository);
        log.trace("\t+ this.postsRepository: {}", this.postsRepository);
    } // beforeAll

    @AfterAll
    void afterAll() {
        log.trace("afterAll() invoked.");

        Objects.requireNonNull(this.postsRepository);
        this.postsRepository.deleteAll();
    } // afterAll

//    @Disabled
    @Test
    @Order(1)
    @DisplayName("contextLoads")
    @Timeout(1L)
    void contextLoads() {
        log.trace("contextLoads() invoked.");

        String title = "NEW_TITLE";
        String content = "NEW_CONTENT";

        Posts posts =
                Posts.
                    builder().
                    title(title).
                    content(content).
                    author("Yoseph").
                    build();

        log.info("\t1. posts before saving: {}", posts);

        posts = this.postsRepository.<Posts>save(posts);
        log.info("\t2. posts after saving: {}", posts);

        List<Posts> postsList = this.postsRepository.<List<Posts>>findAll();
        log.info("\t3. postsList: {}", postsList);

        posts = postsList.get(0);

        assert posts.getTitle().equals(title);
        assert posts.getContent().equals(content);
    } // contextLoads

} // end class
