package com.strmanager.api.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Performance {

    @Id
    @GeneratedValue
    @Column(name = "performance_id")
    private Long id;

    @JsonIgnore
    @OneToOne(mappedBy = "performance", fetch = FetchType.LAZY)
    private Member member;

    private double benchpress;
    private double deadlift;
    private double squat;
    private double overheadpress;
    private double barbellrow;

    //====생성 메서드====
    public static Performance createPerformance(Member member, double benchpress, double deadlift, double squat, double overheadpress, double barbellrow) {
        Performance performance = new Performance();
        performance.setMember(member);
        performance.setBenchpress(benchpress);
        performance.setDeadlift(deadlift);
        performance.setSquat(squat);
        performance.setOverheadpress(overheadpress);
        performance.setBarbellrow(barbellrow);
        return performance;
    }

    //====수정 메서드====
    public void updatePerformance(double benchpress, double deadlift, double squat, double overheadpress, double barbellrow) {
        if(benchpress > 0) this.benchpress = benchpress;
        if(deadlift > 0) this.deadlift = deadlift;
        if(squat > 0) this.squat = squat;
        if(overheadpress > 0) this.overheadpress = overheadpress;
        if(barbellrow > 0) this.barbellrow = barbellrow;
    }

}
