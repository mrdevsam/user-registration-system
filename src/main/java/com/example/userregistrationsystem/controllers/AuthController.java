package com.example.userregistrationsystem.controllers;

import com.example.userregistrationsystem.model.*;
import com.example.userregistrationsystem.services.*;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;
import java.util.List;


@Controller
@Slf4j
public class AuthController {

	private final AccServ serv;

	public AuthController(AccServ serv) {
		this.serv = serv;
	}
	
	@GetMapping({"","/","/index"})
	public String homepage() {
		log.info("at index page");
		return "/index";
	}

	@GetMapping("/register")
	public String registration_page(Model model) {
		log.info("accessing registration page");
		//creating a model object to store form data
		AccDTO acc = new AccDTO();
		model.addAttribute("user", acc);
		return "register";
	}

	@PostMapping("/register/save")
	public String proccess_registration(@Valid @ModelAttribute("user") AccDTO acc, BindingResult rs, Model ml) {
		Account existingAcc = serv.findAccByEmail(acc.getUserEmail());

		//checking if the user with same email exists or not in the database
		if(existingAcc != null && existingAcc.getUserEmail() != null && !existingAcc.getUserEmail().isEmpty()) {
			rs.rejectValue("userEmail", null, "This email is associated with another account");
		}

		if(rs.hasErrors()) {
			ml.addAttribute("user", acc);
			return "register";
		}

		//if there are no errors with the provided data, then save those.
		serv.saveOrUpdateAcc(acc);
		return "redirect:/register?success";
	}

	@GetMapping("/users")
	public String showUsers(Model model) {
		List<AccDTO> accounts = serv.findAllAccounts();
		model.addAttribute("users", accounts);
		log.info("showing details of all users");
		return "users";
	}

	@GetMapping("/login")
	public String login() {
		log.info("at login page");
		return "login";
	}
}
