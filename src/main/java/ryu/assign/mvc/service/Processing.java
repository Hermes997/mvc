package ryu.assign.mvc.service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.Data;
import ryu.assign.mvc.model.FreePost;
import ryu.assign.mvc.model.UploadList;

@Data
@Service
public abstract class Processing {
	
	
	//포스트 객체는 요청에 의해 의존성 주입
	FreePost freePost;
	
	HttpServletRequest request;
	HttpServletResponse response;
	
	//리스트는 스프링에서 관리
	@Autowired
	UploadList uploadList;
	
	static String redirect = "/home";
	
	//요청에 의한 의존성 주입
	public Processing (FreePost freePost, HttpServletRequest request, HttpServletResponse response) {
		
		freePost = this.freePost;
		request = this.request;
		response = this.response;
		
	}
	
	
	public void service() throws IOException{};
	
}
