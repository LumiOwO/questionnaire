package com.example.questionnaire.dao;

import com.example.questionnaire.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDAO extends JpaRepository<User, Long> {
	// 根据接口的方法名，
	// JpaRepository会自动生成对应的SQL语句

	User getByEmailAndPassword(String email, String password);

	User getByEmail(String email);
}
