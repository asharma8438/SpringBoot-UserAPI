package io.services;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import io.views.User;
import lombok.Getter;
import lombok.Setter;

@Service
@Getter @Setter
public class ManagementService {

	private static List<User> users;
	private static final AtomicLong counter = new AtomicLong();

	static{
		users= populateDummyUsers();
	}
	private static List<User> populateDummyUsers() {
		// TODO Auto-generated method stub
		List<User>  userList= new ArrayList<>();
		userList.add(new User("Ashish","M","29","abc@xyz.com","0000000000","habits"));
		userList.add(new User("Vikas","M","30","abc@xyz.com","0000000000","habits"));
		userList.add(new User("Gopi","M","30","abc@xyz.com","0000000000","habits"));
		return userList;
	}
	public List<User> getUsersList() {
		return users;
	}

	public User getUser(String name) {
		// TODO Auto-generated method stub
		for(User user : users){
			if(user.getName().equalsIgnoreCase(name)){
				return user;
			}
		}
		return null;
	}

	public void updateUser(User currentUser) {
		// TODO Auto-generated method stub
		int index = users.indexOf(currentUser);
		users.set(index, currentUser);
	}
	
	public void saveUser(User user) {
		//user.setId(counter.incrementAndGet());
		users.add(user);
	}
	
public void deleteUserByname(String name) {
		
		for (Iterator<User> iterator = users.iterator(); iterator.hasNext(); ) {
		    User user = iterator.next();
		    if (user.getName().equalsIgnoreCase(name)) {
		        iterator.remove();
		    }
		}
	}
}
