package com.example.questionnaire.error;

import com.example.questionnaire.response.MyResponse;
import com.example.questionnaire.response.UserResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@ResponseBody
public class GlobalExceptionInterceptor {

	@ExceptionHandler(value = Exception.class)
	public MyResponse exceptionHandler(HttpServletRequest request, Exception e) {
		MyResponse response = null;
		if(e instanceof MethodArgumentNotValidException) {
			response = new UserResponse.Builder(false)
					.msg(((MethodArgumentNotValidException)e)
						.getBindingResult().getFieldError().getDefaultMessage())
					.build();
		}
		return response;
	}
}
