package com.api.ejercicio234.controllers;

import org.springframework.web.bind.annotation.*;

import com.api.ejercicio234.models.Article;

@RestController
@RequestMapping("api/v1/articles/")
public class ArticlesController {
	
	@GetMapping("")
	public String getAll() {
		return "Pagina de usarios";
	}
	@GetMapping("{tagId}")
	public String getArticlesByTag(@PathVariable Long tagId) {
		return "Pagina de usarios";
	}
	
	@GetMapping("{blogId}")
	public String getArticlesByBlog(@PathVariable Long blogId) {
		return "Pagina de usarios";
	}
	
	@GetMapping("details/{id}")
	public String getById(@PathVariable Long id) {
		return "Pagina de usarios";
	}
	
	
	@PostMapping("create")
	public String add(@RequestBody Article articleRequest) {
		return "Pagina de usarios";
	}
	
	@PutMapping("edit/{id}")
	public String update(@PathVariable Long id) {
		return "Pagina de usarios";
	}
	
	@DeleteMapping("delete/{id}")
	public String remove(@PathVariable Long id) {
		return "Pagina de usarios";
	}
	
	

}
