package ryu.assign.mvc.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import lombok.Data;
import ryu.assign.mvc.model.FreePost;
import ryu.assign.mvc.model.UploadList;

@Service
@Data
@ComponentScan("ryu.assign.mvc")
public class PostProcessing {
	
	//포스트 객체는 요청에 의해 의존성 주입
	FreePost freePost;
	
	HttpServletRequest request;
	HttpServletResponse response;
	
	//리스트는 스프링에서 관리
	@Autowired
	UploadList uploadList;
	
	static String redirect = "/home";
	
	//요청에 의한 의존성 주입
	public PostProcessing (FreePost freePost, HttpServletRequest request, HttpServletResponse response) {
		
		freePost = this.freePost;
		request = this.request;
		response = this.response;
		
		
	}

	public void service () throws IOException {
		
		//new를 쓰지 않고 날짜 데이터를 불러오는 방식
		LocalDateTime dateTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy-MM-dd HH:mm:ss");
		
		//id는 자바클래스에서 부여
		freePost.setTitle(request.getParameter("title"));
		freePost.setContents(request.getParameter("contents"));
		freePost.setPublisher(request.getParameter("publisher"));
		freePost.setUploadTime(dateTime.format(formatter));
		
		uploadList.addPost(freePost);
		response.sendRedirect(redirect);
		
	}
	
	
}
