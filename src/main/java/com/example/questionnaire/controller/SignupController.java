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

import javax.validation.Valid;

@Controller
public class SignupController {

	private final UserService userService;

	@Autowired
	public SignupController(UserService userService) {
		this.userService = userService;
	}

	@CrossOrigin
	@RequestMapping("/api/signup")
	@ResponseBody
	public UserResponse signup(@RequestBody @Valid User requestUser) {
		if(userService.exists(requestUser.getEmail())) {
			return new UserResponse.Builder(false)
					.msg("该邮箱已被注册")
					.build();
		}

		userService.signup(requestUser);
		return new UserResponse.Builder(true)
				.msg("注册成功")
				.build();
	}
}
