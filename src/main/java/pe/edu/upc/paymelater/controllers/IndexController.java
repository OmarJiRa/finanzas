package pe.edu.upc.paymelater.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class IndexController {
	
	@GetMapping
	public String landing() {
		return "landing";
	}
	
	@GetMapping("index")
	public String index() {
		return "index";
	}
	
	@GetMapping("layout")
	public String layout() {
		return "layout/layout";
	}
	
	@GetMapping("login")
	public String login() {
		return "login";
	}
}
