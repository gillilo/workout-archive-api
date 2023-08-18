package com.strmanager.api.controller;

import com.strmanager.api.domain.Workout;
import com.strmanager.api.dto.WorkoutSearchDto;
import com.strmanager.api.service.WorkoutService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class WorkoutController {

    private final WorkoutService workoutService;

    @GetMapping("/workout")
    public FindWorkoutListResponse findWorkoutList(
            @RequestParam(required = false, name = "n") String name,
            @RequestParam(required = false, name = "t") String type,
            @RequestParam(required = false, name = "m") String muscle,
            @RequestParam(required = false, name = "e") String equipment,
            @RequestParam(required = false, name = "d") String difficulty,
            @RequestParam(required = false, name = "o", defaultValue = "0") int offset,
            @RequestParam(required = false, name = "l", defaultValue = "10") int limit
    ) {
        WorkoutSearchDto workoutSearchDto = new WorkoutSearchDto(name, type, muscle, equipment, difficulty, offset, limit);
        List<Workout> workoutList = workoutService.findWorkoutList(workoutSearchDto);
        System.out.println(workoutList.size());
        return new FindWorkoutListResponse(workoutList.size(), workoutList);
    }
    @Data
    @AllArgsConstructor
    static class FindWorkoutListResponse {
        private int count;
        private List<Workout> data;
    }
}
