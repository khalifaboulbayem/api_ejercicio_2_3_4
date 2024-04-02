package com.api.ejercicio234.models;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Tag {
	
	private Long id;
	private String label;
	private LocalDate date;
}
