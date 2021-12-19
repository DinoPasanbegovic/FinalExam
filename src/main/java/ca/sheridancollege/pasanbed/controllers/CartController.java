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
public class CartController {
	
	@Autowired
	@Lazy
	private DatabaseAccess da;

	@GetMapping("/checkout")
	public String getCheckout(Model model, HttpSession session) {
		session.setMaxInactiveInterval(60);
		model.addAttribute("checkouts",da.getCheckout());
		return "/user/Checkout.html";
	}
	
	
	@GetMapping("/cart/{id}")
	public String editQuantity(Model model, @PathVariable int id) {
		
		Food d =da.getMemFoodbyId(id);
		model.addAttribute("carts",d);
		return "/user/Cart.html";
	}
	
	@GetMapping("/modifyCart")
	public String modifyCart(Model model, @ModelAttribute Food food) {
		
		da.editQuantity(food);
		return "/user/index.html";
	}
}


