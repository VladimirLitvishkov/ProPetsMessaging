package propets.service.messaging;

import java.util.Set;

import org.springframework.data.domain.Page;

import propets.dto.messaging.PostDto;
import propets.dto.messaging.PostRequestDto;

public interface MessagingService {

	PostDto addPost(String author, PostRequestDto postRequestDto);

	PostDto findPostById(String id);

	PostDto deletePost(String id);

	PostDto updatePost(String id, PostRequestDto postRequestDto);
	
	PostDto complain(String id);
	
	Page<PostDto> viewPosts(int page, int limit);
	
	Set<PostDto> findPostsByAllId(Set<String> allId);

}
