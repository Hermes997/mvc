package ryu.assign.mvc.controller;

import static org.mockito.ArgumentMatchers.contains;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import ryu.assign.mvc.model.FreePost;
import ryu.assign.mvc.model.UploadList;
import ryu.assign.mvc.service.PostProcessing;


@RunWith(SpringRunner.class)
@WebMvcTest(PostController.class)
class PostControllerTest {
	
	@Autowired
    private Validator validatorInjected;
	
	
	@Autowired
	UploadList uploadList;
	
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	FreePost freePost;
	
	@Autowired
	PostProcessing postprocessing;

	
	@Test
	public void testPostProcessingSuccess() throws Exception {
		
		freePost.setTitle("title999");
		freePost.setContents("contents999");
		freePost.setPublisher("ryu999");
		
		 String content = objectMapper.writeValueAsString(freePost);
		 
		 mockMvc.perform(post("/free")
			.content(content)
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().is3xxRedirection())
			.andDo(print());
	  }
	
	
	@Test
	public void testPostProcessingFail()  throws Exception {
		
		freePost.setId(999);
		freePost.setTitle("title999");
		freePost.setContents("contents999");
		freePost.setPublisher("ryu   ");
		freePost.setUploadTime("22-09-04 14:20:35");
		
		 String content = objectMapper.writeValueAsString(freePost);

		 mockMvc.perform(post("/free")
			.content(content)
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().is4xxClientError())
			.andDo(print());
	  }
	
	
	@Test
    void PublisherTestSuccess() {

		FreePost request = new FreePost(999, "title999", "contents999", "ryu999", "22-09-04 14:20:35");

        // when
        Set<ConstraintViolation<FreePost>> validate = validatorInjected.validate(request);

        // then
        Iterator<ConstraintViolation<FreePost>> iterator = validate.iterator();
        List<String> messages = new ArrayList<>();
        while (iterator.hasNext()) {
            ConstraintViolation<FreePost> next = iterator.next();
            messages.add(next.getMessage());
            System.out.println("message = " + next.getMessage());
        }

        Assertions.assertThat(messages).isNotIn(contains("작성자는 빈 공백을 포함하지 않아야 합니다"));
    }
	
	
	@Test
    void PublisherTestFail() {

		FreePost request = new FreePost(999, "title999", "contents999", "ryu   ", "22-09-04 14:20:35");

        // when
        Set<ConstraintViolation<FreePost>> validate = validatorInjected.validate(request);

        // then
        Iterator<ConstraintViolation<FreePost>> iterator = validate.iterator();
        List<String> messages = new ArrayList<>();
        while (iterator.hasNext()) {
            ConstraintViolation<FreePost> next = iterator.next();
            messages.add(next.getMessage());
            System.out.println("message = " + next.getMessage());
        }

        Assertions.assertThat(messages).contains("작성자는 빈 공백을 포함하지 않아야 합니다");
    }

	
	@Test
    void TitleContentsTestSuccess() {

		FreePost request = new FreePost(999, "  ", "", "ryu999", "22-09-04 14:20:35");

        // when
        Set<ConstraintViolation<FreePost>> validate = validatorInjected.validate(request);

        // then
        Iterator<ConstraintViolation<FreePost>> iterator = validate.iterator();
        List<String> messages = new ArrayList<>();
        while (iterator.hasNext()) {
            ConstraintViolation<FreePost> next = iterator.next();
            messages.add(next.getMessage());
            System.out.println("message = " + next.getMessage());
        }

        Assertions.assertThat(messages).contains("제목은 빈칸으로 할 수 없습니다", "내용은 한 글자 이상입니다");
    }
	
	@Test
    void TitleContentsTestFail() {

		FreePost request = new FreePost(999, "title999", "contents999", "ryu999", "22-09-04 14:20:35");

        // when
        Set<ConstraintViolation<FreePost>> validate = validatorInjected.validate(request);

        // then
        Iterator<ConstraintViolation<FreePost>> iterator = validate.iterator();
        List<String> messages = new ArrayList<>();
        while (iterator.hasNext()) {
            ConstraintViolation<FreePost> next = iterator.next();
            messages.add(next.getMessage());
            System.out.println("message = " + next.getMessage());
        }

        Assertions.assertThat(messages).isNotIn(contains("제목은 빈칸으로 할 수 없습니다"));
        Assertions.assertThat(messages).isNotIn(contains("내용은 한 글자 이상입니다"));
    }
    
}
