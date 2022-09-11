package ryu.assign.mvc.model;



import java.util.Collection;
import java.util.HashMap;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UploadList {
	
	private static HashMap<Integer, FreePost> postItems = new HashMap<Integer, FreePost>();
	
	private static int postNum = 1;
	
	public void addPost(FreePost post) {
		
		post.setId(postNum);
		postItems.put(postNum, post);
		postNum++;
	}
	
	public void changePost(int id, FreePost post) {
		
		postItems.replace(id, post);
		
	}
	
	public Collection<FreePost> printAll() {
		
		return postItems.values();
		
	}
	
	
	/*
	public Collection<FreePost> printAll() {
		System.out.println(postItems.size());
		System.out.println(postItems.get(1));
		System.out.println(postItems);
		return postItems.values();
		
	}*/
	
	
	public FreePost selFreePost(int id) {
		return postItems.get(id);
	}
}
