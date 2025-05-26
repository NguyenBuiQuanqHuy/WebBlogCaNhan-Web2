package huynbq.ntu.web2.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import huynbq.ntu.web2.entities.Comment;
import huynbq.ntu.web2.entities.Post;
@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer>{
	List<Comment> findByPostOrderByCreatedatAsc(Post post);
}
