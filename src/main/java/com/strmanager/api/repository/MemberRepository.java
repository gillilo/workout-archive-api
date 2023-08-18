package com.strmanager.api.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.strmanager.api.domain.Member;
import com.strmanager.api.domain.QMember;
import com.strmanager.api.domain.QPerformance;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

import static com.strmanager.api.domain.QMember.member;
import static com.strmanager.api.domain.QPerformance.performance;

@Repository
public class MemberRepository {

    private final EntityManager em;
    private final JPAQueryFactory query;

    public MemberRepository(EntityManager em) {
        this.em = em;
        this.query = new JPAQueryFactory(em);
    }

    public void save(Member member) {
        em.persist(member);
    }

    public Member findOne(Long id) {
        return em.find(Member.class, id);
    }

    public List<Member> findByEmail(String email) {
        return query
                .select(member)
                .from(member)
                .join(member.performance, performance)
                .where(member.email.eq(email))
                .fetch();
    }

}
