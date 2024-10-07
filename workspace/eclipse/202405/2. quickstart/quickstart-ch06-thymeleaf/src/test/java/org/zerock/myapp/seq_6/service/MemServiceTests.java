package org.zerock.myapp.seq_6.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.zerock.myapp.common.CommonJUnitTestCallbacks;
import org.zerock.myapp.seq_4.domain.Member;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@SpringBootTest(
		webEnvironment = WebEnvironment.MOCK, 
//		webEnvironment = WebEnvironment.DEFINED_PORT,
//		properties = "spring.jpa.hibernate.ddl-auto=create"
		properties = "spring.jpa.hibernate.ddl-auto=update"
)
public class MemServiceTests extends CommonJUnitTestCallbacks {
	@Autowired(required = true) @Qualifier("memberService")
	private MemberService service;

	
	@BeforeAll
	public
	void beforeAll() {
		super.beforeAll();
		log.trace("beforeAll() invoked.");
		log.info("  + this.service: {}", this.service);
	} // beforeAll

	
	
//	@Disabled
	@Order(1)
	@Test
//	@RepeatedTest(1)
	@DisplayName("testFindById")
	@Timeout(value=1L, unit=TimeUnit.MINUTES)
	void testFindById() {
		log.trace("testFindById() invoked.");
		
		Long searchId = 1L;
		Member foundMember = this.service.findById(searchId);
		
		assertNotNull(foundMember);
		log.info("  + foundMember: {}", foundMember);
	} // testFindById
	
//	@Disabled
	@Order(2)
	@Test
//	@RepeatedTest(1)
	@DisplayName("testFindByName")
	@Timeout(value=1L, unit=TimeUnit.MINUTES)
	void testFindByName() {
		log.trace("testFindByName() invoked.");
		
		String searchName = "Yoseph";
		Member foundMember = this.service.findByName(searchName);
		
		assertNotNull(foundMember);
		log.info("  + foundMember: {}", foundMember);
	} // testFindByName
	
	

} // end class


