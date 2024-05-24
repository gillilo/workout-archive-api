package com.rlaghlwns.workout_archive_api.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Workout {

    @Id
    @GeneratedValue
//    @Column(name = "workout_id")
    private Long id;

    private String name;
    private String type;
    private String muscle;
    private String equipment;
    private String difficulty;

    @Column(columnDefinition = "TEXT")
    private String instructions;

    public static Workout createWorkout(
            String name,
            String type,
            String muscle,
            String equipment,
            String difficulty,
            String instructions
    ) {
        Workout workout = new Workout();
        workout.name = name;
        workout.type = type;
        workout.muscle = muscle;
        workout.equipment = equipment;
        workout.difficulty = difficulty;
        workout.instructions = instructions;
        return workout;
    }

}
