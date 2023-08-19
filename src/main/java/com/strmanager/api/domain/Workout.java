package com.strmanager.api.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Workout {

    @Id
    @GeneratedValue
    @Column(name = "workout_id")
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
