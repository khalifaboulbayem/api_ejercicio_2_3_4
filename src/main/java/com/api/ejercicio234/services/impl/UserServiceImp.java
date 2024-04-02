package com.api.ejercicio234.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.api.ejercicio234.models.User;
import com.api.ejercicio234.repositories.UserRepository;
import com.api.ejercicio234.services.UserService;

@Service
public class UserServiceImp implements UserService {

	@Autowired
	public UserRepository userRepository;

	@Override
	public List<User> getAll() {
		return userRepository.getAll();
	}

	@Override
	public User create(User userModel) {
		User user = userRepository.getById(userModel.getId());
		if (user != null) {
			throw new ResponseStatusException(HttpStatus.CONFLICT,
					"El usuario con el Id " + user.getId() + "Ya existe");
		}

		if (userModel.getNick().isBlank()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El nick de usuario es requerido");
		}
		return userRepository.add(userModel);
	}

	@Override
	public User getById(Long id) {
		if (id == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El Id no debe ser nulo");
		}
		User user = userRepository.getById(id);
		if (user == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El usuario con el id " + id + " no existe");
		}
		return user;
	}

	@Override
	public User edit(Long id, User userRequest) {
		if (id == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El Id no debe ser nulo");
		}

		if (userRequest.getNick().isBlank()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El nick de usuario es requerido");
		}

		User user = userRepository.getById(id);

		if (user == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No hay ningun usuario con el ID:  " + id);
		}
		userRequest.setId(id);
		userRepository.edit(userRequest);

		return userRequest;
	}

	@Override
	public void delete(Long id) {
		if (id == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El Id no debe ser nulo");
		}
		User user = userRepository.getById(id);
		if (user == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El usuario con el id " + id + " no existe");
		}
		userRepository.delete(id);
	}

}
