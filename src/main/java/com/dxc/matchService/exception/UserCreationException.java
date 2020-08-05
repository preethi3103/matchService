package com.dxc.matchService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.CONFLICT,reason="User cannot be created")
public class UserCreationException extends Exception {
	public static final long serialVersionUID = 1234567894;

}
