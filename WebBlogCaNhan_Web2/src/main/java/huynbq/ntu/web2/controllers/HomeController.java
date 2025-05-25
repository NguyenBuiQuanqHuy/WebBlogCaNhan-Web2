package huynbq.ntu.web2.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import huynbq.ntu.web2.entities.Category;
import huynbq.ntu.web2.entities.Post;
import huynbq.ntu.web2.entities.User;
import huynbq.ntu.web2.repositories.CategoryRepository;
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
	@GetMapping("/blog/home")
	public String home(
	        @RequestParam(value = "keyword", required = false) String keyword,
	        @RequestParam(value = "category", required = false) Integer categoryId,
	        ModelMap model) {

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

	    model.addAttribute("posts", posts);
	    model.addAttribute("categories", categories);
	    model.addAttribute("selectedCategory", categoryId);
	    model.addAttribute("keyword", keyword);

	    return "views/home";
	}
	
	
	@GetMapping("blog/myblog")
	public String viewMyBlog(ModelMap model, HttpSession session) {
		String username = (String) session.getAttribute("username");
	    List<Post> myPosts = postService.getPostsByUser(username);
	    model.addAttribute("posts", myPosts);
	    return "views/myblog";
	}


	

}
