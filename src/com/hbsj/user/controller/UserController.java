package com.hbsj.user.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hbsj.entity.User;
import com.hbsj.user.service.UserService;
@Controller
@RequestMapping("/adduser")
public class UserController {
	@Resource
	private UserService userService;
	
	@RequestMapping("/add")
	public String login(@RequestParam("userName") String name,
			@RequestParam("password") String password,
			Model model, HttpSession session){
		User u = new User();
		u.setUserName(name);
		u.setPassword(password);
		userService.add(u);
		return "hello";
	}
}
