package com.strmanager.api.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.strmanager.api.domain.QWorkout;
import com.strmanager.api.domain.Workout;
import com.strmanager.api.dto.WorkoutSearchDto;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

import static com.strmanager.api.domain.QWorkout.workout;

@Repository
public class WorkoutRepository {

    private final EntityManager em;
    private final JPAQueryFactory query;

    public WorkoutRepository(EntityManager em) {
        this.em = em;
        this.query = new JPAQueryFactory(em);
    }

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
