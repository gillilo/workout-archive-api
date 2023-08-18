package com.strmanager.api.dto;

import lombok.*;

import java.util.ArrayList;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoutineUpdateDto {

    private long id;
    private long memberId;
    private String planDate;
    private String workoutName;
    private int mstSeq;
    private ArrayList<RoutineDtlUpdateDto> dtls = new ArrayList<>();

}
