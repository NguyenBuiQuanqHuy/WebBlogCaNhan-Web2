package huynbq.ntu.web2.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import huynbq.ntu.web2.entities.Category;
import huynbq.ntu.web2.entities.Comment;
import huynbq.ntu.web2.entities.Post;
import huynbq.ntu.web2.entities.User;
import huynbq.ntu.web2.repositories.CategoryRepository;
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
	
	@PostMapping("/comments")
	public String postComment(@RequestParam("postId") int postId,
	                          @RequestParam("content") String content,
	                          HttpSession session,
	                          ModelMap model) {

	    String username = (String) session.getAttribute("username");
	    if (username == null) {
	        return "redirect:/login";
	    }

	    Post post = postService.findPost(postId);
	    User user = userRepository.findByUsername(username);

	    if (post == null || user == null) {
	        return "redirect:/";
	    }

	    commentService.save(post, user, content);

	    // Gọi lại các dữ liệu như bên GET /blog/home
	    List<Post> posts = postService.getRandomPublicPosts();
	    List<Category> categories = categoryRepository.findAll();

	    Map<Integer, List<Comment>> postCommentsMap = new HashMap<>();
	    for (Post p : posts) {
	        List<Comment> comments = commentService.getCommentsByPost(p);
	        postCommentsMap.put(p.getID(), comments);
	    }
	    
	    model.addAttribute("currentUsername", username);
	    model.addAttribute("posts", posts);
	    model.addAttribute("categories", categories);
	    model.addAttribute("selectedCategory", null);
	    model.addAttribute("keyword", null);
	    model.addAttribute("postCommentsMap", postCommentsMap);

	    return "redirect:/blog/home#post-" + postId;

	}
	
	
	@PostMapping("/comments/delete")
	public String deleteComment(@RequestParam("commentId") int commentId,
	                            HttpSession session,
	                            RedirectAttributes redirectAttributes) {

	    String username = (String) session.getAttribute("username");
	    if (username == null) {
	        return "redirect:/login";
	    }

	    Comment comment = commentService.findById(commentId);
	    if (comment != null && comment.getUser().getUsername().equals(username)) {
	        int postId = comment.getPost().getID();
	        commentService.delete(commentId);
	        return "redirect:/blog/home#post-" + postId;
	    }

	    return "redirect:/blog/home";
	}


}
