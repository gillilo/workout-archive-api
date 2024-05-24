package com.rlaghlwns.workout_archive_api.service;

import com.rlaghlwns.workout_archive_api.domain.Workout;
import com.rlaghlwns.workout_archive_api.dto.WorkoutSearchDto;
import com.rlaghlwns.workout_archive_api.repository.WorkoutRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class WorkoutService {

    private final WorkoutRepository workoutRepository;

    public List<Workout> findWorkoutList(WorkoutSearchDto workoutSearchDto) {
        return workoutRepository.findWorkoutList(workoutSearchDto);
    }

    @Transactional
    public void save(Workout workout) {
        workoutRepository.save(workout);
    }

}
