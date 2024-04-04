package com.api.ejercicio234.dto.request;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Long Id;
    private String email;
    private String nick;
    private String name;
    private String password;
}
