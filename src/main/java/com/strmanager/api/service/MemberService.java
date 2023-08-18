package com.strmanager.api.service;

import com.strmanager.api.domain.Member;
import com.strmanager.api.dto.MemberCreateDto;
import com.strmanager.api.dto.MemberUpdateDto;
import com.strmanager.api.exception.AlreadyExistsMemberException;
import com.strmanager.api.exception.ShortPasswordException;
import com.strmanager.api.repository.MemberRepository;
import com.strmanager.api.repository.PerformanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PerformanceRepository performanceRepository;

    // 회원 가입
    @Transactional
    public Long join(MemberCreateDto memberCreateDto) {

        validateDuplicateEmail(memberCreateDto.getEmail()); // 이메일 중복 검증

        validatePasswordLength(memberCreateDto.getPassword()); // 비밀번호 길이 검증

        Member member = new Member(memberCreateDto);
        memberRepository.save(member);

        return member.getId();
    }

    // 회원 정보 수정
    @Transactional
    public void update(MemberUpdateDto memberUpdateDto) {
        Member member = memberRepository.findOne(memberUpdateDto.getId());
        member.update(memberUpdateDto);
    }

    private void validateDuplicateEmail(String email) {
        List<Member> findMembers = memberRepository.findByEmail(email);
        if (!findMembers.isEmpty()) {
            throw new AlreadyExistsMemberException("This email already exists.");
        }
    }

    private void validatePasswordLength(String password) {
        if (password.length() < 8) {
            throw new ShortPasswordException("Password must be 8 characters or longer.");
        }
    }

}
