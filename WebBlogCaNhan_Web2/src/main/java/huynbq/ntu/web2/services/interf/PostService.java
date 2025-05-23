package huynbq.ntu.web2.services.interf;

import org.springframework.stereotype.Service;

import huynbq.ntu.web2.entities.Mode;
import huynbq.ntu.web2.entities.Post;
@Service
public interface PostService {
	public Post createPost(String title, String content, String username, int categoryId, Mode mode);
}
