package com.api.ejercicio234.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.api.ejercicio234.models.Article;
import com.api.ejercicio234.services.impl.ArticleServiceImp;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Articulos", description = "Api de articulos")
@RestController
@RequestMapping("api/v1/articles/")
public class ArticlesController {

	@Autowired
	private ArticleServiceImp articleService;

	@GetMapping("")
	@Operation(summary = "traer los articulos", description = "Devolver todos los articulos existentes", responses = {
			@ApiResponse(responseCode = "200", description = "¡Operación exitosa!", content = @Content(schema = @Schema(implementation = Article.class))) })
	public ResponseEntity<List<Article>> getAll() {
		return ResponseEntity.ok(articleService.getAll());
	}

	/**
	 * Obtener la lista de articulos segun el id etiqueta
	 * 
	 * @param tagId
	 * @return
	 */
	@Operation(summary = "Obtener articulos por la su etiqueta", description = "Devuelve un articulos segun su etiqueta", responses = {
			@ApiResponse(responseCode = "200", description = "¡Operación exitosa!", content = @Content(schema = @Schema(implementation = Article.class))),
			@ApiResponse(responseCode = "400", description = "El Id no es valido"),
			@ApiResponse(responseCode = "404", description = "No se han encontado articulos") })
	@GetMapping("{tagId}")
	public ResponseEntity<List<Article>> getArticlesByTag(
			@Parameter(description = "El id del blog") @PathVariable Long tagId) {
		return ResponseEntity.ok(articleService.getAllByTagId(tagId));
	}

	/**
	 * Obtener la lista de articulos de un blog
	 * 
	 * @param blogId
	 * @return
	 */
	@GetMapping("{blogId}")
	@Operation(summary = "Obtener articulos de un blog", description = "Devuelve un articulos de un blog concreto", responses = {
			@ApiResponse(responseCode = "200", description = "¡Operación exitosa!", content = @Content(schema = @Schema(implementation = Article.class))),
			@ApiResponse(responseCode = "400", description = "El Id no es valido"),
			@ApiResponse(responseCode = "404", description = "No se han encontado articulos") })
	public ResponseEntity<List<Article>> getArticlesByBlog(
			@Parameter(description = "El id del blog") @PathVariable Long blogId) {
		return ResponseEntity.ok(articleService.getAllByBlogId(blogId));
	}

	@Operation(summary = "Obtener articulos por el id", description = "Devuelve un articulos", responses = {
			@ApiResponse(responseCode = "200", description = "¡Operación exitosa!", content = @Content(schema = @Schema(implementation = Article.class))),
			@ApiResponse(responseCode = "400", description = "El Id no es valido"),
			@ApiResponse(responseCode = "404", description = "No se ha encontado el articulos") })
	@GetMapping("details/{id}")
	public ResponseEntity<Article> getById(
			@Parameter(description = "El id del articulos a buscar") @PathVariable Long id) {
		return ResponseEntity.ok(articleService.getById(id));
	}

	@PostMapping("create")
	@Operation(summary = "Añadir nuevo articulo", description = "Añadir articulo", responses = {
			@ApiResponse(responseCode = "200", description = "¡Operación exitosa!"),
			@ApiResponse(responseCode = "400", description = "¡Datos invalidos!"),
			@ApiResponse(responseCode = "409", description = "El articulos ya exite!") })
	public ResponseEntity<Article> add(
			@Parameter(description = "Añadir articulos", required = true) @RequestBody Article articleRequest) {
		Article articleCreated = articleService.create(articleRequest);
		return ResponseEntity.ok(articleCreated);
	}

	@PutMapping("edit/{id}")
	@Operation(summary = "Editar articulo", description = "Editar articulo por su id", responses = {
			@ApiResponse(responseCode = "200", description = "¡Operación exitosa!"),
			@ApiResponse(responseCode = "400", description = "¡El id es invalidos!"),
			@ApiResponse(responseCode = "404", description = "No se ha encontado el articulo") })
	public ResponseEntity<Article> update(@PathVariable Long id, @RequestBody Article articleRequest) {
		Article articleUpdated = articleService.edit(id, articleRequest);
		return ResponseEntity.ok(articleUpdated);
	}

	@DeleteMapping("delete/{id}")
	@Operation(summary = "Borrar el articulo por su id", description = "Borrar articulo", responses = {
			@ApiResponse(responseCode = "200", description = "¡Operación exitosa!"),
			@ApiResponse(responseCode = "400", description = "¡El id es invalidos!"),
			@ApiResponse(responseCode = "404", description = "No se ha encontado el articulo") })
	public ResponseEntity<Void> remove(
			@Parameter(description = "Id de articulo a borrar", required = true) @PathVariable Long id) {
		articleService.delete(id);
		return ResponseEntity.ok().build();
	}

}
