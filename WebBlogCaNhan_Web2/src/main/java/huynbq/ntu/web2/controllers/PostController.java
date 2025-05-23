package huynbq.ntu.web2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import huynbq.ntu.web2.entities.Mode;
import huynbq.ntu.web2.repositories.CategoryRepository;
import huynbq.ntu.web2.services.interf.PostService;
import jakarta.servlet.http.HttpSession;

@Controller
public class PostController {
	@Autowired
	PostService postService;
	@Autowired
	CategoryRepository categoryRepository;
	
	@GetMapping("/posts/create")
	public String showCreatePostForm(ModelMap model) {
	    model.addAttribute("categories", categoryRepository.findAll());
	    return "views/createblog";
	}

	@PostMapping("/posts/create")
	public String createPost(@RequestParam String title,
	                         @RequestParam String content,
	                         @RequestParam String mode,
	                         @RequestParam int categoryId,
	                         HttpSession session) {
	    String username = (String) session.getAttribute("username");
	    postService.createPost(title, content, username, categoryId, Mode.valueOf(mode));
	    return "redirect:/home"; // hoặc redirect đến trang chi tiết
	}

}
