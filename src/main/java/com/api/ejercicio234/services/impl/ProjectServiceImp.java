package com.api.ejercicio234.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.api.ejercicio234.models.Project;
import com.api.ejercicio234.repositories.UserRepository;
import com.api.ejercicio234.services.ProjectService;

public class ProjectServiceImp implements ProjectService {

	@Autowired
	public UserRepository userRepository;

	@Override
	public List<Project> getAll() {
		userRepository.getAll();
		return null;
	}

	@Override
	public Project create(Project projectModel) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Project getById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Project edit(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub

	}

}
