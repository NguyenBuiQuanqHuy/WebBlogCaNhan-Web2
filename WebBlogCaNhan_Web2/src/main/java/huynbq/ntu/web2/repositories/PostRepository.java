package huynbq.ntu.web2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import huynbq.ntu.web2.entities.Post;
@Repository
public interface PostRepository extends JpaRepository<Post, Integer>{

}
