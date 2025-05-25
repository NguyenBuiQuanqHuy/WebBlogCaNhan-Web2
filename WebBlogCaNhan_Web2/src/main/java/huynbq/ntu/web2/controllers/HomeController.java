package huynbq.ntu.web2.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import huynbq.ntu.web2.entities.Category;
import huynbq.ntu.web2.entities.Post;
import huynbq.ntu.web2.repositories.CategoryRepository;
import huynbq.ntu.web2.services.interf.PostService;

@Controller
public class HomeController {
	@Autowired
	PostService postService;
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
	
	
	@GetMapping("/blog/myblog")
	public String getMethodName() {
		return "views/myblog";
	}
	

}
