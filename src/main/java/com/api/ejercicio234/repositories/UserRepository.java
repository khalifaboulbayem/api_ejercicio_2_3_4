package com.api.ejercicio234.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Repository;

import com.api.ejercicio234.models.User;

@Repository
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
	public List<User> getAll() {
		return this.users;
	}

	public User getById(Long id) {
		return this.users.stream()
				.filter(user -> user.getId().equals(id))
				.findAny()
				.orElse(null);
	}

	public User add(User userModel) {
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

	public void delete(Long id) {
		this.users.removeIf(user -> user.getId().equals(id));
	}

}
