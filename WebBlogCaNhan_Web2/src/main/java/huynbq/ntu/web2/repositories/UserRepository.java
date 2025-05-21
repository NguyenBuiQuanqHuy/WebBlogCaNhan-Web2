package huynbq.ntu.web2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import huynbq.ntu.web2.entities.User;
@Repository
public interface UserRepository extends JpaRepository<User, String>{
	 boolean existsByUsername(String username);
	 User findByUsername(String username);
}
