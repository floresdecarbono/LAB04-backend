package com.Lab04Backend.TaskFlow.members.controllers;

import com.Lab04Backend.TaskFlow.dtos.member.MemberRequest;
import com.Lab04Backend.TaskFlow.dtos.member.MemberResponse;
import com.Lab04Backend.TaskFlow.dtos.member.TeamMemberResponse;
import com.Lab04Backend.TaskFlow.services.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/api/members")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping
    ResponseEntity<MemberResponse> createMember(@RequestBody MemberRequest request) {
        MemberResponse response = memberService.createMember(request);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/team//{teamId}")
    ResponseEntity<List<TeamMemberResponse>> listMembersByTeam(@PathVariable UUID teamId) {
        return ResponseEntity.ok().body(memberService.listAllMembersByTeam(teamId));
    }

    @GetMapping("/{id}")
    ResponseEntity<MemberResponse> getMemberById(@PathVariable UUID id) {
        MemberResponse response = memberService.getMemberById(id);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteMemberById(@PathVariable UUID id) {
        memberService.deleteMember(id);
        return ResponseEntity.noContent().build();
    }
}
