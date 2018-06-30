package io.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.services.LoginService;
import io.services.ManagementService;
import io.services.RegistrationService;
import io.views.User;

@RestController
@RequestMapping("/api")
public class UserController {

	LoginService loginS;
	RegistrationService registerS;
	ManagementService manage;

	public UserController() {};

	@Autowired
	public UserController(LoginService loginS,RegistrationService registerS, ManagementService manage) {
		this.loginS = loginS;
		this.registerS = registerS;
		this.manage = manage;
	}

	public static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login() {
		logger.info("Displaying the login form");
		return loginS.login();
	}

	@RequestMapping(value="/users", method=RequestMethod.GET)
	public ResponseEntity<List<User>> getUsers() {
		logger.info("Get the list of all users");
		List<User> users = manage.getUsersList();
		if (users.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
			// You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}

	@RequestMapping(value="/user/{id}", method=RequestMethod.GET)
	public ResponseEntity getUser(@PathVariable String id) {
		logger.info("Get the user details="+id);
		User user= manage.getUser(id);
		if (user==null) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
			// You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity(user, HttpStatus.OK);
	}

	@RequestMapping(value="/user/{name}", method=RequestMethod.PUT)
	public ResponseEntity updateUser(@PathVariable String name, @RequestBody User user) {
		logger.info("Update the user info="+name);
		User currentUser= manage.getUser(name);
		if (currentUser==null) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
			// You many decide to return HttpStatus.NOT_FOUND
		}
		currentUser.setName(user.getName());
		currentUser.setAge(user.getAge());
		currentUser.setEmail(user.getEmail());
		currentUser.setPhone(user.getPhone());
		currentUser.setLocation(user.getLocation());
		manage.updateUser(currentUser);
		return new ResponseEntity<User>(currentUser, HttpStatus.OK);
	}

	@RequestMapping(value="/register/", method=RequestMethod.POST)
	public ResponseEntity register(@RequestBody User user) {
		logger.info("Register the new user="+user.getName());
		
		if (manage.getUser(user.getName())!=null) {
			logger.error("User already exists:"+user.getName());
			return new ResponseEntity("user:"+user.getName()+" already exists", HttpStatus.CONFLICT);
			// You many decide to return HttpStatus.CONFLICT
		}
		manage.saveUser(user);
		return new ResponseEntity("registered user:"+user.getName(), HttpStatus.OK);
	}

	@RequestMapping(value="/user/{id}", method=RequestMethod.DELETE)
	public ResponseEntity deleteUser(@PathVariable String id) {
		logger.info("Delete the user details="+id);
		User user= manage.getUser(id);
		if (user==null) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
			// You many decide to return HttpStatus.NOT_FOUND
		}
		manage.deleteUserByname(id);
		return new ResponseEntity("Deleted User:"+id, HttpStatus.OK);
	}
}
