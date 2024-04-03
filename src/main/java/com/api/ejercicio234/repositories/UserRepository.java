package com.api.ejercicio234.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Component;

import com.api.ejercicio234.models.User;

@Component
public class UserRepository {

	List<User> users = new ArrayList<User>();

	public UserRepository() {
		this.users = Stream.of(
				new User(1L, "nick 1", "name 1"),
				new User(2L, "nick 2", "name 2"),
				new User(3L, "nick 2", "name 3"),
				new User(4L, "nick 3", "name 4"))
				.collect(Collectors.toList());
	}

	// Obtener todos los usuarios:
	public List<User> findAll() {
		return this.users;
	}

	public User findById(Long id) {
		return this.users.stream()
				.filter(user -> user.getId().equals(id))
				.findAny()
				.orElse(null);
	}

	public User save(User userModel) {
		users.add(userModel);
		return userModel;
	}

	public User edit(User userModel) {
		User userUpdeted = users.stream()
				.filter(user -> user.getId().equals(userModel.getId()))
				.findAny()
				.orElse(null);
		userUpdeted.setName(userModel.getName());
		userUpdeted.setNick(userModel.getNick());
		return userUpdeted;
	}

	public void deleteById(Long id) {
		this.users.removeIf(user -> user.getId().equals(id));
	}

}
