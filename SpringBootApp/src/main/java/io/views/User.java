package io.views;

import org.springframework.data.annotation.Id;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class User {

	@Id
	private String id;
	private String name;
	private String gender;
	private String age;
	private String email;
	private String phone;
	private String location;
	
	public User() {};
	
	public User(String name,String gender,String age, String email, String phone, String habits) {
		super();
		this.name=name;
		this.gender=gender;
		this.age=age;
		this.email=email;
		this.phone=phone;
		this.location=habits;
	}
}
