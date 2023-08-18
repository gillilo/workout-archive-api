package com.strmanager.api.controller;

import com.strmanager.api.domain.Performance;
import com.strmanager.api.dto.PerformanceUpdateDto;
import com.strmanager.api.service.MemberService;
import com.strmanager.api.service.PerformanceService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class PerformanceController {

    private final PerformanceService performanceService;
    private final MemberService memberService;

    @PatchMapping("/performance")
    public void updatePerformance(@RequestBody PerformanceUpdateDto performanceUpdateDto) {
        performanceService.updatePerformance(
            performanceUpdateDto.getMemberId(),
            performanceUpdateDto.getBenchpress(),
            performanceUpdateDto.getDeadlift(),
            performanceUpdateDto.getSquat(),
            performanceUpdateDto.getOverheadpress(),
            performanceUpdateDto.getBarbellrow()
        );
    }

    @GetMapping("/performance/{memberId}")
    public GetPerformanceResponse getPerformanceResponse(
            @PathVariable("memberId") long memberId
    ) {
        Performance performance = performanceService.findByMemberId(memberId);
        return new GetPerformanceResponse(performance);
    }

    @Data
    @AllArgsConstructor
    private static class GetPerformanceResponse {
        private Performance performance;
    }

}
