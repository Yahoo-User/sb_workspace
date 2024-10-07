package org.zerock.myapp.mybatis.beans;

import lombok.Cleanup;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.*;
import org.mybatis.spring.SqlSessionUtils;
import org.mybatis.spring.boot.autoconfigure.MybatisProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.zerock.myapp.mybatis.mapper.DateTimeMapper;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Objects;
import java.util.Properties;

import static org.assertj.core.api.Assertions.assertThat;


@Log4j2
@NoArgsConstructor

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

@AutoConfigureMockMvc
@SpringBootTest
class SupportedMyBatisBeansTests {
	@Setter(onMethod_= @Autowired)
	private MockMvc mockMvc;

	@Setter(onMethod_ = @Autowired)
	private SqlSessionFactory sqlSessionFactory;

	@Setter(onMethod_ = @Autowired)
	private SqlSession sqlSession;

	// Reference : https://github.com/mybatis/spring-boot-starter/blob/master/mybatis-spring-boot-autoconfigure/src/test/java/org/mybatis/spring/boot/autoconfigure/MybatisAutoConfigurationTest.java
	@Setter(onMethod_ = @Autowired)
	private MybatisProperties mybatisProperties;

	@Setter(onMethod_ = @Autowired)
	private DateTimeMapper dateTimeMapper;


//	@Disabled
	@Tag("fast")
	@Test
	@Order(1)
	@DisplayName("testMyBatisAutoRegisteredBeans")
	@Timeout(1L)
	void testMyBatisAutoRegisteredBeans() {
		log.trace("testMyBatisAutoRegisteredBeans() invoked.");

		log.info("\t1. this.mockMvc: {}", this.mockMvc);

		log.info("\t2. this.sqlSessionFactory: {}", this.sqlSessionFactory);
		log.info("\t3. this.sqlSession: {}", this.sqlSession);
		log.info("\t4. this.mybatisProperties: {}", this.mybatisProperties);

		log.info("\t5. this.dateTimeMapper: {}, type: {}", this.dateTimeMapper, this.dateTimeMapper.getClass().getName());
	} // testMyBatisAutoRegisteredBeans

	//	@Disabled
	@Tag("fast")
	@Test
	@Order(2)
	@DisplayName("testDateTimeMapper")
	@Timeout(1L)
	void testDateTimeMapper() {
		log.trace("testDateTimeMapper() invoked.");

		LocalDateTime now = this.dateTimeMapper.now();

		assertThat(now).isNotNull();
		log.info("\t+ now: {}", now);
	} // testDateTimeMapper

	//	@Disabled
	@Tag("fast")
	@Test
	@Order(3)
	@DisplayName("testSqlSessionUtils")
	@Timeout(1L)
	void testSqlSessionUtils() {
		log.trace("testSqlSessionUtils() invoked.");

		@Cleanup SqlSession sqlSession = SqlSessionUtils.getSqlSession(this.sqlSessionFactory);

		Objects.requireNonNull(sqlSession);
		log.info("\t+ sqlSession: {}", sqlSession);

		// To get the `DateTimeMapper` Mapper Proxy from SqlSession.
		DateTimeMapper mapper = sqlSession.getMapper(DateTimeMapper.class);
		log.info("\t+ mapper: {}", mapper);

		Objects.requireNonNull(mapper);
		log.info("\t+ now: {}", mapper.now());

//		sqlSession.close();
	} // testSqlSessionUtils

	//	@Disabled
	@Tag("fast")
	@Test
	@Order(4)
	@DisplayName("testMybatisProperties")
	@Timeout(1L)
	void testMybatisProperties() {
		log.trace("testMybatisProperties() invoked.");

		Properties properties = this.mybatisProperties.getConfigurationProperties();
		log.info("\t+1. properties: {}", properties);

		String configLocation = this.mybatisProperties.getConfigLocation();
		log.info("\t+2. configLocation: {}", configLocation);

		String[] mapperLocations = this.mybatisProperties.getMapperLocations();
		log.info("\t+3. mapperLocations: {}", Arrays.toString(mapperLocations));
	} // testMybatisProperties



} // end class
