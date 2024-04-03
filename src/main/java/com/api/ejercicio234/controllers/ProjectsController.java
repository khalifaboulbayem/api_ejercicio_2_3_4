package com.api.ejercicio234.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.api.ejercicio234.models.Article;
import com.api.ejercicio234.models.Project;
import com.api.ejercicio234.services.impl.ProjectServiceImp;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("api/v1/projects/")
public class ProjectsController {

	@Autowired
	private ProjectServiceImp projectService;

	@GetMapping("")
	@Operation(summary = "traer los proyectos", description = "Devolver todos los proyectos existentes", responses = {
			@ApiResponse(responseCode = "200", description = "¡Operación exitosa!", content = @Content(schema = @Schema(implementation = Project.class))) })
	public ResponseEntity<List<Project>> getAll() {
		return ResponseEntity.ok(projectService.getAll());
	}

	@GetMapping("{userId}")
	public String getUserProjects(@PathVariable Long userId) {
		return "Pagina de usarios";
	}

	@Operation(summary = "Obtener proyectos por la su etiqueta", description = "Devuelve un proyectos segun su etiqueta", responses = {
			@ApiResponse(responseCode = "200", description = "¡Operación exitosa!", content = @Content(schema = @Schema(implementation = Article.class))),
			@ApiResponse(responseCode = "400", description = "El Id no es valido"),
			@ApiResponse(responseCode = "404", description = "No se han encontado proyectos") })
	@GetMapping("{tagId}")
	public ResponseEntity<List<Project>> getProjectByTag(
			@Parameter(description = "El id del blog") @PathVariable Long tagId) {
		return ResponseEntity.ok(projectService.getAllByTagId(tagId));
	}

	@Operation(summary = "Obtener proyecto por el id", description = "Devuelve un proyecto", responses = {
			@ApiResponse(responseCode = "200", description = "¡Operación exitosa!", content = @Content(schema = @Schema(implementation = Project.class))),
			@ApiResponse(responseCode = "400", description = "El Id no es valido"),
			@ApiResponse(responseCode = "404", description = "No se ha encontado el proyecto") })
	@GetMapping("details/{id}")
	public ResponseEntity<Project> getById(
			@Parameter(description = "El id del proyecto a buscar") @PathVariable Long id) {
		return ResponseEntity.ok(projectService.getById(id));
	}

	@PostMapping("create")
	@Operation(summary = "Añadir nuevo proyecto", description = "Añadir proyecto", responses = {
			@ApiResponse(responseCode = "200", description = "¡Operación exitosa!"),
			@ApiResponse(responseCode = "400", description = "¡Datos invalidos!"),
			@ApiResponse(responseCode = "409", description = "El proyectos ya exite!") })
	public ResponseEntity<Project> add(
			@Parameter(description = "Añadir proyectos", required = true) @RequestBody Project projectRequest) {
		Project projectCreated = projectService.create(projectRequest);
		return ResponseEntity.ok(projectCreated);
	}

	@PutMapping("edit/{id}")
	@Operation(summary = "Editar proyecto", description = "Editar proyecto por su id", responses = {
			@ApiResponse(responseCode = "200", description = "¡Operación exitosa!"),
			@ApiResponse(responseCode = "400", description = "¡El id es invalidos!"),
			@ApiResponse(responseCode = "404", description = "No se ha encontado el proyecto") })
	public ResponseEntity<Project> update(@PathVariable Long id, @RequestBody Project projectRequest) {
		Project projectUpdated = projectService.edit(id, projectRequest);
		return ResponseEntity.ok(projectUpdated);
	}

	@DeleteMapping("delete/{id}")
	@Operation(summary = "Borrar el proyecto por su id", description = "Borrar proyecto", responses = {
			@ApiResponse(responseCode = "200", description = "¡Operación exitosa!"),
			@ApiResponse(responseCode = "400", description = "¡El id es invalidos!"),
			@ApiResponse(responseCode = "404", description = "No se ha encontado el proyecto") })
	public ResponseEntity<Void> remove(
			@Parameter(description = "Id de proyecto a borrar", required = true) @PathVariable Long id) {
		projectService.delete(id);
		return ResponseEntity.ok().build();
	}

}
