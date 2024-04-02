package com.api.ejercicio234.models;
import java.time.LocalDate;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Detail {

	private LocalDate date;
	private String content;
	
	private String type;
	private String budget;
	
}
