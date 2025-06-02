package huynbq.ntu.web2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import huynbq.ntu.web2.entities.Category;
import huynbq.ntu.web2.entities.Mode;
import huynbq.ntu.web2.entities.Post;
import huynbq.ntu.web2.repositories.CategoryRepository;
import huynbq.ntu.web2.services.interf.PostService;
import jakarta.servlet.http.HttpSession;

@Controller
public class PostController {
	@Autowired
	PostService postService;
	@Autowired
	CategoryRepository categoryRepository;
	
	@GetMapping("/blog/create")
	public String showCreatePostForm(ModelMap model) {
	    model.addAttribute("categories", categoryRepository.findAll());
	    return "views/createblog";
	}

	@PostMapping("/blog/create")
	public String createPost(@RequestParam String title,
	                         @RequestParam String content,
	                         @RequestParam String mode,
	                         @RequestParam int categoryId,
	                         HttpSession session) {
	    String username = (String) session.getAttribute("username");
	    postService.createPost(title, content, username, categoryId, Mode.valueOf(mode));
	    return "redirect:/blog/home";
	}
	
	@GetMapping("/myblog/delete/{id}")
	public String deletePost(@PathVariable("id") int id) {
	    postService.deletePost(id);
	    return "redirect:/blog/myblog";
	}

	@GetMapping("/myblog/edit/{id}")
	public String editPostForm(@PathVariable("id") int id, ModelMap model) {
	    Post post = postService.findPost(id);
	    model.addAttribute("post", post);
	    model.addAttribute("categories", categoryRepository.findAll());
	    return "views/editpost"; 
	}
	
	@PostMapping("/myblog/update")
	public String updatePost(@RequestParam int id,
	                         @RequestParam String title,
	                         @RequestParam String content,
	                         @RequestParam int categoryId,
	                         @RequestParam Mode mode) {
	    postService.updatePost(id, title, content, categoryId, mode);
	    return "redirect:/blog/myblog";
	}
}
