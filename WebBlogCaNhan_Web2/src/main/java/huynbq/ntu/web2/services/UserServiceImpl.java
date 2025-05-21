package huynbq.ntu.web2.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import huynbq.ntu.web2.entities.User;
import huynbq.ntu.web2.repositories.UserRepository;
@Service
public class UserServiceImpl implements UserService{
	 @Autowired
	    private UserRepository userRepository;
	 private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	@Override
	public boolean register(User user) {
		// TODO Auto-generated method stub
		 if (userRepository.existsByUsername(user.getUsername())) {
	            return false;
	        }


	        user.setPassword(passwordEncoder.encode(user.getPassword()));

	        userRepository.save(user);
	        return true;
	}
	@Override
	public User findByUsername(String username) {
		// TODO Auto-generated method stub
		 return userRepository.findByUsername(username);
	}
	@Override
	public boolean checkPassword(String rawPassword, String encodedPassword) {
		// TODO Auto-generated method stub
		return passwordEncoder.matches(rawPassword, encodedPassword);
	}

}
