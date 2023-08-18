package com.strmanager.api.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.strmanager.api.dto.RoutineDtlUpdateDto;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class RoutineDtl {

    @Id
    @GeneratedValue
    @Column(name = "routine_dtl_id")
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "routine_id")
    private Routine routine;

    private int setNumber;

    private double weight;
    private double targetCount;
    private double actualCount;
    private boolean done;

    //====생성 메서드====//
    public static RoutineDtl createRoutineDtl(int setNumber, double weight, double targetCount) {
        RoutineDtl set = new RoutineDtl();
        set.setSetNumber(setNumber);
        set.setWeight(weight);
        set.setTargetCount(targetCount);
        set.setDone(false);
        return set;
    }

    public static RoutineDtl createRoutineDtl(Routine routine, RoutineDtlUpdateDto dto) {
        RoutineDtl dtl = new RoutineDtl();
        dtl.setRoutine(routine);
        dtl.setSetNumber(dto.getSetNumber());
        dtl.setWeight(dto.getWeight());
        dtl.setTargetCount(dto.getTargetCount());
        dtl.setActualCount(dto.getActualCount());
        dtl.setDone(dto.isDone());
        return dtl;
    }

    public void update(RoutineDtlUpdateDto dto) {
        this.weight = dto.getWeight();
        this.targetCount = dto.getTargetCount();
        this.actualCount = dto.getActualCount();
        this.done = dto.isDone();
    }
}
