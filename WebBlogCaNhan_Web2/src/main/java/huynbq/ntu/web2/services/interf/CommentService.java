package huynbq.ntu.web2.services.interf;

import java.util.List;

import org.springframework.stereotype.Service;

import huynbq.ntu.web2.entities.Comment;
import huynbq.ntu.web2.entities.Post;
import huynbq.ntu.web2.entities.User;

@Service
public interface CommentService {
	 public List<Comment> getCommentsByPost(Post post);
	 public void save(Post post, User user, String content);
	 public Comment findById(Integer commentID);
	 public void delete(int commentId);
}
