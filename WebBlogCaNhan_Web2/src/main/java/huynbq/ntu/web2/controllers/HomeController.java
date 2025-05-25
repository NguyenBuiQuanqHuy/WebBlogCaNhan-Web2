package huynbq.ntu.web2.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import huynbq.ntu.web2.entities.Post;
import huynbq.ntu.web2.services.interf.PostService;

@Controller
public class HomeController {
	@Autowired
	PostService postService;
	@GetMapping("/blog/home")
	public String home(ModelMap model) {
		List<Post> posts = postService.getRandomPublicPosts();
		model.addAttribute("posts", posts);
		return "views/home";
	}
}
