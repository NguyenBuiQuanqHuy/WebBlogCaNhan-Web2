package huynbq.ntu.web2.services;

import org.springframework.stereotype.Service;

import huynbq.ntu.web2.entities.User;

@Service
public interface UserService {
	boolean register(User user);
	User findByUsername(String username);
	public boolean checkPassword(String rawPassword, String encodedPassword);
}
