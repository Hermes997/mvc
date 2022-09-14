package ryu.assign.mvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import ryu.assign.mvc.model.FreePost;
import ryu.assign.mvc.service.PostProcessing;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.MOCK)
@ComponentScan("ryu.assign.mvc")
@AutoConfigureMockMvc
class MvcTrainingApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	FreePost freePost;
	
	@Autowired
	PostProcessing postprocessing;
	
	@Test
	public void testPostProcessingSuccess()  throws Exception {
		
		freePost.setId(999);
		freePost.setTitle("title999");
		freePost.setContents("contents999");
		freePost.setPublisher("ryu999");
		freePost.setUploadTime("22-09-04 14:20:35");
		
		 String content = objectMapper.writeValueAsString(freePost);
		 
		 System.out.println("contents : " + content);
		 
		 mockMvc.perform(post("/free")
			.content(content)
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andDo(print());
		 
	  }


}
