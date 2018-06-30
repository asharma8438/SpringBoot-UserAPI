package io.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import io.views.User;

public interface UserRepository extends MongoRepository<User, String> {

	public User findByName(String name);
}
