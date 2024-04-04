package com.api.ejercicio234.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.api.ejercicio234.models.User;
import com.api.ejercicio234.repositories.UserRepository;
import com.api.ejercicio234.services.UserService;

@Service
public class UserServiceImp implements UserService, UserDetailsService {

	@Autowired
	public UserRepository userRepository;

	@Override
	public List<User> getAll() {
		return userRepository.findAll();
	}

	@Override
	public User create(User userModel) {
		User user = userRepository.findById(userModel.getId());
		if (user != null) {
			throw new ResponseStatusException(HttpStatus.CONFLICT,
					"El usuario con el Id " + user.getId() + "Ya existe");
		}

		if (userModel.getNick().isBlank()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El nick de usuario es requerido");
		}
		return userRepository.save(userModel);
	}

	@Override
	public User getById(Long id) {
		if (id == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El Id no debe ser nulo");
		}
		User user = userRepository.findById(id);
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

		User user = userRepository.findById(id);

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
		User user = userRepository.findById(id);
		if (user == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El usuario con el id " + id + " no existe");
		}
		userRepository.deleteById(id);
	}

	@Override
	public Optional<User> findByNick(String nickname) {
		if (nickname == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El Id no debe ser nulo");
		}
		return Optional.ofNullable(userRepository.findByNick(nickname));
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByNick(username);
		if (user == null) {
			throw new UsernameNotFoundException("El usuario con el nick " + username + " no existe");
		}
		return new org.springframework.security.core.userdetails.User(
				user.getNick(), user.getPassword(), true, true, true, true, null);
	}

}
