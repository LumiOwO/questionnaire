package com.example.questionnaire.pojo;

import com.example.questionnaire.annotation.DataBytes;
import com.example.questionnaire.annotation.WithoutSpace;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "user")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})

public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "uid")
	private int uid;

	@NotEmpty(message = "用户名不能为空")
	@DataBytes(min = 3, max = 18, message = "用户名必须是3-18个英文字符或1-6个中文字符")
	@Column(name = "username")
	@WithoutSpace(message = "用户名中不能包含空格")
	private String username;

	@NotEmpty(message = "密码不能为空")
	@Length(min = 6, max = 20, message = "密码长度必须在6-20个字符之间")
	@Column(name = "password")
	private String password;

	@NotEmpty(message = "邮箱不能为空")
	@Email(message = "邮箱格式错误")
	@Length(max = 50, message = "邮箱长度不能超过50个字符")
	@Column(name = "email")
	private String email;

	public int getId() {
		return uid;
	}

	public void setId(int uid) {
		this.uid = uid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
