package com.example.questionnaire.service;

import com.example.questionnaire.dao.UserDAO;
import com.example.questionnaire.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	private final UserDAO userDAO;

	@Autowired
	public UserService(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public User getUser(String email, String password){
		return userDAO.getByEmailAndPassword(email, password);
	}

	public void signup(User user) {
		userDAO.save(user);
	}

	public boolean exists(String email) {
		return userDAO.getByEmail(email) != null;
	}
}
