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
	@Override
	public List<Post> searchPosts(String keyword, Integer categoryId) {
		// TODO Auto-generated method stub
		  return postRepository.searchByKeywordAndCategory(keyword, categoryId);
	}
	@Override
	public List<Post> getAllPosts() {
		// TODO Auto-generated method stub
		 return postRepository.findAll();
	}
	@Override
	public List<Post> getPostsByUser(String username) {
		// TODO Auto-generated method stub
		return postRepository.findPostsByUsername(username);
	}
	@Override
	public void deletePost(int postID) {
		// TODO Auto-generated method stub
		postRepository.deleteById(postID);
	}
	@Override
	public Post findPost(Integer postID) {
		// TODO Auto-generated method stub
		return postRepository.findById(postID)
                .orElseThrow();
	}
	@Override
	public void updatePost(int id, String title, String content, int categoryId, Mode mode) {
		// TODO Auto-generated method stub
		Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy bài viết với ID = " + id));

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy chủ đề với ID = " + categoryId));

        post.setTitle(title);
        post.setContent(content);
        post.setMode(mode);
        post.setCategory(category);

        postRepository.save(post);
	}
	
}
