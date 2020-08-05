package com.dxc.matchService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.CONFLICT,reason="Player already exists")
public class CMatchAlreadyExistsException extends Exception{
	public static final long serialVersionUID = 1234567891;

}
