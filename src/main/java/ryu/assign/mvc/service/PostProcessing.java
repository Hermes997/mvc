package ryu.assign.mvc.service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import ryu.assign.mvc.model.FreePost;

@Service
@ComponentScan("ryu.assign.mvc")
public class PostProcessing extends Processing{

	//요청에 의한 의존성 주입
	public PostProcessing (FreePost freePost, HttpServletRequest request, HttpServletResponse response) {
		
		super(freePost, request, response);
		
	}
	
	@Override
	public void service () throws IOException {

		freePost.setUploadTime(dateTime.format(formatter));
		
		uploadList.addPost(freePost);
		response.sendRedirect(redirect);
		
	}
	
	
}
