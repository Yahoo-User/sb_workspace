package org.zerock.myapp.properties;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.web.servlet.MockMvc;
import org.zerock.myapp.controller.BoardController;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertNotNull;


@Log4j2
@NoArgsConstructor

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

@AutoConfigureMockMvc
@SpringBootTest(
    // Properties in the form `key=value` that should be added to the Spring Environment
    // before the test runs.
    properties = {
        "author.name=Trinity",
        "author.age = 24",
        "author.nation = Korea"
    },
    // The component classes used to load the application context
    classes = BoardController.class
)
public class PropertiesTests {
    @Autowired private MockMvc mockMvc;
    @Autowired private Environment env;
    @Autowired private BoardController boardController;


    @BeforeAll
    void beforeAll() {
        log.trace("beforeAll() invoked.");

        assert this.mockMvc != null;
        log.info("\t+ this.mockMvc: {}", this.mockMvc);

        assertNotNull(this.env);
        log.info("\t+ this.env: {}", this.env);

        Objects.requireNonNull(this.boardController);
        log.info("\t+ this.boardController: {}", this.boardController);
    } // beforeAll

    //@Disabled
    @Tag("fast")
    @Test
    @Order(1)
    @DisplayName("testEnvironment")
    @Timeout(1L)
    void testEnvironment() {    // Test for using external `application.properties`
        log.trace("testEnvironment() invoked.");

        log.info("\t+ server.port = {}", this.env.getProperty("server.port"));
        log.info("\t+ server.address = {}", this.env.getProperty("server.address"));

        log.info("\t+ author.name = {}", this.env.getProperty("author.name"));
        log.info("\t+ author.age = {}", this.env.getProperty("author.age"));
        log.info("\t+ author.nation = {}", this.env.getProperty("author.nation"));
    } // testEnvironment

    //@Disabled
    @Tag("fast")
    @Test
    @Order(2)
    @DisplayName("testRedefineProperties")
    @Timeout(1L)
    void testRedefineProperties() {    // Test for using external `application.properties`
        log.trace("testRedefineProperties() invoked.");

        log.info("\t+ author.name = {}", this.env.getProperty("author.name"));
        log.info("\t+ author.age = {}", this.env.getProperty("author.age"));
        log.info("\t+ author.nation = {}", this.env.getProperty("author.nation"));
    } // testRedefineProperties

} // end class
