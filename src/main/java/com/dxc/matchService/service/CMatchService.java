package com.dxc.matchService.service;

import java.util.List;

import com.dxc.matchService.domain.CMatch;
import com.dxc.matchService.domain.User;
import com.dxc.matchService.exception.CMatchAlreadyExistsException;
import com.dxc.matchService.exception.CMatchNotFoundException;
import com.dxc.matchService.exception.UserAlreadyExistsException;
import com.dxc.matchService.exception.UserCreationException;
import com.dxc.matchService.exception.UserNotFoundException;



public interface CMatchService {

	public User registerUser(User user) throws UserAlreadyExistsException, UserCreationException;
	
	public User saveCMatchToFavorites(CMatch cmatch,String userName) throws CMatchAlreadyExistsException, UserNotFoundException;
	
	public User deleteCMatchFromFavorites(CMatch cmatch,String userName) throws UserNotFoundException, CMatchNotFoundException;
	
	public List<CMatch> getAllMatchesFromFavorites(String userName) throws UserNotFoundException;	
	
	
}
