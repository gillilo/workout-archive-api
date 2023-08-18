package com.strmanager.api.service;

import com.strmanager.api.domain.Gender;
import com.strmanager.api.domain.Performance;
import com.strmanager.api.dto.MemberCreateDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class PerformanceServiceTest {

    @Autowired MemberService memberService;
    @Autowired PerformanceService performanceService;

    @Test
    public void 수행_능력_조회() throws Exception {
        //given
        MemberCreateDto member = new MemberCreateDto(
                "name","email","password","010-1111-1111",30, Gender.MALE,100
        );
        Long joinId = memberService.join(member);

        //when
        Performance performance = performanceService.findByMemberId(joinId);

        //then
        assertEquals(performance.getBenchpress(), 0, "회원 가입시 자동으로 초기화되어 생성된다.");
    }

    @Test
    public void 수행_능력_수정() throws Exception {
        //given
        MemberCreateDto member = new MemberCreateDto(
                "name","email","password","010-1111-1111",30, Gender.MALE,100
        );
        Long joinId = memberService.join(member);

        //when
        performanceService.updatePerformance(joinId, 1,2,3,4,5);

        //then
        Performance performance = performanceService.findByMemberId(joinId);
        assertEquals(performance.getBenchpress(), 1, "수정 완료 - benchpress");
        assertEquals(performance.getDeadlift(), 2, "수정 완료 - deadlift");
        assertEquals(performance.getSquat(), 3, "수정 완료 - squat");
        assertEquals(performance.getOverheadpress(), 4, "수정 완료 - overheadpress");
        assertEquals(performance.getBarbellrow(), 5, "수정 완료 - barbellrow");
    }

}