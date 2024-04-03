package com.api.ejercicio234.services;

import java.util.List;

import com.api.ejercicio234.models.Article;

public interface ArticleService {

	List<Article> getAll();

	Article create(Article model);

	Article getById(Long id);

	Article edit(Long id, Article model);

	void delete(Long id);

	List<Article> getAllByBlogId(Long blogId);

	List<Article> getAllByTagId(Long tagId);
}
