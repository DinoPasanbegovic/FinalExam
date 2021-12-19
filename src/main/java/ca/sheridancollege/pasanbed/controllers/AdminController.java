package ca.sheridancollege.pasanbed.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import ca.sheridancollege.pasanbed.Database.DatabaseAccess;
import ca.sheridancollege.pasanbed.beans.Food;
import ca.sheridancollege.pasanbed.beans.User;

@Controller
public class AdminController {

	@Autowired
	@Lazy
	private DatabaseAccess da;
	
	@GetMapping("/addFood")
	public String goAddFood(Model model, HttpSession session) {
		model.addAttribute("food",new Food());
		session.setMaxInactiveInterval(60);

		return "/admin/AddFood.html";
	}
	
	
	@GetMapping("/save")
	public String saveFood(Model model, @ModelAttribute Food food) {

		da.addFood(food);
		return "/admin/Home.html";
	}
	
	@GetMapping("/admin/deleteUser/{id}")
	public String deleteUsers(Model model, @PathVariable int id) {
		
		da.deleteUsers(id);
		System.out.println("Admin deleted User");
		return "redirect:/UserName";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteMemFood(Model model, @PathVariable int id) {
		
		da.deleteMemFood(id);
		System.out.println("Admin deleted Food");
		return "redirect:/view";
	}
	
	@GetMapping("/admin/editUser/{id}")
	public String editUser(Model model, @PathVariable int id) {
		
		User d = da.getUserbyId(id);
		model.addAttribute("urs",d);
		return "/admin/modifyUser.html";
	}
	
	@GetMapping("/edit/{id}")
	public String editMemFood(Model model, @PathVariable int id) {
		
		Food d =da.getMemFoodbyId(id);
		model.addAttribute("foods",d);
		System.out.println("Admin edited Food");
		return "/admin/modifyFood.html";
	}
	
	@GetMapping("/modifyUser")
	public String modifyUser(Model model, @ModelAttribute User user) {
		
		da.editUsername(user);
		return "redirect:/UserName";
	}
	
	@GetMapping("/modify")
	public String modifyFood(Model model, @ModelAttribute Food food) {
		
		da.editMemFood(food);
		System.out.println("Admin modified Food");
		return "redirect:/view";
	}
	
	@GetMapping("/view")
	public String viewFood(Model model, HttpSession session) {
		session.setMaxInactiveInterval(60);

		model.addAttribute("foods",da.getMemFood());
		return "/admin/FoodView.html";
	}
	@GetMapping("/UserName")
	public String getUsers(Model model){
		model.addAttribute("urs",da.getUsers());
		return "/admin/UserView.html";
	}
}