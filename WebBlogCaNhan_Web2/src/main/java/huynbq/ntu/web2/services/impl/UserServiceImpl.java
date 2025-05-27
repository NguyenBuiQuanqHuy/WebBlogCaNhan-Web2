package huynbq.ntu.web2.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import huynbq.ntu.web2.entities.User;
import huynbq.ntu.web2.repositories.UserRepository;
import huynbq.ntu.web2.services.interf.UserService;
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
	public boolean checkLogin(String username, String rawPassword) {
		// TODO Auto-generated method stub
		User user = findByUsername(username);
        if (user == null) return false;

        return passwordEncoder.matches(rawPassword, user.getPassword());
	}
	@Override
	public boolean changePassword(String username, String oldPassword, String newPassword) {
		// TODO Auto-generated method stub
		 User user = userRepository.findByUsername(username);
	        if (user == null) {
	            return false; 
	        }

	        if (oldPassword != null) {
	            if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
	                return false; 
	            }
	        }

	        String encodedNewPassword = passwordEncoder.encode(newPassword);
	        user.setPassword(encodedNewPassword);

	        userRepository.save(user);
	        return true;
	}
	
	

}
