package com.dxc.matchService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.CONFLICT,reason="Player not found")
public class CMatchNotFoundException extends Exception{
	public static final long serialVersionUID = 1234567892;

}

