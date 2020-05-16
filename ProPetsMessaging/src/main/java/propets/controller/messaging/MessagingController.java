package propets.controller.messaging;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import propets.dto.messaging.PostDto;
import propets.dto.messaging.PostRequestDto;
import propets.service.messaging.MessagingService;

@RestController
@RequestMapping("/{lang}/v1")
@CrossOrigin(origins = "*", exposedHeaders = { "X-token" })
public class MessagingController {

	@Autowired
	MessagingService messagingService;

	@PostMapping
	public PostDto addPost(Principal author, @RequestBody PostRequestDto postRequestDto) {
		return messagingService.addPost(author.getName(), postRequestDto);
	}

	@GetMapping("/{id}")
	public PostDto findPostById(@PathVariable Long id) {
		return messagingService.findPostById(id);
	}

	@DeleteMapping("/{id}")
	public PostDto deletePost(@PathVariable Long id) {
		return messagingService.deletePost(id);
	}

	@PutMapping("/{id}")
	public PostDto updatePost(@PathVariable Long id, @RequestBody PostRequestDto postRequestDto) {
		return messagingService.updatePost(id, postRequestDto);
	}

	@GetMapping("/complain/{id}")
	public PostDto complain(@PathVariable Long id) {
		return messagingService.complain(id);
	}

	@GetMapping("/posts")
	public Page<PostDto> viewPosts(@RequestParam int page, @RequestParam int size) {
		return messagingService.viewPosts(page, size);
	}

}
