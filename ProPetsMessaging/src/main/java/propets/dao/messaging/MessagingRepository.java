package propets.dao.messaging;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import propets.model.messaging.Post;

public interface MessagingRepository extends JpaRepository<Post, Long> {

	Page<Post> findAll(Pageable pageable);
}
