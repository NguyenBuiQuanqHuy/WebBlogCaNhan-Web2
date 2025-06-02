package huynbq.ntu.web2.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import huynbq.ntu.web2.entities.Comment;
import huynbq.ntu.web2.entities.Post;
import huynbq.ntu.web2.entities.User;
import huynbq.ntu.web2.repositories.CommentRepository;
import huynbq.ntu.web2.repositories.UserRepository;
import huynbq.ntu.web2.services.interf.CommentService;
import jakarta.servlet.http.HttpSession;

@Controller
public class CommentController {
	@Autowired
	CommentService commentService;

	@Autowired
	UserRepository userRepository;

	@Autowired
	CommentRepository commentRepository;

	@GetMapping("/blog/post/{id}")
    public String viewPostDetail(@PathVariable("id") int id,
    		@RequestParam(value = "editCommentId", required = false) Integer editCommentId,
    		ModelMap model, HttpSession session) {
        Post post = commentService.getPostById(id);
        List<Comment> comments = commentService.getCommentsByPost(post);
        String currentUsername = (String) session.getAttribute("username");

        model.addAttribute("post", post);
        model.addAttribute("comments", comments);
        model.addAttribute("newComment", new Comment());
        model.addAttribute("editCommentId", editCommentId); 
        model.addAttribute("currentUsername", currentUsername);// id comment đang sửa, có thể null

        return "views/comment";  // trang chi tiết bài viết + bình luận
    }

	@PostMapping("/blog/comment")
    public String addComment(@RequestParam("postId") int postId,
                             @RequestParam("content") String content,
                             HttpSession session) {
        String username = (String) session.getAttribute("username");
        if(username == null) {
        	return "redirect:/login"; 
        }
        Post post = commentService.getPostById(postId);
        User user = userRepository.findByUsername(username);
        commentService.save(post, user, content);

        return "redirect:/blog/post/" + postId;
    }
	
	@PostMapping("/blog/comment/delete/{id}")
	public String deleteComment(@PathVariable int id, HttpSession session) {
	    Comment comment = commentService.findById(id);
	    if(comment != null && comment.getUser().getUsername().equals(session.getAttribute("username"))) {
	        commentService.delete(id);
	        return "redirect:/blog/post/" + comment.getPost().getID();
	    }
	    return "redirect:/blog/home";
	}

	@PostMapping("/blog/comment/edit/{id}")
	public String updateComment(@PathVariable int id,
	                            @RequestParam String content,
	                            HttpSession session) {
	    Comment comment = commentService.findById(id);
	    if(comment != null && comment.getUser().getUsername().equals(session.getAttribute("username"))) {
	        comment.setContent(content);
	        commentRepository.save(comment);
	        return "redirect:/blog/post/" + comment.getPost().getID();
	    }
	    return "redirect:/blog/home";
	}
}
