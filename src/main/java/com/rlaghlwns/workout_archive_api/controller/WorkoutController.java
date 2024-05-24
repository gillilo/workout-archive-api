package com.rlaghlwns.workout_archive_api.controller;

import com.rlaghlwns.workout_archive_api.domain.Workout;
import com.rlaghlwns.workout_archive_api.dto.WorkoutSearchDto;
import com.rlaghlwns.workout_archive_api.service.WorkoutService;
import lombok.*;
import org.springframework.web.bind.annotation.*;

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

    //select '{!name!:!'||name||'!,!type!:!'||type||'!,!muscle!:!'||muscle||'!,!equipment!:!'||equipment||'!,!difficulty!:!'||difficulty||'!,!instructions!:!'||instructions||'!},' from workouts_tmp
//    private final EntityManager em;
    @PostMapping("/workout/dumpinsert")
    public void dumpinsert(
            @RequestBody List<DumpDto> list
    ) {
        list.forEach(dumpDto -> {
            Workout workout = Workout.createWorkout(
                    dumpDto.name,
                    dumpDto.type,
                    dumpDto.muscle,
                    dumpDto.equipment,
                    dumpDto.difficulty,
                    dumpDto.instructions
            );
            workoutService.save(workout);
        });
    }
    @Data
    @Getter @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    private static class DumpDto {
        private String name;
        private String type;
        private String muscle;
        private String equipment;
        private String difficulty;
        private String instructions;
    }
}
