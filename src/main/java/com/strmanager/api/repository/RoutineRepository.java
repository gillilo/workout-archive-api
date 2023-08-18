package com.strmanager.api.repository;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.strmanager.api.domain.Routine;
import com.strmanager.api.domain.RoutineDtl;
import com.strmanager.api.dto.RoutineSearchDto;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

import static com.strmanager.api.domain.QMember.member;
import static com.strmanager.api.domain.QRoutine.routine;
import static com.strmanager.api.domain.QRoutineDtl.routineDtl;


@Repository
public class RoutineRepository {

    private final EntityManager em;
    private final JPAQueryFactory query;

    public RoutineRepository(EntityManager em) {
        this.em = em;
        this.query = new JPAQueryFactory(em);
    }

    public void save(Routine routine) {
        em.persist(routine);
    }

    public void saveDtl(RoutineDtl routineDtl) {
        em.persist(routineDtl);
    }

    public List<Routine> findRoutines(RoutineSearchDto routineSearchDto) {
        return query
                .select(routine)
                .from(routine)
                .join(routine.member, member)
                .where(
                        member.id.eq(routineSearchDto.getMemberId()),
                        routine.planDate.eq(routineSearchDto.getPlanDate()),
                        workoutNameEq(routineSearchDto.getWorkoutName())
                )
                .orderBy(routine.mstSeq.asc())
                .fetch();
    }

    private BooleanExpression workoutNameEq(String workoutName) {
        return workoutName != null? routine.name.eq(workoutName) : null;
    }

    public Routine findById(long id) {
        return em.find(Routine.class, id);
    }

    public RoutineDtl findDtlById(long id) {
        return em.find(RoutineDtl.class, id);
    }

    public void deleteRoutine(long id) {
        Routine r = em.find(Routine.class, id);
        em.remove(r);
    }

    public List<RoutineDtl> findRoutineDtls(Long id) {
        return query
                .select(routineDtl)
                .from(routineDtl)
                .where(routineDtl.routine.id.eq(id))
                .orderBy(routineDtl.id.asc())
                .fetch();
    }

    public void deleteRoutineDtl(long id) {
        RoutineDtl d = em.find(RoutineDtl.class, id);
        em.remove(d);
    }
}
