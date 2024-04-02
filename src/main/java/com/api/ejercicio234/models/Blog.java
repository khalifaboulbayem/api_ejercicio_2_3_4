package com.api.ejercicio234.models;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Blog {
	
	private Long id;
	private String title;
	private LocalDate date;

}
