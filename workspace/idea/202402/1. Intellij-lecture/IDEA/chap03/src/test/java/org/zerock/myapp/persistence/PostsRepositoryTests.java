package org.zerock.myapp.persistence;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.zerock.myapp.domain.Posts;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@Log4j2
@NoArgsConstructor

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

@AutoConfigureMockMvc
@SpringBootTest
public class PostsRepositoryTests {
    @Autowired private MockMvc mockMvc;
    @Autowired private PostsRepository postsRepository;


    @BeforeAll
    void beforeAll() {
        log.trace("beforeAll() invoked.");

        assertThat(this.mockMvc).isNotNull();
        log.trace("\t+ this.mockMvc: {}", this.mockMvc);

        assertThat(this.postsRepository).isNotNull();
        log.trace("\t+ this.postsRepository: {}", this.postsRepository);
    } // beforeAll

    //	@Disabled
    @Tag("fast")
    @Test
    @Order(1)
    @DisplayName("testGetAllPostsList")
    @Timeout(1L)
    void testGetAllPostsList() {
        log.trace("testGetAllPostsList() invoked.");

        String title = "TEST TITLE";
        String content = "TEST CONTENT";

        Posts posts =
            Posts.builder().
                    title(title).
                    content(content).
                    author("cherryneo@gmail.com").
                    build();
        log.info("\t+ posts before saving: {}", posts);

        this.postsRepository.save(posts);
        log.info("\t+ posts after saving: {}", posts);

        List<Posts> postsList = this.postsRepository.findAll();
        Posts posts0 = postsList.get(0);
        log.info("\t+ posts0: {}", posts0);

        assertThat(posts0.getTitle()).isEqualTo(title);
        assertThat(posts0.getContent()).isEqualTo(content);
    } // testGetAllPostsList

    //	@Disabled
    @Tag("fast")
    @Test
    @Order(2)
    @DisplayName("testBaseTimeEntity")
    @Timeout(5L)
    void testBaseTimeEntity() throws InterruptedException {
        log.trace("testBaseTimeEntity() invoked.");

        LocalDateTime now = LocalDateTime.of(2019, 6, 4, 0, 0, 0);
        log.info("\t+ now: {}", now);

        Posts posts =
                Posts.builder().
                        title("title").
                        content("content").
                        author("author").
                        build();

        this.postsRepository.<Posts>save(posts);
        log.info("\t+ posts: {}, createDate: {}, modifiedDate: {}",
                posts, posts.getCreatedDate(), posts.getModifiedDate());

//        ---
        assertThat(posts.getCreatedDate()).isAfter(now);
        assertThat(posts.getModifiedDate()).isAfter(now);
    } // testBaseTimeEntity


    @AfterAll
    void afterAll() {
        log.trace("afterAll() invoked.");

        this.postsRepository.deleteAll();
    } // afterAll

} // end class
