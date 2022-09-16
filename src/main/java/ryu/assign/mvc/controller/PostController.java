package ryu.assign.mvc.controller;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import ryu.assign.mvc.model.FreePost;
import ryu.assign.mvc.model.UploadList;
import ryu.assign.mvc.service.PostProcessing;
import ryu.assign.mvc.service.PutProcessing;


@ComponentScan("ryu.assign.mvc")
@Controller
public class PostController {
	
	@Autowired
	PostProcessing postProcessing;
	
	@Autowired
	PutProcessing putProcessing;
	
	@Autowired
	UploadList uploadList;
	
	@GetMapping("/jstest")
	public String jsTest() {
		
		return "/jstest";
	}
	
	@GetMapping("/home")
	public String home() {
		
		return "/home";
	}
	
	@GetMapping("/free")
	public @ResponseBody Collection<FreePost> viewList() {
		
		return uploadList.printAll();
	}
	
	@GetMapping("/free/{id}")
	public @ResponseBody FreePost viewListSel(@PathVariable int id) {
		
		return uploadList.selFreePost(id);
	}
	
	@GetMapping("/postform")
	public String postForm() {

		return "/postform";
	}
	
	@GetMapping("/changeform")
	public String changeForm() {

		return "/changeform";
	}
	
	//에러는 validation 영향을 받는 객체다음에 선언되어야함
	@PostMapping(value = "/free", consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody FreePost postProcessing(@RequestBody @Validated FreePost freePost, HttpServletRequest request, HttpServletResponse response) throws IOException {
			
		postProcessing.setFreePost(freePost);
		postProcessing.setRequest(request);
		postProcessing.setResponse(response);
		
		postProcessing.service();

		return freePost;
	}
	
	@PutMapping("/free")
	public @ResponseBody FreePost putProcessing(@RequestBody @Validated FreePost freePost, HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		putProcessing.setFreePost(freePost);
		putProcessing.setRequest(request);
		putProcessing.setResponse(response);
		
		putProcessing.service();
		
		return freePost;
	}
	
	@DeleteMapping("/free/{id}")
	public String deleteProcessing(@PathVariable int id, HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		uploadList.deletePost(id);
		
		return "/home";
	}
	
}
