package huynbq.ntu.web2.controllers;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import huynbq.ntu.web2.entities.Category;
import huynbq.ntu.web2.entities.Comment;
import huynbq.ntu.web2.entities.Post;
import huynbq.ntu.web2.entities.User;
import huynbq.ntu.web2.repositories.CategoryRepository;
import huynbq.ntu.web2.services.interf.CommentService;
import huynbq.ntu.web2.services.interf.PostService;
import huynbq.ntu.web2.services.interf.UserService;
import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
	@Autowired
	PostService postService;
	@Autowired
	UserService userService;
	@Autowired
	CategoryRepository categoryRepository;
	@Autowired
	CommentService commentService;
	
	@GetMapping("/blog/home")
	public String home(
	        @RequestParam(value = "keyword", required = false) String keyword,
	        @RequestParam(value = "category", required = false) Integer categoryId,
	        ModelMap model, HttpSession session) {
			
		 String username = (String) session.getAttribute("username");
		    if (username == null) {
		        return "redirect:/login";
		    }
		
	    if (keyword != null && keyword.trim().isEmpty()) {
	        keyword = null;
	    }

	    List<Post> posts;

	    if (keyword != null || categoryId != null) {
	        posts = postService.searchPosts(keyword, categoryId);
	    } else {
	        posts = postService.getRandomPublicPosts();
	    }

	    List<Category> categories = categoryRepository.findAll();
	    
	    Map<Integer, List<Comment>> postCommentsMap = new HashMap<>();

	    for (Post post : posts) {
	        List<Comment> comments = commentService.getCommentsByPost(post);
	        postCommentsMap.put(post.getID(), comments);
	    }
	    
	    model.addAttribute("currentUsername", username);
	    model.addAttribute("posts", posts);
	    model.addAttribute("categories", categories);
	    model.addAttribute("selectedCategory", categoryId);
	    model.addAttribute("keyword", keyword);
	    model.addAttribute("postCommentsMap", postCommentsMap);

	    return "views/home";
	}
	
	
	@GetMapping("blog/myblog")
	public String viewMyBlog(ModelMap model, HttpSession session) {
		String username = (String) session.getAttribute("username");
	    List<Post> myPosts = postService.getPostsByUser(username);
	    
	    Map<Integer, List<Comment>> postCommentsMap = new HashMap<>();

	    for (Post post : myPosts) {
	        List<Comment> comments = commentService.getCommentsByPost(post);
	        postCommentsMap.put(post.getID(), comments);
	    }
	    model.addAttribute("posts", myPosts);
	    model.addAttribute("postCommentsMap", postCommentsMap);
	    return "views/myblog";
	}


	

}
