package com.strmanager.api.dto;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PerformanceCreateDto {

    private long memberId;
    private double benchpress;
    private double deadlift;
    private double squat;
    private double overheadpress;
    private double barbellrow;

}
