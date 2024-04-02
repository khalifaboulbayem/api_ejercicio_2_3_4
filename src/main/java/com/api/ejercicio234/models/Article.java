package com.api.ejercicio234.models;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Article {

	private Long id;
	private String title;
	private String _abstract;
	private String content;
}
