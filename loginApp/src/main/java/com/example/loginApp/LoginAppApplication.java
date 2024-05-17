package com.example.loginApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("message", "Inicio de sesión exitoso");
		return "home";
	}

	@GetMapping("/login")
	public String login() {
		return "login";
	}
}
