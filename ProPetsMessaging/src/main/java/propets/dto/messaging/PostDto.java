package propets.dto.messaging;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Singular;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@EqualsAndHashCode(of = { "id" })
public class PostDto {
	Long id;
	String authorId;
	String text;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	LocalDateTime postDate;
	@Singular("imagesUrl")
	List<String> imagesUrl;

}
