package com.strmanager.api.service;

import com.strmanager.api.domain.Gender;
import com.strmanager.api.domain.Member;
import com.strmanager.api.dto.MemberCreateDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class LoginServiceTest {

    @Autowired LoginService loginService;
    @Autowired EntityManager em;

    @Test
    public void 로그인_성공() throws Exception {
        //given
        Member member = createMember("test", "password");

        //when
        Member login = loginService.login("test", "password");

        //then
        assertEquals(member.getId(), login.getId());
    }

    @Test
    public void 로그인_실패_email_없음() throws Exception {
        //given
        createMember("있는 email", "test");

        //when

        //then
        assertThrows(
                Exception.class,
                () -> loginService.login("없는 email", "test")
        );
    }

    @Test
    public void 로그인_실패_비밀번호_틀림() throws Exception {
        //given
        createMember("test", "맞는 비밀번호");

        //when

        //then
        assertThrows(
                Exception.class,
                () -> loginService.login("test", "틀린 비밀번호")
        );
    }

    private Member createMember(String email, String password) {
        MemberCreateDto memberCreateDto = new MemberCreateDto(
                "name",
                email,
                password,
                "010-1111-1111",
                30,
                Gender.MALE,
                100
        );
        Member member = new Member(memberCreateDto);
        em.persist(member);
        return member;
    }

}