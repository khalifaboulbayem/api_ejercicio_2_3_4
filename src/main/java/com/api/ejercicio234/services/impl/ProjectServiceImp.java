package com.api.ejercicio234.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.api.ejercicio234.models.Project;
import com.api.ejercicio234.services.ProjectService;

@Service
public class ProjectServiceImp implements ProjectService {

	@Override
	public List<Project> getAll() {
		// TODO Auto-generated method stub
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
	public Project edit(Long id, Project project) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Project> getAllByTagId(Long tagId) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'getAllByTagId'");
	}

}
