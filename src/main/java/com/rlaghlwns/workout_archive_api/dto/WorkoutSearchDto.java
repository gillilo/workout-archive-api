package com.rlaghlwns.workout_archive_api.dto;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WorkoutSearchDto {
    
    private String name, type, muscle, equipment, difficulty;
    private int offset, limit;
    
}
