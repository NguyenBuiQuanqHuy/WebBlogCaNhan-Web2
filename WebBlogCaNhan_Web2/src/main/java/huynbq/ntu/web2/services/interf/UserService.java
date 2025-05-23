package huynbq.ntu.web2.services.interf;

import org.springframework.stereotype.Service;

import huynbq.ntu.web2.entities.User;

@Service
public interface UserService {
	boolean register(User user);
	User findByUsername(String username);
	public boolean checkLogin(String username, String rawPassword);
}
