package org.zerock.myapp.controller;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.zerock.myapp.domain.Posts;
import org.zerock.myapp.domain.PostsSaveRequestDto;
import org.zerock.myapp.domain.PostsUpdateRequestDto;
import org.zerock.myapp.persistence.PostsRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@Log4j2
@NoArgsConstructor

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostsApiControllerTests {
    @Autowired private MockMvc mockMvc;
    @Autowired private PostsRepository postsRepository;
    @Autowired private TestRestTemplate testRestTemplate;

    @LocalServerPort
    private int port;


    @BeforeAll
    void beforeAll() {
        log.trace("beforeAll() invoked.");

        assertThat(this.mockMvc).isNotNull();
        log.trace("\t+ 1. this.mockMvc: {}", this.mockMvc);

        assertThat(this.postsRepository).isNotNull();
        log.trace("\t+ 2. this.postsRepository: {}", this.postsRepository);

        assertThat(this.testRestTemplate).isNotNull();
        log.trace("\t+ 3. this.testRestTemplate: {}", this.testRestTemplate);

        log.trace("\t+ 4. this.port: {}", this.port);
    } // beforeAll

    //	@Disabled
    @Tag("fast")
    @Test
    @Order(1)
    @DisplayName("testCreatePosts")
    @Timeout(1L)
    void testCreatePosts() {
        log.trace("testCreatePosts() invoked.");

        String title = "title";
        String content = "content";

        PostsSaveRequestDto requestDto =
                PostsSaveRequestDto.builder().title(title).content(content).build();
        log.info("\t+ 1. requestDto: {}", requestDto);

        String requestUrl = "http://localhost:"+ this.port + "/api/v1/posts";
        log.info("\t+ 2. requestUrl: {}", requestUrl);

        /**
         * ===================================
         * In the `TestRestTemplate` class :
         * ===================================
         * public <T> org.springframework.http.ResponseEntity<T> postForEntity(
         *      String url,
         *      Object request,
         *      Class<T> responseType,
         *      Object... urlVariables )
         * ===================================
         * Create a new resource by POSTing the given object to the URI template,
         * and returns the response as `ResponseEntity`.
         * URI Template variables are expanded using the given URI variables, if any.
         * The request parameter can be a `HttpEntity` in order to add additional HTTP headers to the request.
         *
         * Params:
         *  url : the URL
         *  request : the Object to be POSTed, may be null
         *  responseType : the response type to return
         *  urlVariables : the variables to expand the template
         *
         * Returns:
         *  the converted object
         */
        ResponseEntity<Long> responseEntity =
                this.testRestTemplate.postForEntity(requestUrl, requestDto, Long.class);
        log.info("\t+ 3. responseEntity: {}", responseEntity);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Posts> postsList = this.postsRepository.findAll();
        postsList.forEach(log::info);

        assertThat(postsList.get(0).getTitle()).isEqualTo(title);
        assertThat(postsList.get(0).getContent()).isEqualTo(content);
    } // testCreatePosts

    //	@Disabled
    @Tag("fast")
    @Test
    @Order(2)
    @DisplayName("testUpdatePosts")
    @Timeout(1L)
    void testUpdatePosts() {
        log.trace("testUpdatePosts() invoked.");

        Posts newPosts =
                Posts.builder().
                        title("title").
                        content("content").
                        author("author").
                        build();
        log.info("\t+ newPosts: {}", newPosts);

        Posts savedPosts = this.postsRepository.<Posts>save(newPosts);
        log.info("\t+ savedPosts: {}", savedPosts);

//        ---

        Long updateId = savedPosts.getId();

        String expectedTitle = "expected title";
        String expectedContent = "expected content";
        String expectedAuthor = "Yoseph";

        PostsUpdateRequestDto requestDto =
                    PostsUpdateRequestDto.builder().
                            title(expectedTitle).
                            content(expectedContent).
                            author(expectedAuthor).
                            build();
        log.info("\t+ requestDto: {}", requestDto);

        String requestUrl = "http://localhost:" + this.port + "/api/v1/posts/" + updateId;
        log.info("\t+ requestUrl: {}", requestUrl);

        /**
         * ===================================
         * HttpEntity<T>
         * ===================================
         * Represents an `HTTP request or response` entity, consisting of headers and body.
         * Often used in combination with the `org.springframework.web.client.RestTemplate`,
         * like so:
         *
         *   HttpHeaders headers = new HttpHeaders();
         *   headers.setContentType(MediaType.TEXT_PLAIN);
         *
         *   HttpEntity<String> entity = new HttpEntity<>("Hello World", headers);
         *   URI location = template.postForLocation("https://example.com", entity);
         *
         * or
         *
         *   HttpEntity<String> entity = template.getForEntity("https://example.com", String.class);
         *
         *   String body = entity.getBody();
         *   MediaType contentType = entity.getHeaders().getContentType();
         *
         * Can also be used in Spring MVC, as a return value from a `@Controller` method:
         *
         *   @GetMapping("/handle")
         *   public HttpEntity<String> handle() {
         *     HttpHeaders responseHeaders = new HttpHeaders();
         *     responseHeaders.set("MyResponseHeader", "MyValue");
         *
         *     return new HttpEntity<>("Hello World", responseHeaders);
         *   } // handle
         */

        HttpEntity<PostsUpdateRequestDto> requestEntity = new HttpEntity<>(requestDto);
        log.info("\t+ requestEntity: {}", requestEntity);

        /**
         * ===================================
         * in the `TestRestTemplate` class :
         * ===================================
         *  public <T> ResponseEntity<T> exchange(
         *      String url, HttpMethod method, HttpEntity<?> requestEntity,
         * 		Class<T> responseType, Object... urlVariables
         * 	)
         * ===================================
         *
         * 1. Execute the `HTTP method` to the given URI template,
         * 2. writing the given request entity to the request,
         * 3. and returns the response as `ResponseEntity`.
         *
         * URI Template variables are expanded using the given URI variables, if any.
         *
         * Params:
         *  url : the URL method – the HTTP method (GET, POST, etc.)
         *  requestEntity : the entity (headers and/or body) to write to the request, may be null
         *  responseType : the type of the return value
         *  urlVariables : the variables to expand in the template
         *
         * Returns: the response as entity
         *
         */
        ResponseEntity<Long> responseEntity =
                this.testRestTemplate.exchange(requestUrl, HttpMethod.PUT, requestEntity, Long.class);
        log.info("\t+ responseEntity: {}", responseEntity);

//        ---

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Posts> postsList = this.postsRepository.findAll();

        assertThat(postsList.get(0).getTitle()).isEqualTo(expectedTitle);
        assertThat(postsList.get(0).getContent()).isEqualTo(expectedContent);
    } // testUpdatePosts

    @AfterAll
    void afterAll() {
        log.trace("afterAll() invoked.");

        this.postsRepository.deleteAll();
    } // afterAll

} // end class
