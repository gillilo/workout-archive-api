package com.strmanager.api.controller;

import com.strmanager.api.domain.Routine;
import com.strmanager.api.dto.RoutineCreateDto;
import com.strmanager.api.dto.RoutineDeleteDto;
import com.strmanager.api.dto.RoutineSearchDto;
import com.strmanager.api.dto.RoutineUpdateDto;
import com.strmanager.api.service.RoutineService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class RoutineController {

    private final RoutineService routineService;

    @PostMapping("/routine")
    public CreateRoutineResponse createRoutine(
            @RequestBody RoutineCreateDto routineCreateDto
    ) {
        return new CreateRoutineResponse(routineService.save(routineCreateDto));
    }
    @Data
    @AllArgsConstructor
    static class CreateRoutineResponse {
        private Long id;
    }

    @GetMapping("/routine")
    public SearchRoutinesResponse searchRoutines(
//            @RequestBody RoutineSearchDto routineSearchDto
            @RequestParam(required = false) long memberId,
            @RequestParam(required = false) String planDate,
            @RequestParam(required = false) String workoutName
    ) {
        RoutineSearchDto routineSearchDto = new RoutineSearchDto();
        routineSearchDto.setMemberId(memberId);
        routineSearchDto.setPlanDate(planDate);
        routineSearchDto.setWorkoutName(workoutName);
        List<Routine> routines = routineService.findRoutines(routineSearchDto);
        return new SearchRoutinesResponse(routines);
    }
    @Data
    @AllArgsConstructor
    static class SearchRoutinesResponse {
        private List<Routine> data;
    }

    @PatchMapping("/routine")
    public void UpdateRoutineResponse(
            @RequestBody RoutineUpdateDto routineUpdateDto
    ) {
        routineService.update(routineUpdateDto);
    }

    @GetMapping("/routine/autocreate")
    public ArrayList<String[]> autoPlan(
            @RequestParam("n") String name,
            @RequestParam("id") long memberId
    ) {
        if (name.equals("w")) {
            return routineService.getWendlerPlan(memberId);
        } else {
            return routineService.getStrongLiftPlan(memberId);
        }
    }

    @DeleteMapping("/routine/{id}")
    public void deleteRoutineDtl(
            @PathVariable("id") long id
    ) {
        routineService.deleteRoutine(id);
    }

}
