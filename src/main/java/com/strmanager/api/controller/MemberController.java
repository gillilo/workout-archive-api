package com.strmanager.api.controller;

import com.strmanager.api.dto.MemberCreateDto;
import com.strmanager.api.dto.MemberUpdateDto;
import com.strmanager.api.exception.AlreadyExistsMemberException;
import com.strmanager.api.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/members")
    public ResponseEntity<String> createMember(@RequestBody MemberCreateDto memberCreateDto) {
        try {
            Long id = memberService.join(memberCreateDto);

            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/members")
                    .buildAndExpand(id)
                    .toUri();

            return ResponseEntity.ok().body(id.toString());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PatchMapping("/members")
    public void updateMember(@RequestBody MemberUpdateDto memberUpdateDto) {
        memberService.update(memberUpdateDto);
    }

}
