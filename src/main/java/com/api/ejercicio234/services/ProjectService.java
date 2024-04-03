package com.api.ejercicio234.services;

import java.util.List;

import com.api.ejercicio234.models.Project;

public interface ProjectService {

	List<Project> getAll();

	Project create(Project projectModel);

	Project getById(Long id);

	Project edit(Long id, Project projectModel);

	void delete(Long id);

	List<Project> getAllByTagId(Long tagId);
}
