package com.api.ejercicio234.controllers;

import org.springframework.web.bind.annotation.*;

import com.api.ejercicio234.models.Blog;

@RestController
@RequestMapping("api/v1/blogs/")
public class BlogsController {
	
	@GetMapping("")
	public String getAll() {
		return "Pagina de usarios";
	}
	@GetMapping("{userId}")
	public String getBlogsByUser(@PathVariable Long userId) {
		return "Pagina de usarios";
	}
	
	@GetMapping("details/{id}")
	public String getById(@PathVariable Long id) {
		return "Pagina de usarios";
	}
	
	
	@PostMapping("create")
	public String add(@RequestBody Blog blogRequest) {
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
