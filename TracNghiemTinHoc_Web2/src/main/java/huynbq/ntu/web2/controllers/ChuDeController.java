package huynbq.ntu.web2.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChuDeController {
	@GetMapping("/chude")
	public String ChuDe() {
		return"chude";
	}
}
