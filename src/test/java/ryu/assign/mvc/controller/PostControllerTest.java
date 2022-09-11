package ryu.assign.mvc.controller;

import static org.mockito.ArgumentMatchers.contains;

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
import org.springframework.test.context.junit4.SpringRunner;

import ryu.assign.mvc.model.FreePost;


@RunWith(SpringRunner.class)
@WebMvcTest(PostController.class)
class PostControllerTest {
	
	@Autowired
    private Validator validatorInjected;
	
	/*
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;
	
	@Test
	public void testPostProcessingSuccess()  throws Exception {
		 String content = objectMapper.writeValueAsString(FreePost.builder()
				 .id(999).title("title999").contents("contents999").publisher("ryu999").uploadTime("22-09-04 14:20:35").build());

		 mockMvc.perform(post("/postprocessing")
			.content(content)
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andReturn();
	  }
	
	@Test
	public void testPostProcessingFail()  throws Exception {
		 String content = objectMapper.writeValueAsString(FreePost.builder()
				 .id(999).title("").contents("contents999").publisher("ryu???!!!").uploadTime("22-09-04 14:20:35").build());

		 mockMvc.perform(post("/postprocessing")
			.content(content)
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andReturn();
	  }
	*/
	
	@Test
    void PublisherTestSuccess() {

		FreePost request = new FreePost(999, "title999", "contents999", "ryu???", "22-09-04 14:20:35");

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

        Assertions.assertThat(messages).contains("작성자는 영문또는 숫자입니다");
    }
	
	@Test
    void PublisherTestFail() {

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

        Assertions.assertThat(messages).isNotIn(contains("작성자는 영문또는 숫자입니다"));
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
