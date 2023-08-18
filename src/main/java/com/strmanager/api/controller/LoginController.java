package com.strmanager.api.controller;

import com.strmanager.api.domain.Gender;
import com.strmanager.api.domain.Member;
import com.strmanager.api.domain.Performance;
import com.strmanager.api.dto.LoginDto;
import com.strmanager.api.exception.NotExistsEmailException;
import com.strmanager.api.exception.WrongPasswordException;
import com.strmanager.api.service.LoginService;
import lombok.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginDto loginDto) throws Exception {
        try {
            Member member = loginService.login(loginDto.getEmail(), loginDto.getPassword());
            return ResponseEntity.ok().body(new LoginResponse(new MemberDto(member)));
        } catch (NotExistsEmailException | WrongPasswordException e) {
            return ResponseEntity.badRequest().body(new LoginResponse(e.getMessage()));
        }
    }

    @Data
    @AllArgsConstructor
    private static class LoginResponse {
        private MemberDto data;
        private String message;
        public LoginResponse(MemberDto data) {
            this.data = data;
        }
        public LoginResponse(String message) {
            this.message = message;
        }
    }
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    private static class MemberDto {
        private Long id;
        private String email;
        private String name;
        private String phone;
        private int age;
        private Gender gender;
        private double weight;
        private double benchpress;
        private double deadlift;
        private double squat;
        private double overheadpress;
        private double barbellrow;
        public MemberDto(Member member) {
            this.id = member.getId();
            this.email = member.getEmail();
            this.name = member.getName();
            this.phone = member.getPhone();
            this.age = member.getAge();
            this.gender = member.getGender();
            this.weight = member.getWeight();
            this.benchpress = member.getPerformance().getBenchpress();
            this.deadlift = member.getPerformance().getDeadlift();
            this.squat = member.getPerformance().getSquat();
            this.overheadpress = member.getPerformance().getOverheadpress();
            this.barbellrow = member.getPerformance().getBarbellrow();
        }
    }
}
