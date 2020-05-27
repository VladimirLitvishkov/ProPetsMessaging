package propets.dto.messaging;

import java.util.List;

import lombok.Getter;

@Getter
public class PostRequestDto {

	String text;
	List<String> images;
	String userName;
	String avatar;

}
