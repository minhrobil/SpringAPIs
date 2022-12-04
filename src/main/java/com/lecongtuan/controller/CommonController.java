package com.lecongtuan.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CommonController {
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("/login")
	public String processLogin(HttpServletRequest request,
			@RequestParam(name ="error", required = false) String error) {
		if (error != null) {
			request.setAttribute("error", error);
		}
		return "login";
	}
}
