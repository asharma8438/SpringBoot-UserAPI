package io.controllers;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import application.Application;
import io.repositories.UserRepository;
import io.views.User;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= Application.class)
public class MongoUserControllerTest {

	@Autowired
	private MongoUserController mc;
	@MockBean
	private UserRepository ur;
	private User user;
	@Before
	public void before() {
		user = new User();
		user.setAge("32");
		user.setEmail("a@gmail.com");
		user.setGender("M");
		//user.setId("11");
		user.setLocation("Docks");
		user.setPhone("000");

		List<User> ul = new ArrayList<>();
		ul.add(user);

		Mockito.when(ur.save(Matchers.<User>any())).thenReturn(user);
		Mockito.when(ur.findAll()).thenReturn(ul);
		Mockito.when(ur.findByName(Matchers.anyString())).thenReturn(user);
	}

	@Test
	public void testCreate() {

		ResponseEntity<User> rs= mc.createUser(user);
		assertTrue(rs.getStatusCode()==HttpStatus.ACCEPTED);
	}
	@Test
	public void testfindAll() {
		ResponseEntity<List> all=mc.getAllusers();
		assertTrue(all.getStatusCode()==HttpStatus.ACCEPTED);
	}
	@Test
	public void testfind() {
		ResponseEntity<User> rs=mc.getUser("Ashish");
		assertTrue(rs.getStatusCode()==HttpStatus.ACCEPTED);
	}
	@Test(expected = Exception.class)
	public void testNull() {
		user=null;
		ResponseEntity<User> rs= mc.createUser(user);
		assertTrue(rs.getStatusCode()==HttpStatus.ACCEPTED);
	}

}
