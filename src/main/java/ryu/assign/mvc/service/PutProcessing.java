package ryu.assign.mvc.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import ryu.assign.mvc.model.FreePost;

@Service
@ComponentScan("ryu.assign.mvc")
public class PutProcessing extends Processing{
	
	public PutProcessing (FreePost freePost, HttpServletRequest request, HttpServletResponse response) {
		
		super(freePost, request, response);
		
	}
	
	@Override
	public void service() throws IOException {
		
		//new를 쓰지 않고 날짜 데이터를 불러오는 방식
		LocalDateTime dateTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy-MM-dd HH:mm:ss");
		
		freePost.setId(Integer.parseInt(request.getParameter("id")));
		freePost.setTitle(request.getParameter("title"));
		freePost.setContents(request.getParameter("contents"));
		freePost.setPublisher(request.getParameter("publisher"));
		freePost.setUploadTime(dateTime.format(formatter));
		
		uploadList.changePost(freePost.getId(), freePost);
		response.sendRedirect(redirect);
		
	}
	
	
}
