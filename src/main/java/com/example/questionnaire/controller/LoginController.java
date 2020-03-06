package com.example.questionnaire.controller;

import com.example.questionnaire.pojo.User;
import com.example.questionnaire.result.Result;
import com.example.questionnaire.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.HtmlUtils;

@Controller
public class LoginController {

	private final UserService userService;

	@Autowired
	public LoginController(UserService userService) {
		this.userService = userService;
	}

	@CrossOrigin
	@RequestMapping("/api/login")
	@ResponseBody
	public Result login(@RequestBody User requestUser) {
		String email = HtmlUtils.htmlEscape(requestUser.getUsername());
		User user = userService.getUser(email, requestUser.getPassword());
		if (user == null) {
			return new Result(400);
		} else {
			return new Result(200);
		}
	}
}
