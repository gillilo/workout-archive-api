package com.strmanager.api.dto;

import lombok.*;

@Data
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {
    private String email;
    private String password;
}