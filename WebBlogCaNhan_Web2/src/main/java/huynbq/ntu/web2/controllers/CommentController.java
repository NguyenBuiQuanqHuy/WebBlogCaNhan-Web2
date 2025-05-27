package huynbq.ntu.web2.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import huynbq.ntu.web2.entities.Category;
import huynbq.ntu.web2.entities.Comment;
import huynbq.ntu.web2.entities.Post;
import huynbq.ntu.web2.entities.User;
import huynbq.ntu.web2.repositories.CategoryRepository;
import huynbq.ntu.web2.repositories.CommentRepository;
import huynbq.ntu.web2.repositories.UserRepository;
import huynbq.ntu.web2.services.interf.CommentService;
import huynbq.ntu.web2.services.interf.PostService;
import jakarta.servlet.http.HttpSession;

@Controller
public class CommentController {
	@Autowired
	CommentService commentService;
	@Autowired
	PostService postService;
	@Autowired
	UserRepository userRepository;
	@Autowired
	CategoryRepository categoryRepository;
	@Autowired
	CommentRepository commentRepository;
	
	@GetMapping("/blog/post/{id}")
    public String viewPostDetail(@PathVariable("id") int id, ModelMap model) {
        Post post = commentService.getPostById(id);
        List<Comment> comments = commentService.getCommentsByPost(post);

        model.addAttribute("post", post);
        model.addAttribute("comments", comments);
        model.addAttribute("newComment", new Comment());

        return "views/comment";
    }
	
	 @PostMapping("/blog/comment")
	    public String addComment(@RequestParam("postId") int postId,
	                             @RequestParam("content") String content,
	                            HttpSession session) {
	        String username = (String) session.getAttribute("username");
	        Post post = commentService.getPostById(postId);
	        User user = userRepository.findByUsername((String) session.getAttribute("username"));
	        commentService.save(post, user, content);

	        return "redirect:/blog/post/" + postId;
	    }

}
