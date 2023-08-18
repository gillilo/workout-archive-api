package com.strmanager.api.repository;

import com.strmanager.api.domain.Performance;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class PerformanceRepository {

    private final EntityManager em;

    public void save(Performance performance) {
        em.persist(performance);
    }

    public Performance findByMemberId(Long memberId) {
        return em.createQuery("select p from Performance p join p.member m where m.id = :id", Performance.class)
                .setParameter("id", memberId)
                .getResultList().get(0);
    }

}
