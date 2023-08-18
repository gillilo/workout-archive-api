package com.strmanager.api.dto;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoutineSearchDto {

    private long memberId;
    private String planDate;
    private String workoutName;

}
