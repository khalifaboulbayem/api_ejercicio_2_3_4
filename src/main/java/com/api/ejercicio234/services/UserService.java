package com.api.ejercicio234.services;

import java.util.List;

import com.api.ejercicio234.models.User;

public interface UserService {

	List<User> getAll();

	User create(User userModel);

	User getById(Long id);

	User edit(Long id, User userModel);

	void delete(Long id);
}
