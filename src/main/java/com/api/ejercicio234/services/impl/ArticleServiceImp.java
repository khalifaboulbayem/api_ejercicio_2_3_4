package com.api.ejercicio234.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.api.ejercicio234.models.Article;
import com.api.ejercicio234.services.ArticleService;

@Service
public class ArticleServiceImp implements ArticleService {

	@Override
	public List<Article> getAll() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'getAll'");
	}

	@Override
	public Article create(Article model) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'create'");
	}

	@Override
	public Article getById(Long id) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'getById'");
	}

	@Override
	public Article edit(Long id, Article model) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'edit'");
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'delete'");
	}

	public List<Article> getAllByBlogId(Long blogId) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'getByBlogId'");
	}

	@Override
	public List<Article> getAllByTagId(Long tagId) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'getAllByTagId'");
	}

}
