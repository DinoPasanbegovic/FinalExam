package ca.sheridancollege.pasanbed.controllers;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ca.sheridancollege.pasanbed.Database.DatabaseAccess;


@Controller
public class HomeController {

	@Autowired
	@Lazy
	private DatabaseAccess da;
	
	@GetMapping("/")
	public String goHome(){
		return "index.html";
	}
	
	@GetMapping("user")
	public String goHomeUser(HttpSession session){
		session.setMaxInactiveInterval(60);
		return "/user/index.html";
	}
	
	@GetMapping("/login")
	public String goLoginPage(){
		return "login.html";
	}
	@GetMapping("/guest")
	public String goUser(){
		return "/user/index.html";
	}
	
	@GetMapping("/access-denied")
	public String goAccessDenied() {
		return "/error/accessDenied.html";
	}
	@GetMapping("/register")	
	public String goRegistration() {
		return "registration.html";
	}
	
	@PostMapping("/register")	
	public String processRegistration(@RequestParam String name, 
			@RequestParam String password) {
		
		da.addUser(name, password);
		Long userId=da.findUserAccount(name).getUserId();
		
		da.addRole(userId, 2);
		return "redirect:/";
	}
	
    @RolesAllowed({"ADMIN"})
    @RequestMapping("/admin")
    public String getAdmin()
    {
        return "/admin/Home.html";
    }
}
