package com.example.userregistrationsystem.controllers;

import com.example.userregistrationsystem.model.*;
import com.example.userregistrationsystem.services.*;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;


@Controller
@Slf4j
public class AuthController {

	@GetMapping({"","/","/index"})
	public String homepage() {
		log.info("at index page");
		return "/index";
	}

	// @GetMapping("/registration")
	// public String registration_page() {
	// 	log.info("at registration page");
	// 	return null;
	// }
}
