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
		Post post = Post.builder().author(author).content(postRequestDto.getText())
				.imagesUrl(postRequestDto.getImagesUrl()).build();
		messagingRepository.save(post);
		return buildPostDto(post);
	}

	private PostDto buildPostDto(Post post) {
		return PostDto.builder().id(post.getId()).authorId(post.getAuthor()).text(post.getContent())
				.imagesUrl(post.getImagesUrl()).postDate(post.getPostDate()).build();
	}

	@Override
	public PostDto findPostById(Long id) {
		Post post = messagingRepository.findById(id).orElseThrow(() -> new PostIdNotFoundException());
		return buildPostDto(post);
	}

	@Override
	public PostDto deletePost(Long id) {
		Post post = messagingRepository.findById(id).orElseThrow(() -> new PostIdNotFoundException());
		PostDto postDto = buildPostDto(post);
		messagingRepository.deleteById(id);
		return postDto;
	}

	@Override
	public PostDto updatePost(Long id, PostRequestDto postRequestDto) {
		Post post = messagingRepository.findById(id).orElseThrow(() -> new PostIdNotFoundException());
		if (postRequestDto.getText() != null) {
			post.setContent(postRequestDto.getText());
		}
		if (postRequestDto.getImagesUrl() != null) {
			post.setImagesUrl(postRequestDto.getImagesUrl());
		}
		messagingRepository.save(post);
		return buildPostDto(post);
	}

	@Override
	public PostDto complain(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<PostDto> viewPosts(int page, int limit) {
		Pageable pageable = PageRequest.of(page, limit, Sort.Direction.DESC, "id");
		Page<PostDto> list = new PageImpl<PostDto>(messagingRepository.findAll(pageable).stream().map(this::buildPostDto).collect(Collectors.toList()));
//		List<PostDto> list = messagingRepository.findAll(pageable).stream().map(this::buildPostDto).collect(Collectors.toList());
		return list;
	}

}
