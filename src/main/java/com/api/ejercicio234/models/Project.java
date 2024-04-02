package com.api.ejercicio234.models;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Project {
	private Long id;
	private String description;
	private String language;
	private Boolean open;

}
