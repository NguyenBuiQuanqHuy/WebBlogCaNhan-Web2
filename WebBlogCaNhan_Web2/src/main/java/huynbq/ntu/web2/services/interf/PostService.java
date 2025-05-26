package huynbq.ntu.web2.services.interf;

import java.util.List;

import org.springframework.stereotype.Service;

import huynbq.ntu.web2.entities.Mode;
import huynbq.ntu.web2.entities.Post;
import huynbq.ntu.web2.entities.User;
@Service
public interface PostService {
	public Post createPost(String title, String content, String username, int categoryId, Mode mode);
	public List<Post> getRandomPublicPosts();
	public List<Post> searchPosts(String keyword, Integer categoryId);
	public List<Post> getAllPosts();
	public List<Post> getPostsByUser(String username);
	public void deletePost(int postID);
	public Post findPost(Integer postID);
	public void updatePost(int id, String title, String content, int categoryId, Mode mode);
}
