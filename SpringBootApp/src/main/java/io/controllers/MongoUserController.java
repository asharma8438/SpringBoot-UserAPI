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
import org.springframework.web.bind.annotation.RestController;

import io.repositories.UserRepository;
import io.views.User;

@RestController
@RequestMapping(value="/mapi")
public class MongoUserController {
	
	UserRepository ur;
	
	public static final Logger log = LoggerFactory.getLogger(MongoUserController.class);
	
	@Autowired
	public MongoUserController(UserRepository ur) {
		this.ur = ur;
	}

	@RequestMapping(value="/users", method=RequestMethod.GET)
	public ResponseEntity<List> getAllusers(){
		log.info("List of all users from the database");
		return new ResponseEntity<List>(ur.findAll(),HttpStatus.ACCEPTED);
	}
	
	@RequestMapping(value="/user/{name}", method=RequestMethod.GET)
	public ResponseEntity<User> getUser(@PathVariable String name){
		log.info("fetching the details of "+name);
		return new ResponseEntity<User>(ur.findByName(name), HttpStatus.ACCEPTED);
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public ResponseEntity<User> createUser(@RequestBody User user){
		log.info("Save the details of "+user.getName());
		return new ResponseEntity<User>(ur.save(user),HttpStatus.ACCEPTED);
	}
}
