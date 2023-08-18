package com.strmanager.api.domain;

import com.strmanager.api.dto.MemberCreateDto;
import com.strmanager.api.dto.MemberUpdateDto;
import com.strmanager.api.exception.EmptyIdParameterException;
import com.strmanager.api.exception.ShortPasswordException;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @Column(unique = true)
    private String email;
    private String password;
    private String name;
    private String phone;
    private int age;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private double weight;

    //cascade는 https://resilient-923.tistory.com/417 참고
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "performance_id")
    private Performance performance;

    public Member(MemberCreateDto memberCreateDto) {
        this.email = memberCreateDto.getEmail();
        this.password = memberCreateDto.getPassword();
        this.name = memberCreateDto.getName();
        this.phone = memberCreateDto.getPhone();
        this.age = memberCreateDto.getAge();
        this.gender = memberCreateDto.getGender();
        this.weight = memberCreateDto.getWeight();
        this.performance = new Performance();
    }

    //=====로직=====//
    //멤버 id가 파라미터에 같이 담겨와야한다.
    //email은 수정할 수 없다.
    //비밀번호는 8글자 이상이어야 한다.
    //공백으로 수정할 수 없다.
    public void update(MemberUpdateDto memberUpdateDto) {
        if (memberUpdateDto.getId() == 0) {
            throw new EmptyIdParameterException("id 파라미터 값이 없습니다.");
        }
        if (memberUpdateDto.getPassword() != null && !memberUpdateDto.getPassword().isEmpty()) {
            if (memberUpdateDto.getPassword().length() < 8) throw new ShortPasswordException("비밀번호는 8글자 이상이어야 합니다.");
            this.password = memberUpdateDto.getPassword();
        }
        if (memberUpdateDto.getName() != null && !memberUpdateDto.getName().isEmpty()) {
            this.name = memberUpdateDto.getName();
        }
        if (memberUpdateDto.getPhone() != null && !memberUpdateDto.getPhone().isEmpty()) {
            this.phone = memberUpdateDto.getPhone();
        }
        if (memberUpdateDto.getAge() > 0) {
            this.age = memberUpdateDto.getAge();
        }
        if (memberUpdateDto.getGender() != null && memberUpdateDto.getGender() != null) {
            this.gender = memberUpdateDto.getGender();
        }
        if (memberUpdateDto.getWeight() > 0) {
            this.weight = memberUpdateDto.getWeight();
        }

        if (memberUpdateDto.getBenchpress() > 0) {
            this.getPerformance().setBenchpress(memberUpdateDto.getBenchpress());
        }
        if (memberUpdateDto.getDeadlift() > 0) {
            this.getPerformance().setDeadlift(memberUpdateDto.getDeadlift());
        }
        if (memberUpdateDto.getSquat() > 0) {
            this.getPerformance().setSquat(memberUpdateDto.getSquat());
        }
        if (memberUpdateDto.getOverheadpress() > 0) {
            this.getPerformance().setOverheadpress(memberUpdateDto.getOverheadpress());
        }
        if (memberUpdateDto.getBarbellrow() > 0) {
            this.getPerformance().setBarbellrow(memberUpdateDto.getBarbellrow());
        }
    }

}
