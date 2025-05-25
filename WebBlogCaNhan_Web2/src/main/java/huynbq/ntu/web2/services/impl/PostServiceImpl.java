package huynbq.ntu.web2.services.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import huynbq.ntu.web2.entities.Category;
import huynbq.ntu.web2.entities.Mode;
import huynbq.ntu.web2.entities.Post;
import huynbq.ntu.web2.entities.User;
import huynbq.ntu.web2.repositories.CategoryRepository;
import huynbq.ntu.web2.repositories.PostRepository;
import huynbq.ntu.web2.repositories.UserRepository;
import huynbq.ntu.web2.services.interf.PostService;
@Service
public class PostServiceImpl implements PostService{
	@Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private CategoryRepository categoryRepository;
	@Override
	public Post createPost(String title, String content, String username, int categoryId, Mode mode) {
		// TODO Auto-generated method stub
		 User user = userRepository.findByUsername(username);
	     Category category = categoryRepository.findById(categoryId).orElse(null);

	        if (user == null || category == null) {
	            return null; // hoặc throw exception
	        }

	        Post post = new Post();
	        post.setTitle(title);
	        post.setContent(content);
	        post.setCreated_at(LocalDateTime.now());
	        post.setUser(user);
	        post.setCategory(category);
	        post.setMode(mode);

	        return postRepository.save(post);
	}
	@Override
	public List<Post> getRandomPublicPosts() {
		// TODO Auto-generated method stub
		 return postRepository.findRandomCongKhaiPosts();
	}

}
