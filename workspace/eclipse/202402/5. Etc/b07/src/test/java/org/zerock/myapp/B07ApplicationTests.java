package org.zerock.myapp;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Timeout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.zerock.myapp.dao.MemberRepository;
import org.zerock.myapp.domain.Member;
import org.zerock.myapp.domain.RoleType;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)

@SpringBootTest(webEnvironment=WebEnvironment.MOCK)
class B07ApplicationTests {
	
	@Autowired
	private MemberRepository dao;
	
	@Autowired
	private PasswordEncoder pwEncoder;
	
	
	@BeforeAll
	static void beforeAll() {
		log.debug("beforeAll() invoked.");
		
	} // beforeAll
	
	@AfterAll
	static void afterAll() {
		log.debug("afterAll() invoked.");
		
	} // afterAll
	
	@BeforeEach
	void beforeEach() {
		log.debug("beforeEach() invoked.");
		
		assert this.dao != null;
		log.info("\t+ dao: {}, type: {}", this.dao, this.dao.getClass().getName());
		
		Objects.requireNonNull(this.pwEncoder);
		log.info("\t+ pwEncoder: {}", this.pwEncoder);
	} // beforeEach
	
	@AfterEach
	void afterEach() {
		log.debug("afterEach() invoked.");
		
	} // afterEach
	
//	========================

	@Test
	@Order(1)
	@DisplayName("insertNewAdmin")
	@Timeout(value=1000, unit=TimeUnit.MILLISECONDS)
	void insertNewAdmin() {
		log.debug("insertNewAdmin() invoked.");
		
		for(int i=3; i<=7;i++) {
			Member admin = new Member();
			
			admin.setId("admin"+i);
			admin.setPassword(this.pwEncoder.encode("admin"+i));
			admin.setName("NAME_"+i);
			admin.setRole(RoleType.ROLE_ADMIN);
			admin.setEnabled("1");
			
			this.dao.save(admin);
			log.info("\t+ new admin: {}", admin);
		} // for
		
	} // insertNewAdmin
	
	
	@Test
	@Order(2)
	@DisplayName("insertNewMember")
	@Timeout(value=1000, unit=TimeUnit.MILLISECONDS)
	void insertNewMember() {
		log.debug("insertNewMember() invoked.");
		
		int i=1;

		Member member = new Member();
		
		member.setId("member"+i);
		member.setPassword(this.pwEncoder.encode("member"+i));
		member.setName("NAME_"+i);
		member.setRole(RoleType.ROLE_MEMBER);
		member.setEnabled("1");
		
		this.dao.save(member);
		log.info("\t+ new member: {}", member);
	} // insertNewMember
	
	
	@Test
	@Order(3)
	@DisplayName("insertNewManager")
	@Timeout(value=1000, unit=TimeUnit.MILLISECONDS)
	void insertNewManager() {
		log.debug("insetNewManager() invoked.");
		
		Member manager = new Member();
		
		int i = 1;
		
		manager.setId("manager"+i);
		manager.setPassword(this.pwEncoder.encode("manager"+i));
		manager.setName("NAME_"+i);
		manager.setRole(RoleType.ROLE_MANAGER);
		manager.setEnabled("1");
		
		this.dao.save(manager);
		log.info("\t+ manager: {}", manager);
	} // insertNewManager

} // end class
