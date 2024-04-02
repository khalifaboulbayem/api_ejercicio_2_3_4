package com.api.ejercicio234.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

	private Long id;

	@NotBlank
	@Size(min = 0, max = 30)
	private String nick;
	private String name;

}
