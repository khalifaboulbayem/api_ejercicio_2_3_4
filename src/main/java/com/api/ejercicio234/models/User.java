package com.api.ejercicio234.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

	private Long id;

	@Email
	@NotBlank
	@Size(max = 60)
	private String email;

	@NotBlank
	@Size(min = 0, max = 30)
	private String nick;
	private String name;

	@NotBlank
	private String password;

}
