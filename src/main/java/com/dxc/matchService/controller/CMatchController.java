package com.dxc.matchService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dxc.matchService.domain.CMatch;
import com.dxc.matchService.domain.User;
import com.dxc.matchService.service.CMatchService;

@RestController
@RequestMapping("/api/v1/cmatchservice")
public class CMatchController {
	
	@Autowired
	private CMatchService cmatchService;
	
	public CMatchController(CMatchService cmatchService) {
		this.cmatchService = cmatchService;
	}
	
	private ResponseEntity<?> responseEntity;


	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@RequestBody User user) {
		try {
			System.out.println("Entered registerUser");
			cmatchService.registerUser(user);
			System.out.println("Match registered successfully");
			responseEntity = new ResponseEntity<User>(user, HttpStatus.CREATED);
		} catch (Exception e) {
			responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
		}

		return responseEntity;
	}

	// http://localhost:8085/api/v1/cmatchservice/user/shyam/cmatch
	@PostMapping("/user/{userName}/cmatch")
	public ResponseEntity<?> saveCMatchToFavourites(@RequestBody CMatch cmatch,
			@PathVariable("userName") String userName) {
		try {
			User user = cmatchService.saveCMatchToFavorites(cmatch, userName);
			responseEntity = new ResponseEntity<User>(user, HttpStatus.CREATED);
		} catch (Exception e) {
			responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
		}
		return responseEntity;
	}

	@DeleteMapping("/user/{userName}/cmatch")
	public ResponseEntity<?> deleteCmatchFromFavourites(@PathVariable("userName") String userName,
			@RequestBody CMatch cmatch) {
		try {
			User user = cmatchService.deleteCMatchFromFavorites(cmatch, userName);
			responseEntity = new ResponseEntity<User>(user, HttpStatus.OK);
		} catch (Exception e) {
			responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		return responseEntity;
	}

	@GetMapping("/user/{userName}/cmatches")
	public ResponseEntity<?> getAllMatchesFromFavorites(@PathVariable("userName") String userName) {
		try {

			responseEntity = new ResponseEntity<List<CMatch>>(cmatchService.getAllMatchesFromFavorites(userName),
					HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return responseEntity;
	}

}
