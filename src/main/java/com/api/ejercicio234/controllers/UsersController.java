package com.api.ejercicio234.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.api.ejercicio234.models.User;
import com.api.ejercicio234.services.impl.UserServiceImp;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.*;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.util.*;

@Tag(name = "Usuarios", description = "Api de usuarios")
@RestController
@RequestMapping("api/v1/users/")
public class UsersController {

	@Autowired
	private UserServiceImp userService;

	@GetMapping("")
	@Operation(summary = "traer los usuarios", description = "Devolver todos los usuarios existentes", responses = {
			@ApiResponse(responseCode = "200", description = "¡Operación exitosa!", content = @Content(schema = @Schema(implementation = User.class))) })
	public ResponseEntity<List<User>> getAll() {
		return ResponseEntity.ok(userService.getAll());
	}

	@Operation(summary = "Obtener usuario por el id", description = "Devuelve un usuario", responses = {
			@ApiResponse(responseCode = "200", description = "¡Operación exitosa!", content = @Content(schema = @Schema(implementation = User.class))),
			@ApiResponse(responseCode = "400", description = "El Id no es valido"),
			@ApiResponse(responseCode = "404", description = "No se ha encontado el usuario") })
	@GetMapping("details/{id}")
	public ResponseEntity<User> getById(@Parameter(description = "El id del usuario a buscar") @PathVariable Long id) {
		return ResponseEntity.ok(userService.getById(id));
	}

	@PostMapping("create")
	@Operation(summary = "Añadir nuevo usuario", description = "Añadir usuario", responses = {
			@ApiResponse(responseCode = "200", description = "¡Operación exitosa!"),
			@ApiResponse(responseCode = "400", description = "¡Datos invalidos!"),
			@ApiResponse(responseCode = "409", description = "El usuario ya exite!") })
	public ResponseEntity<User> add(
			@Parameter(description = "Añadir usuario", required = true) @RequestBody User userRequest) {
		User userCreated = userService.create(userRequest);
		return ResponseEntity.ok(userCreated);
	}

	@PutMapping("edit/{id}")
	@Operation(summary = "Editar usuario", description = "Editar usuario por su id", responses = {
			@ApiResponse(responseCode = "200", description = "¡Operación exitosa!"),
			@ApiResponse(responseCode = "400", description = "¡El id es invalidos!"),
			@ApiResponse(responseCode = "404", description = "No se ha encontado el usuario") })
	public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User userRequest) {
		User userUpdated = userService.edit(id, userRequest);
		return ResponseEntity.ok(userUpdated);
	}

	@DeleteMapping("delete/{id}")
	@Operation(summary = "Borrar el usuario por su id", description = "Borrar usuario", responses = {
			@ApiResponse(responseCode = "200", description = "¡Operación exitosa!"),
			@ApiResponse(responseCode = "400", description = "¡El id es invalidos!"),
			@ApiResponse(responseCode = "404", description = "No se ha encontado el usuario") })
	public ResponseEntity<Void> remove(
			@Parameter(description = "Id of message to delete", required = true) @PathVariable Long id) {
		userService.delete(id);
		return ResponseEntity.ok().build();
	}

}
