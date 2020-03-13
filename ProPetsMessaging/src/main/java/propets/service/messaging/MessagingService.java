package propets.service.messaging;

import org.springframework.data.domain.Page;

import propets.dto.messaging.PostDto;
import propets.dto.messaging.PostRequestDto;

public interface MessagingService {

	PostDto addPost(String author, PostRequestDto postRequestDto);

	PostDto findPostById(Long id);

	PostDto deletePost(Long id);

	PostDto updatePost(Long id, PostRequestDto postRequestDto);
	
	PostDto complain(Long id);
	
	Page<PostDto> viewPosts(int page, int limit);

}
