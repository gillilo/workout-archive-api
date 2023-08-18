package com.strmanager.api.service;

import com.strmanager.api.domain.Performance;
import com.strmanager.api.repository.MemberRepository;
import com.strmanager.api.repository.PerformanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PerformanceService {

    private final MemberRepository memberRepository;
    private final PerformanceRepository performanceRepository;

    public Performance findByMemberId(Long memberId) {
        return performanceRepository.findByMemberId(memberId);
    }

    @Transactional
    public void updatePerformance(
            long memberId,
            double benchpress,
            double deadlift,
            double squat,
            double overheadpress,
            double barbellrow
    ) {
        Performance performance = performanceRepository.findByMemberId(memberId);
        performance.updatePerformance(benchpress, deadlift, squat, overheadpress, barbellrow);
    }

}
