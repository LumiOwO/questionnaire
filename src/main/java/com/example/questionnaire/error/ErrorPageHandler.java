package com.example.questionnaire.error;

import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.server.ErrorPageRegistry;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class ErrorPageHandler implements ErrorPageRegistrar {
	@Override
	public void registerErrorPages(ErrorPageRegistry registry) {
		registry.addErrorPages(new ErrorPage(
			HttpStatus.NOT_FOUND, "/index.html"
		));
	}
}
