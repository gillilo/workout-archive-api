package com.strmanager.api.service;

import com.strmanager.api.domain.Gender;
import com.strmanager.api.domain.Member;
import com.strmanager.api.dto.MemberCreateDto;
import com.strmanager.api.dto.MemberUpdateDto;
import com.strmanager.api.exception.AlreadyExistsMemberException;
import com.strmanager.api.exception.ShortPasswordException;
import com.strmanager.api.exception.WrongPasswordException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired LoginService loginService;
    @Autowired EntityManager em;

    @Test
    public void 맴버_생성() throws Exception {
        //given
        createMember("이미 존재하는 email");

        //when
        MemberCreateDto createDto = createMemberCreateDto("존재하지 않는 email");

        //then
        assertDoesNotThrow(() -> memberService.join(createDto));
    }

    @Test
    public void 맴버_생성_실패() throws Exception {
        //given
        createMember("이미 존재하는 email");

        //when
        MemberCreateDto createDto = createMemberCreateDto("이미 존재하는 email");

        //then
        assertThrows(AlreadyExistsMemberException.class, () -> memberService.join(createDto));
    }

    @Test
    public void 맴버_비밀번호_수정_성공_8글자이상() throws Exception {
        //given
        MemberCreateDto member = new MemberCreateDto(
            "name","email","password","010-1111-1111",30,Gender.MALE,100
        );
        Long joinId = memberService.join(member);

        //when
        MemberUpdateDto dto = new MemberUpdateDto();
        dto.setId(joinId);
        dto.setPassword("12345678");

        //then
        assertDoesNotThrow(() -> memberService.update(dto), "8글자 이상 Password 수정 완료");
        assertDoesNotThrow(() -> loginService.login(member.getEmail(), dto.getPassword()), "수정 된 Password로 로그인");

        Member afterUpdate = loginService.login(member.getEmail(), dto.getPassword());
        assertEquals(afterUpdate.getPassword(), dto.getPassword(), "Password 입력값으로 수정 완료");
        assertNotEquals(member.getPassword(), afterUpdate.getPassword(), "Password 수정 완료");
        assertEquals(afterUpdate.getEmail(), member.getEmail(), "Password 외 수정 X - email");
        assertEquals(afterUpdate.getName(), member.getName(), "Password 외 수정 X - name");
        assertEquals(afterUpdate.getPhone(), member.getPhone(), "Password 외 수정 X - phone");
        assertEquals(afterUpdate.getAge(), member.getAge(), "Password 외 수정 X - age");
        assertEquals(afterUpdate.getGender(), member.getGender(), "Password 외 수정 X - gender");
        assertEquals(afterUpdate.getWeight(), member.getWeight(), "Password 외 수정 X - weigth");
    }

    @Test
    public void 맴버_비밀번호_수정_실패_8글자이하() throws Exception {
        //given
        MemberCreateDto member = new MemberCreateDto(
            "name","email","password","010-1111-1111",30,Gender.MALE,100
        );
        Long joinId = memberService.join(member);

        //when
        MemberUpdateDto dto = new MemberUpdateDto();
        dto.setId(joinId);
        dto.setPassword("1234567");

        //then
        assertThrows(
                ShortPasswordException.class,
                () -> memberService.update(dto),
                "8글자 이하 페스워드 수정 실패"
        );
        assertThrows(
                WrongPasswordException.class,
                () -> loginService.login(member.getEmail(), dto.getPassword()),
                "수정 된 Password로 로그인 실패"
        );

        Member afterUpdate = loginService.login(member.getEmail(), member.getPassword());
        assertEquals(afterUpdate.getPassword(), member.getPassword(), "Password 수정 실패 시 수정 X - password");
        assertEquals(afterUpdate.getEmail(), member.getEmail(), "Password 수정 실패 시 수정 X - email");
        assertEquals(afterUpdate.getName(), member.getName(), "Password 수정 실패 시 수정 X - name");
        assertEquals(afterUpdate.getPhone(), member.getPhone(), "Password 수정 실패 시 수정 X - phone");
        assertEquals(afterUpdate.getAge(), member.getAge(), "Password 수정 실패 시 수정 X - age");
        assertEquals(afterUpdate.getGender(), member.getGender(), "Password 수정 실패 시 수정 X - gender");
        assertEquals(afterUpdate.getWeight(), member.getWeight(), "Password 수정 실패 시 수정 X - weigth");
    }

    @Test
    public void 맴버정보_수정() throws Exception {
        //given
        MemberCreateDto member = new MemberCreateDto(
                "name","email","password","010-1111-1111",30,Gender.MALE,100
        );
        Long joinId = memberService.join(member);

        MemberUpdateDto dto = new MemberUpdateDto();
        dto.setId(joinId);
        dto.setName("새 이름");
        dto.setWeight(80);
        //when
        memberService.update(dto);

        //then
        Member afterUpdate = loginService.login(member.getEmail(), member.getPassword());

        assertEquals(afterUpdate.getName(), dto.getName(), "name 수정");
        assertEquals(afterUpdate.getWeight(), dto.getWeight(), "weigth 수정");
        assertNotEquals(afterUpdate.getName(), member.getName(), "name 수정");
        assertNotEquals(afterUpdate.getWeight(), member.getWeight(), "weigth 수정");

        assertEquals(afterUpdate.getPassword(), member.getPassword(), "Password 수정 X");
        assertEquals(afterUpdate.getEmail(), member.getEmail(), "email 수정 X");
        assertEquals(afterUpdate.getPhone(), member.getPhone(), "phone 수정 X");
        assertEquals(afterUpdate.getAge(), member.getAge(), "age 수정 X");
        assertEquals(afterUpdate.getGender(), member.getGender(), "gender 수정 X");
    }

    private Member createMember(String email) {
        MemberCreateDto memberCreateDto = new MemberCreateDto(
                "name",
                email,
                "password",
                "010-1111-1111",
                30,
                Gender.MALE,
                100
        );
        Member member = new Member(memberCreateDto);
        em.persist(member);
        return member;
    }

    private MemberCreateDto createMemberCreateDto(String email) {
        MemberCreateDto memberCreateDto = new MemberCreateDto(
                "name",
                email,
                "password",
                "010-1111-1111",
                30,
                Gender.MALE,
                100
        );
        return memberCreateDto;
    }
}