package huynbq.ntu.web2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import huynbq.ntu.web2.entities.User;
import huynbq.ntu.web2.services.UserService;

@Controller
public class UserController {
	@Autowired
	UserService userService;
	@GetMapping("/register")
    public String showRegisterForm() {
        return "views/register"; // trỏ đến file HTML: register.html
    }
	
	@PostMapping("/register")
	public String register(@RequestParam String username,
	                       @RequestParam String password,
	                       @RequestParam String confirmPassword,
	                       ModelMap model) {

	    if (!password.equals(confirmPassword)) {
	        model.addAttribute("error", "Mật khẩu không khớp");
	        return "views/register";
	    }

	    // Kiểm tra điều kiện mật khẩu
	    if (!isValidPassword(password)) {
	        model.addAttribute("error", "Mật khẩu phải dài ít nhất 8 ký tự, có chữ hoa, chữ thường và ký tự đặc biệt");
	        return "views/register";
	    }

	    User user = new User();
	    user.setUsername(username);
	    user.setPassword(password);

	    boolean success = userService.register(user);

	    if (!success) {
	        model.addAttribute("error", "Tên đăng nhập đã tồn tại");
	        return "views/register";
	    }

	    return "redirect:/login";
	}

	// Hàm kiểm tra mật khẩu hợp lệ
	private boolean isValidPassword(String password) {
	    String pattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[^a-zA-Z0-9]).{9,}$";
	    return password.matches(pattern);
	}


    @GetMapping("/login")
    public String loginPage() {
        return "views/login"; // Tạo file login.html riêng
    }
    
    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        ModelMap model) {

        boolean success = userService.checkLogin(username, password);

        if (!success) {
            model.addAttribute("error", "Tên đăng nhập hoặc mật khẩu không đúng");
            return "views/login";
        }

        return "redirect:/home";
    }

}
