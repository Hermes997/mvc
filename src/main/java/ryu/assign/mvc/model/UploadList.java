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
		
		if(postItems.containsKey(id)) {
			postItems.replace(id, post);
			System.out.println("수정되었습니다");
		} else {
			System.out.println("해당 아이디는 존재하지 않습니다");
		}
		
	}
	
	public void deletePost(int id) {
		
		if(postItems.containsKey(id)) {
			postItems.remove(id);
			System.out.println("삭제되었습니다");
		} else {
			System.out.println("해당 아이디는 존재하지 않습니다");
		}
		
	}
	
	public Collection<FreePost> printAll() {
		
		return postItems.values();
		
	}

	
	public FreePost selFreePost(int id) {
		return postItems.get(id);
	}
}
