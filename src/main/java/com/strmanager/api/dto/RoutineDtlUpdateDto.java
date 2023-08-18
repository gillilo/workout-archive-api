package com.strmanager.api.dto;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoutineDtlUpdateDto {

    private long id;
    private int setNumber;
    private int weight;
    private int targetCount;
    private int actualCount;
    private boolean done;

}
