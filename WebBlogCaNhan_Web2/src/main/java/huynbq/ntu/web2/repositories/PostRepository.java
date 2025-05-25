package huynbq.ntu.web2.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import huynbq.ntu.web2.entities.Post;
@Repository
public interface PostRepository extends JpaRepository<Post, Integer>{
	 @Query("SELECT p FROM Post p WHERE p.mode = 'cong_khai' ORDER BY FUNCTION('RAND')")
	    List<Post> findRandomCongKhaiPosts();
	 @Query("SELECT p FROM Post p WHERE " +
		       "(:keyword IS NULL OR :keyword = '' OR " +
		       "LOWER(p.title) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
		       "OR LOWER(p.user.username) LIKE LOWER(CONCAT('%', :keyword, '%'))) " +
		       "AND (:categoryId IS NULL OR p.category.ID = :categoryId)")
		List<Post> searchByKeywordAndCategory(@Param("keyword") String keyword, @Param("categoryId") Integer categoryId);


}
