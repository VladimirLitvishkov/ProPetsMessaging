package propets.model.messaging;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Singular;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = { "id" })
@Builder

@Entity

public class Post {
	@Id
	@GeneratedValue
	Long id;
	String content;
	String author;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Default
	LocalDateTime postDate = LocalDateTime.now();
	@ElementCollection
	@Singular("imagesUrl")
	List<String> imagesUrl;
	
//	public void addImages (List<String> images) {
//		imagesUrl.addAll(images);
//	}

}
