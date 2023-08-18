package com.strmanager.api.dto;

import com.strmanager.api.domain.Gender;
import lombok.*;

@Data
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class MemberUpdateDto {

    private Long id;

    private String password;
    private String name;
    private String phone;
    private int age;
    private Gender gender;
    private double weight;
    private double benchpress;
    private double deadlift;
    private double squat;
    private double overheadpress;
    private double barbellrow;

}
