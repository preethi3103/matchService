package com.dxc.matchService.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.dxc.matchService.domain.CMatch;
import com.dxc.matchService.domain.User;
import com.dxc.matchService.exception.CMatchAlreadyExistsException;
import com.dxc.matchService.exception.CMatchNotFoundException;
import com.dxc.matchService.exception.UserAlreadyExistsException;
import com.dxc.matchService.exception.UserCreationException;
import com.dxc.matchService.exception.UserNotFoundException;
import com.dxc.matchService.repository.CMatchRepository;
@Service
public class CMatchServiceImpl implements CMatchService {

	@Autowired
	private CMatchRepository cmatchRepository;

	public CMatchServiceImpl(CMatchRepository cmatchRepository) {
		super();
		this.cmatchRepository = cmatchRepository;
	}
	
	@Override
	public User registerUser(User user) throws UserAlreadyExistsException, UserCreationException {
		System.out.println("Entered registerUser");
		if (StringUtils.isEmpty(user.getUserName())) {
			throw new UserCreationException();
		}
		if ( cmatchRepository.findByUserName(user.getUserName()) != null) {
			throw new UserAlreadyExistsException();
		} else {
			System.out.println("registerUser for userName "+user.getUserName());
			cmatchRepository.save(user);
			System.out.println("User " + user.getUserName() + " saved successfully into DB");
		}
		System.out.println("Returning registered user");
		return user;
	}

	@Override
	public User saveCMatchToFavorites(CMatch cmatch, String userName) throws CMatchAlreadyExistsException, UserNotFoundException {
		System.out.println("saveCMatchToFavorites userName = "+userName);
		List<CMatch> matchList = null;
		User user = null;
		if (StringUtils.isEmpty(userName)) {
			throw new UserNotFoundException();
		}
		user = cmatchRepository.findByUserName(userName);
		if(user == null) {
			throw new UserNotFoundException();
		}
		matchList = user.getMatchList();
		if (!CollectionUtils.isEmpty(matchList)) {
			for (CMatch cmatchObj : matchList) {
				if (cmatchObj.getMatchType().equals(cmatch.getMatchType()))
					throw new CMatchAlreadyExistsException();
			}

			matchList.add(cmatch);
			user.setMatchList(matchList);
			cmatchRepository.save(user);
		} else {			
			matchList = new ArrayList<>();
			matchList.add(cmatch);
			user = new User();
			user.setUserName(userName);
			user.setMatchList(matchList);
			cmatchRepository.save(user);
		}
		return user;
	}

	@Override
	public User deleteCMatchFromFavorites(CMatch cmatch, String userName) throws UserNotFoundException, CMatchNotFoundException {
		System.out.println("deleteCMatchFromFavorites userName = "+userName);
		List<CMatch> matchList = null;
		User user = null;
		if (StringUtils.isEmpty(userName)) {
			throw new UserNotFoundException();
		}
		user = cmatchRepository.findByUserName(userName);
		if(user == null) {
			throw new UserNotFoundException();
		}
		
		matchList = user.getMatchList();
		Boolean isMatchFound = Boolean.FALSE;
		int cmatchObjectIndex = 0;
		if (!CollectionUtils.isEmpty(matchList)) {
			for (int i = 0; i < matchList.size(); i++) {
				if (matchList.get(i).getMatchId() == cmatch.getMatchId()) {
					isMatchFound = true;
					cmatchObjectIndex = i;
					break;
				}
			}
			if (isMatchFound) {
				matchList.remove(cmatchObjectIndex);
				user.setMatchList(matchList);
				cmatchRepository.save(user);
			}
		} else {
			throw new CMatchNotFoundException();
		}
		return user;
	}

	@Override
	public List<CMatch> getAllMatchesFromFavorites(String userName) throws UserNotFoundException {
		if (StringUtils.isEmpty(userName)) {
			throw new UserNotFoundException();
		}
		User user = cmatchRepository.findByUserName(userName);
		if(user == null) {
			throw new UserNotFoundException();
		}
		return user.getMatchList();
	}

}
	