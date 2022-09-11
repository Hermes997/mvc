package ryu.assign.mvc.model;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigurationBean {

	@Bean(name="freePost")
	FreePost superFreePost() {
		FreePost freePost = new FreePost();
		return freePost;
	}
	
	@Bean(name="uploadList")
	UploadList superUploadList() {
		UploadList uploadList = new UploadList();
		return uploadList;
	}
	
}
