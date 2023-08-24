package com.project.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class UserNotFoundException extends Exception
{
	private String message;

	public UserNotFoundException(String message)
	{
		this.message=message;
	}
}
