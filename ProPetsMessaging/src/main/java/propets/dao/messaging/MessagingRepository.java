package propets.dao.messaging;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
//import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;

import propets.model.messaging.Post;

public interface MessagingRepository extends MongoRepository<Post, String> {

	Page<Post> findAll(Pageable pageable);
}
