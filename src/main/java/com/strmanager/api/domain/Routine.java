package com.strmanager.api.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.strmanager.api.dto.RoutineCreateDto;
import com.strmanager.api.dto.RoutineDtlCreateDto;
import com.strmanager.api.dto.RoutineUpdateDto;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Routine {

    @Id
    @GeneratedValue
    @Column(name = "routine_id")
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private String name;
    private String planDate;

    private int mstSeq;

    @OneToMany(mappedBy = "routine", cascade = CascadeType.ALL)
    private List<RoutineDtl> routineDtls = new ArrayList<>();

    private void addRoutineDtl(RoutineDtl dtl) {
        routineDtls.add(dtl);
        dtl.setRoutine(this);
    }

    //==생성 메서드==//
    public static Routine createRoutine(
            Member member,
            String name,
            String planDate,
            List<RoutineDtl> routineDtls,
            int mstSeq
    ) {
        Routine routine = new Routine();
        routine.setMember(member);
        routine.setName(name);
        routine.setPlanDate(planDate);
        routine.setMstSeq(mstSeq);
        for (RoutineDtl dtl : routineDtls) {
            routine.addRoutineDtl(dtl);
        }
        return routine;
    }

    public void update(RoutineUpdateDto routineUpdateDto) {
        this.name = routineUpdateDto.getWorkoutName();
    }

}
