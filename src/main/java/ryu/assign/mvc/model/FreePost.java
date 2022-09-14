package ryu.assign.mvc.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ryu.assign.mvc.validation.PublisherCheck;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FreePost implements Post{
	
	//int 대신 Integer
	private int id;
	
	@NotBlank(message = "제목은 빈칸으로 할 수 없습니다")
	private String title;
	
	@NotEmpty(message = "내용은 한 글자 이상입니다")
	private String contents;
	
	@PublisherCheck(message = "작성자는 영문또는 숫자입니다")
	private String publisher;
	
	private String uploadTime;
	
	
}
