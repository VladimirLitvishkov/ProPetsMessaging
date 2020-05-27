package propets.service.messaging;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import propets.dao.messaging.MessagingRepository;
import propets.dto.messaging.PostDto;
import propets.dto.messaging.PostRequestDto;
import propets.exceptions.messaging.PostIdNotFoundException;
import propets.model.messaging.Post;

@Service
public class MessagingServiceImpl implements MessagingService {

	@Autowired
	MessagingRepository messagingRepository;

	@Override
	public PostDto addPost(String author, PostRequestDto postRequestDto) {
		Post post = Post.builder().userLogin(author).userName(postRequestDto.getUserName())
				.avatar(postRequestDto.getAvatar()).text(postRequestDto.getText()).images(postRequestDto.getImages())
				.build();
		messagingRepository.save(post);
		return buildPostDto(post);
	}

	private PostDto buildPostDto(Post post) {
		return PostDto.builder().id(post.getId()).userLogin(post.getUserLogin()).userName(post.getUserName())
				.avatar(post.getAvatar()).text(post.getText()).images(post.getImages())
				.datePost(post.getDatePost()).build();
	}

	@Override
	public PostDto findPostById(String id) {
		Post post = messagingRepository.findById(id).orElseThrow(() -> new PostIdNotFoundException());
		return buildPostDto(post);
	}

	@Override
	public PostDto deletePost(String id) {
		Post post = messagingRepository.findById(id).orElseThrow(() -> new PostIdNotFoundException());
		PostDto postDto = buildPostDto(post);
		messagingRepository.deleteById(id);
		return postDto;
	}

	@Override
	public PostDto updatePost(String id, PostRequestDto postRequestDto) {
		Post post = messagingRepository.findById(id).orElseThrow(() -> new PostIdNotFoundException());
		if (postRequestDto.getText() != null) {
			post.setText(postRequestDto.getText());
		}
		if (postRequestDto.getImages() != null) {
			post.setImages(postRequestDto.getImages());
		}
		messagingRepository.save(post);
		return buildPostDto(post);
	}

	@Override
	public PostDto complain(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<PostDto> viewPosts(int page, int limit) {
		Pageable pageable = PageRequest.of(page, limit, Sort.Direction.DESC, "id");
		Page<PostDto> list = new PageImpl<PostDto>(
				messagingRepository.findAll(pageable).stream().map(this::buildPostDto).collect(Collectors.toList()));
//		List<PostDto> list = messagingRepository.findAll(pageable).stream().map(this::buildPostDto).collect(Collectors.toList());
		return list;
	}

}
