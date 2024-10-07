package org.zerock.myapp.util;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.zerock.myapp.config.JdbcConfigurationProperties;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;


@Log4j2
@NoArgsConstructor

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

@AutoConfigureMockMvc
@SpringBootTest
class JdbcConnectionManagerTests {
	@Autowired private MockMvc mockMvc;
	@Autowired private JdbcConfigurationProperties jdbcConfProps;
	@Autowired private JdbcConnectionManager jdbcConnMgr;


	@BeforeAll
	void beforeAll() {
		log.trace("beforeAll() invoked.");

		assertThat(this.jdbcConfProps).isNotNull();
		log.info("\t+ this.jdbcConfProps: {}", this.jdbcConfProps);
		assertThat(this.jdbcConnMgr).isNotNull();
		log.info("\t+ this.jdbcConnMgr: {}", this.jdbcConnMgr);
	} // beforeAll

	//	@Disabled
	@Tag("fast")
	@Test
	@Order(1)
	@DisplayName("testJdbcConfigurationProperties")
	@Timeout(1L)
	void testJdbcConfigurationProperties() {
		log.trace("testJdbcConfigurationProperties() invoked.");

		log.info("\t+ driverClass: {}", this.jdbcConfProps.getDriverClass());
		log.info("\t+ url: {}", this.jdbcConfProps.getUrl());
		log.info("\t+ username: {}", this.jdbcConfProps.getUsername());
		log.info("\t+ password: {}", this.jdbcConfProps.getPassword());
	} // testJdbcConfigurationProperties

	//	@Disabled
	@Tag("fast")
	@Test
	@Order(2)
	@DisplayName("testJdbcConnectionManager")
	@Timeout(2L)
	void testJdbcConnectionManager() throws SQLException, ClassNotFoundException {
		log.trace("testJdbcConnectionManager() invoked.");

		Connection conn = this.jdbcConnMgr.getConnection();
		Objects.requireNonNull(conn);

		try (conn) {
			log.info("\t+ conn: {}", conn);
		} // try-with-resources
	} // testJdbcConnectionManager

} // end class


