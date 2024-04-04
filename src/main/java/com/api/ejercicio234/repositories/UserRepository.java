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
				new User(1L, "khalifa@email.com", "khalifa", "khalifa",
						"$2a$10$HcUWbwp0b7gunMbQHCBehOwv6/nBNfEHi7Hvl26iyrLp7z4FKZWwW"),
				new User(2L, "khalifa2@email.com", "khalifa2", "khalifa",
						"$2a$10$HcUWbwp0b7gunMbQHCBehOwv6/nBNfEHi7Hvl26iyrLp7z4FKZWwW"),
				new User(3L, "khalifa2@email.com", "khalifa3", "khalifa",
						"$2a$10$HcUWbwp0b7gunMbQHCBehOwv6/nBNfEHi7Hvl26iyrLp7z4FKZWwW"))
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

	public User findByNick(String nickname) {
		return this.users.stream()
				.filter(user -> user.getNick().equals(nickname))
				.findAny()
				.orElse(null);
	}

}
