package org.zerock.myapp.seq_16.persistence;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Timeout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.test.web.servlet.MockMvc;
import org.zerock.myapp.seq_15.query.annotation.QueryRepository;
import org.zerock.myapp.seq_6.domain.Board;
import org.zerock.myapp.util.RandomNumberGenerator;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)

@AutoConfigureMockMvc

@SpringBootTest(
		webEnvironment = WebEnvironment.MOCK,
//		webEnvironment = WebEnvironment.DEFINED_PORT,
		
//		properties = "spring.jpa.hibernate.ddl-auto=create"
		properties = "spring.jpa.hibernate.ddl-auto=update"
)
public class QueryRepositoryTests {
	@Autowired(required = false) private MockMvc mockMvc;
	@Autowired(required = true)  private QueryRepository queryRepo;
	
	
	@BeforeAll
	void beforeAll() {
		log.trace("beforeAll() invoked.");
		log.info("  + this.mockMvc: {}", this.mockMvc);
		log.info("  + this.queryRepo: {}", this.queryRepo);
	} // beforeAll
	
	
//	@Disabled
	@Order(1)
	@Test
//	@RepeatedTest(1)
	@DisplayName("1. testFindByJPQLWithQueryAnnotation")
	@Timeout(value=1L, unit=TimeUnit.MINUTES)
	void testFindByJPQLWithQueryAnnotation() {
		log.trace("testFindByJPQLWithQueryAnnotation() invoked.");
		
		// ------------
		String titlePattern = "%tle_7%";
		
		List<Board> foundList = this.queryRepo.findByJPQLWithLocationBasedParameters(titlePattern);		// OK
		log.info("  + foundList: {}", foundList.size());

		// ------------
		foundList.forEach(log::info);
	} // testFindByJPQLWithQueryAnnotation
	
	
//	@Disabled
	@Order(2)
	@Test
//	@RepeatedTest(1)
	@DisplayName("2. testFindByJPQLWithNameBasedParameters")
	@Timeout(value=1L, unit=TimeUnit.MINUTES)
	void testFindByJPQLWithNameBasedParameters() {
		log.trace("testFindByJPQLWithNameBasedParameters() invoked.");
		
		// ------------
		String titlePattern = "%tle_3%";
		
		List<Board> foundList = this.queryRepo.findByJPQLWithNameBasedParameters(titlePattern);			// OK
		log.info("  + foundList: {}", foundList.size());

		// ------------
		foundList.forEach(log::info);
	}	// testFindByJPQLWithNameBasedParameters
	
	
//	@Disabled
	@Order(3)
	@Test
//	@RepeatedTest(1)
	@DisplayName("3. testFindByJPQLWithNameBasedParametersAndParamAnnotation")
	@Timeout(value=1L, unit=TimeUnit.MINUTES)
	void testFindByJPQLWithNameBasedParametersAndParamAnnotation() {
		log.trace("testFindByJPQLWithNameBasedParametersAndParamAnnotation() invoked.");
		
		// ------------
		String pattern = "Title_5%";
		
		List<Board> foundList = this.queryRepo.findByJPQLWithNameBasedParametersAndParamAnnotation(pattern);	// OK
		log.info("  + foundList: {}", foundList.size());

		// ------------
		foundList.forEach(log::info);
	}	// testFindByJPQLWithNameBasedParametersAndParamAnnotation
	
	
//	@Disabled
	@Order(4)
	@Test
//	@RepeatedTest(1)
	@DisplayName("4. testFindByJPQLWithSomeFields")
	@Timeout(value=1L, unit=TimeUnit.MINUTES)
	void testFindByJPQLWithSomeFields() {
		log.trace("testFindByJPQLWithSomeFields() invoked.");
		
		// ------------
		long criteriaStartSeq = 33, criteriaEndSeq = 77;
		
		Slice<Object[]> foundList = this.queryRepo.findByJPQLWithSomeFields(criteriaStartSeq, criteriaEndSeq);
		log.info("  + foundList: {}", foundList.getSize());

		// ------------
		foundList.forEach( arr -> log.info(Arrays.toString(arr)) );
	}	// testFindByJPQLWithSomeFields
	
	
//	@Disabled
	@Order(5)
	@Test
//	@RepeatedTest(1)
	@DisplayName("5. testUpdateDateOfEntities")
	@Timeout(value=1L, unit=TimeUnit.MINUTES)
	void testUpdateDateOfEntities() {
		log.trace("testUpdateDateOfEntities() invoked.");
		
		// ------------
		int updateCnt = RandomNumberGenerator.generateInt(1, 100);
		long[] randomSeqs = RandomNumberGenerator.generateLongArray(33, 1, 34);
		
		log.info("  + updateCnt: {}", updateCnt);
		log.info("  + randomSeqs: {}", Arrays.toString(randomSeqs));

		// ------------
		int affectedRows = this.queryRepo.updateDateOfEntities(updateCnt, randomSeqs);
		log.info("  + affectedRows: {}", affectedRows);
	}	// testUpdateDateOfEntities
	
	
//	@Disabled
	@Order(6)
	@Test
//	@RepeatedTest(1)
	@DisplayName("6. testFindByNativeSQLWithSomeFields")
	@Timeout(value=1L, unit=TimeUnit.MINUTES)
	void testFindByNativeSQLWithSomeFields() {
		log.trace("testFindByNativeSQLWithSomeFields() invoked.");
		
		// ------------
		String titlePattern = "%7%";
		
		Slice<Object[]> foundSlice = this.queryRepo.findByNativeSQLWithSomeFields(titlePattern);
		log.info("  + foundSlice: {}", foundSlice.getSize());

		// ------------
		foundSlice.forEach(arr -> log.info(Arrays.toString(arr)));
	}	// testFindByNativeSQLWithSomeFields
	
	
//	@Disabled
	@Order(7)
	@Test
//	@RepeatedTest(1)
	@DisplayName("7. testFindAllbyNativeSQLWithPagingAndSorting")
	@Timeout(value=1L, unit=TimeUnit.MINUTES)
	void testFindAllbyNativeSQLWithPagingAndSorting() {
		log.trace("testFindAllbyNativeSQLWithPagingAndSorting() invoked.");
		
		// ------------
//		Pageable paging = PageRequest.of(0, 20);																// OK
//		Pageable paging = PageRequest.of(0, 20, Sort.Direction.DESC, "board_id");		// OK
		Pageable paging = PageRequest.of(0, 20, Sort.Direction.DESC, "cnt");					// OK
		
		Page<Board> foundPage = this.queryRepo.findAllbyNativeSQLWithPagingAndSorting(paging);
		log.info("  + foundPage: {}", foundPage);

		// ------------
		foundPage.forEach(log::info);
	}	// testFindAllbyNativeSQLWithPagingAndSorting
	
} // end class


