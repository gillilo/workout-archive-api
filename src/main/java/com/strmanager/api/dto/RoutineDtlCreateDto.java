package com.strmanager.api.dto;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoutineDtlCreateDto {

    private int setNumber;
    private int weight;
    private int targetCount;

}
