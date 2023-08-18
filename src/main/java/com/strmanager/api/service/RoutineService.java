package com.strmanager.api.service;

import com.strmanager.api.domain.Member;
import com.strmanager.api.domain.Routine;
import com.strmanager.api.domain.RoutineDtl;
import com.strmanager.api.dto.*;
import com.strmanager.api.repository.MemberRepository;
import com.strmanager.api.repository.RoutineRepository;
import com.strmanager.api.utils.StrongLift;
import com.strmanager.api.utils.Wendler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RoutineService {

    private final MemberRepository memberRepository;
    private final RoutineRepository routineRepository;
    private final StrongLift strongLift;
    private final Wendler wendler;

    @Transactional
    public long save(RoutineCreateDto createRoutineDto) {
        Member member = memberRepository.findOne(createRoutineDto.getMemberId());

        List<RoutineDtl> routineDtls = new ArrayList<>();
        for (RoutineDtlCreateDto dto : createRoutineDto.getDtls()) {
            RoutineDtl dtl = RoutineDtl.createRoutineDtl(
                    dto.getSetNumber(),
                    dto.getWeight(),
                    dto.getTargetCount()
            );
            routineDtls.add(dtl);
        }

        Routine routine = Routine.createRoutine(
                member,
                createRoutineDto.getWorkoutName(),
                createRoutineDto.getPlanDate(),
                routineDtls,
                createRoutineDto.getMstSeq()
        );

        routineRepository.save(routine);

        return routine.getId();
    }

    public List<Routine> findRoutines(RoutineSearchDto routineSearchDto) {
        List<Routine> resultList = routineRepository.findRoutines(routineSearchDto);
        resultList.forEach(routine -> {
            routine.setRoutineDtls(
                    routineRepository.findRoutineDtls(routine.getId())
            );
        });
        return resultList;
    }

    @Transactional
    public void update(RoutineUpdateDto routineUpdateDto) {
        Routine routine = routineRepository.findById(routineUpdateDto.getId());
        routine.update(routineUpdateDto);
        ArrayList<RoutineDtlUpdateDto> updateDtoDtls = routineUpdateDto.getDtls();
        for (RoutineDtlUpdateDto dto : updateDtoDtls) {
            if (dto.getId() > 0) {
                RoutineDtl dtl = routineRepository.findDtlById(dto.getId());
                dtl.update(dto);
            } else {
                RoutineDtl routineDtl = RoutineDtl.createRoutineDtl(routine, dto);
                routineRepository.saveDtl(routineDtl);
            }
        }
    }

    public ArrayList<String[]> getStrongLiftPlan(long memberId) {
        Member member = memberRepository.findOne(memberId);
        return strongLift.createRoutine(member, 5);
    }

    public ArrayList<String[]> getWendlerPlan(long memberId) {
        Member member = memberRepository.findOne(memberId);
        return wendler.createRoutine(member, 5);
    }

    @Transactional
    public void deleteRoutineDtl(long id) {
        routineRepository.deleteRoutineDtl(id);
    }

    @Transactional
    public void deleteRoutine(long id) {
        routineRepository.deleteRoutine(id);
    }
}
