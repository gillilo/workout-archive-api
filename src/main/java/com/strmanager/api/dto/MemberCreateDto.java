package com.strmanager.api.dto;

import com.strmanager.api.domain.Gender;
import lombok.*;

@Data
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class MemberCreateDto {

    private String name;
    private String email;
    private String password;
    private String phone;
    private int age;
    private Gender gender;
    private double weight;

}
