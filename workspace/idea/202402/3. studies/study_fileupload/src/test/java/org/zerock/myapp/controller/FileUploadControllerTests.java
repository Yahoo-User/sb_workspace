package org.zerock.myapp.controller;

import lombok.Cleanup;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.multipart.MultipartResolver;

import java.io.FileInputStream;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@Log4j2
@NoArgsConstructor

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

@AutoConfigureMockMvc
@SpringBootTest
class FileUploadControllerTests {
	@Setter(onMethod_ = @Autowired)
	private MockMvc mockMvc;

	@Setter(onMethod_ = @Autowired)
	private MultipartResolver multipartResolver;


	@BeforeAll
	void beforeAll() {
		log.trace("beforeAll() invoked.");

		assertThat(this.mockMvc).isNotNull();
		log.info("\t+1. this.mockMvc: {}", this.mockMvc);

		assertThat(this.multipartResolver).isNotNull();
		log.info("\t+2. this.multipartResolver: {}", this.multipartResolver);
	} // beforeAll

//	@Disabled
	@Tag("fast")
	@Test
	@Order(1)
	@DisplayName("testFileUpload")
	@Timeout(3L)
	void testFileUpload() throws Exception {
		log.trace("testFileUpload() invoked.");

		/*
		 * public void testAddContacts() throws Exception{
		 *     File f = new File("C:\\Users\\Public\\Pictures\\Sample Pictures\\Penguins.jpg");
		 *     System.out.println(f.isFile()+"  "+f.getName()+f.exists());
		 *
		 *     FileInputStream fi1 = new FileInputStream(f);
		 *     FileInputStream fi2 = new FileInputStream(new File("C:\\Users\\Public\\Pictures\\Sample Pictures\\Tulips.jpg"));
		 *
		 *     MockMultipartFile fstmp = new MockMultipartFile("upload", f.getName(), "multipart/form-data",fi1);
		 *     MockMultipartFile secmp = new MockMultipartFile("upload", "Tulips.jpg","multipart/form-data",fi2);
		 *
		 *     MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		 *
		 *     mockMvc.perform(
		 * 			MockMvcRequestBuilders.fileUpload("/AddContacts").file(fstmp).file(secmp).
		 *              .param("name","abc").param("email","abc@gmail.com").param("phone", "1234567890")
		 *     ).andExpect(status().isOk());
		 * }
		 */

		Path path1 = Path.of("C:/temp/TTT.http");
		Path path2 = Path.of("C:/temp/REST.http");
		Path path3 = Path.of("C:/temp/RestExample.http");

		@Cleanup FileInputStream fis1 = new FileInputStream(path1.toFile());
		@Cleanup FileInputStream fis2 = new FileInputStream(path2.toFile());
		@Cleanup FileInputStream fis3 = new FileInputStream(path3.toFile());

		MockMultipartFile mf1 = new MockMultipartFile("files", path1.toFile().getName(), "multipart/form-data", fis1);
		MockMultipartFile mf2 = new MockMultipartFile("files", path2.toFile().getName(), "multipart/form-data", fis2);
		MockMultipartFile mf3 = new MockMultipartFile("files", path3.toFile().getName(), "multipart/form-data", fis3);

		this.mockMvc.
			perform(
				// 'fileUpload(java.lang.String, java.lang.Object...)' is * deprecated *.
//				MockMvcRequestBuilders.fileUpload("/fileUpload").file(mf1).file(mf2).file(mf3)

				MockMvcRequestBuilders.multipart("/fileUpload").file(mf1).file(mf2).file(mf3)
			).andExpect(status().isOk());
	} // contextLoads

} // end class
