package io.services;

import org.springframework.stereotype.Service;

import io.views.User;

@Service
public class RegistrationService {
	
	public String registerUser(User currentUser) {
		
		//ManagementService.users.add(currentUser);
		return "registered user "+currentUser;
	}

}
