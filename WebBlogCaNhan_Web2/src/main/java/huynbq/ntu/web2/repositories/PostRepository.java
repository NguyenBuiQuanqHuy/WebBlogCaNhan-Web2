package huynbq.ntu.web2.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import huynbq.ntu.web2.entities.Post;
@Repository
public interface PostRepository extends JpaRepository<Post, Integer>{
	 @Query("SELECT p FROM Post p WHERE p.mode = 'cong_khai' ORDER BY FUNCTION('RAND')")
	    List<Post> findRandomCongKhaiPosts();
}
