package com.strmanager.api.service;

import com.strmanager.api.domain.Member;
import com.strmanager.api.exception.NotExistsEmailException;
import com.strmanager.api.exception.WrongPasswordException;
import com.strmanager.api.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LoginService {

    private final MemberRepository memberRepository;

    public Member login(String email, String password) throws Exception {

        List<Member> memberList = memberRepository.findByEmail(email);

        if (memberList.size() == 0) throw new NotExistsEmailException("This account does not exist.");

        if (!memberList.get(0).getPassword().equals(password)) throw new WrongPasswordException("Passwords do not match.");

        return memberList.get(0);
    }

}
