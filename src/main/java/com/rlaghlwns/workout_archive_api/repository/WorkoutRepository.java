package com.rlaghlwns.workout_archive_api.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.rlaghlwns.workout_archive_api.domain.Workout;
import com.rlaghlwns.workout_archive_api.dto.WorkoutSearchDto;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.rlaghlwns.workout_archive_api.domain.QWorkout.workout;

@Repository
public class WorkoutRepository {

    @Autowired
    private JPAQueryFactory query;
    @Autowired
    private EntityManager em;

    public List<Workout> findWorkoutList(WorkoutSearchDto workoutSearchDto) {
        return query
                .select(workout)
                .from(workout)
                .where(
                        nameLike(workoutSearchDto.getName()),
                        typeEq(workoutSearchDto.getType()),
                        muscleEq(workoutSearchDto.getMuscle()),
                        equipmentEq(workoutSearchDto.getEquipment()),
                        difficultyEq(workoutSearchDto.getDifficulty())
                )
                .offset(workoutSearchDto.getOffset())
                .limit(workoutSearchDto.getLimit())
                .fetch();
    }

    private BooleanExpression nameLike(String workoutName) {
        return workoutName != null && !workoutName.equals("")? workout.name.likeIgnoreCase("%"+workoutName+"%") : null;
    }
    private BooleanExpression typeEq(String type) {
        return type != null && !type.equals("")? workout.type.eq(type) : null;
    }
    private BooleanExpression muscleEq(String muscle) {
        return muscle != null && !muscle.equals("")? workout.muscle.eq(muscle) : null;
    }
    private BooleanExpression equipmentEq(String equipment) {
        return equipment != null && !equipment.equals("")? workout.equipment.eq(equipment) : null;
    }
    private BooleanExpression difficultyEq(String difficulty) {
        return difficulty != null && !difficulty.equals("")? workout.difficulty.eq(difficulty) : null;
    }

    public void save(Workout workout) {
        em.persist(workout);
    }
}
