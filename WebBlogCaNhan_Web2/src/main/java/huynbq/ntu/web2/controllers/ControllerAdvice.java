package huynbq.ntu.web2.controllers;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;

import jakarta.servlet.http.HttpSession;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {
	 @ModelAttribute
	    public void addAttributes(ModelMap model, HttpSession session) {
	        String username = (String) session.getAttribute("username");
	        if (username != null) {
	            model.addAttribute("username", username);
	        }
	    }
}
