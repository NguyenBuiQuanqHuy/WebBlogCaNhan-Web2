package huynbq.ntu.web2.services.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import huynbq.ntu.web2.entities.Comment;
import huynbq.ntu.web2.entities.Post;
import huynbq.ntu.web2.entities.User;
import huynbq.ntu.web2.repositories.CommentRepository;
import huynbq.ntu.web2.services.interf.CommentService;

@Service
public class CommentServiceImpl implements CommentService{
	@Autowired
	CommentRepository commentRepository;
	
	@Override
	public List<Comment> getCommentsByPost(Post post) {
		// TODO Auto-generated method stub
		return commentRepository.findByPostOrderByCreatedatAsc(post);
	}

	@Override
	public void save(Post post, User user, String content) {
		// TODO Auto-generated method stub
		 Comment comment = new Comment();
	        comment.setPost(post);
	        comment.setUser(user);
	        comment.setContent(content);
	        comment.setCreatedAt(LocalDateTime.now()); // tự set thời gian tạo
	        commentRepository.save(comment);
	}

	@Override
	public Comment findById(Integer commentID) {
		// TODO Auto-generated method stub
		return commentRepository.findById(commentID).orElseThrow();
	}

	@Override
	public void delete(int commentId) {
		// TODO Auto-generated method stub
		commentRepository.deleteById(commentId);
	}

	
	
}
