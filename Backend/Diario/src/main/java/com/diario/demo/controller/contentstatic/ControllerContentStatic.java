package com.diario.demo.controller.contentstatic;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.diario.demo.model.Utente;

@Controller
public class ControllerContentStatic {
	
	
	/*Pagine statiche a libero accesso*/
	@GetMapping("/home")
	public String showHome() {
		return "home";
	}
	
	
	@GetMapping("/login")
	public String showLogin() {
		return "login";
	}
	
	
	@GetMapping("/signin")
	public String showSignin() {
		return "signin";
	}
		
	
	/*Pagine statiche riservate a Utente*/
	@GetMapping("/user")
	public String showUser() {
		return "user";
	}
	
	
	/*Pagine statiche riservate a Admin*/
	@GetMapping("/admin")
	public String showAdmin() {
		return "admin";
	}

	
	@GetMapping("/admin/areautenti")
	public String showareaAdminUtenti() {
		return "areautentiadmin";
	}
	
	@GetMapping("/admin/areatesti")
	public String showAdminAreaTesti() {
		return "areatestiadmin";
	}
	
}


