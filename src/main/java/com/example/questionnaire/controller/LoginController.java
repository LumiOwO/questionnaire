package com.example.questionnaire.controller;

import com.example.questionnaire.pojo.User;
import com.example.questionnaire.response.UserResponse;
import com.example.questionnaire.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.HtmlUtils;

import javax.validation.constraints.NotEmpty;

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
	public UserResponse login(@RequestBody User requestUser) {
		String email = HtmlUtils.htmlEscape(requestUser.getEmail());
		User user = userService.getUser(email, requestUser.getPassword());

		if(user != null) {
			return new UserResponse.Builder(true)
					.username(user.getUsername())
					.uid(user.getId())
					.build();
		} else {
			return new UserResponse.Builder(false)
					.msg("用户名或密码错误")
					.build();
		}
	}
}
