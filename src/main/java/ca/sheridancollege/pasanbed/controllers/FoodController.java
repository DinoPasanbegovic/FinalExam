package ca.sheridancollege.pasanbed.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import ca.sheridancollege.pasanbed.Database.DatabaseAccess;
import ca.sheridancollege.pasanbed.beans.Food;

@Controller
public class FoodController {

	
	@Autowired
	@Lazy
	private DatabaseAccess da;
	
	@GetMapping("/Userveg")
	public String getMemFoodTypeVeg(Model model, HttpSession session){
		session.setMaxInactiveInterval(60);

		model.addAttribute("vegs",da.getMemFoodTypeVeg());
		return "/user/UserVeg.html";
	}
	
	@GetMapping("/Userfruit")
	public String getMemFoodTypeFruit(Model model, HttpSession session){
		session.setMaxInactiveInterval(60);

		model.addAttribute("fruit",da.getMemFoodTypeFruit());
		return "/user/UserFruit.html";
	}
	
	@GetMapping("/Usermeat")
	public String getMemFoodTypeMeat(Model model, HttpSession session){
		session.setMaxInactiveInterval(60);

		model.addAttribute("meat",da.getMemFoodTypeMeat());
		return "/user/UserMeat.html";
	}
	
	@GetMapping("/UserCart")
	public String goCart(Model model, HttpSession session){
		session.setMaxInactiveInterval(60);

		model.addAttribute("meat",da.getMemFoodTypeMeat());
		return "/user/Cart.html";
	}
}

