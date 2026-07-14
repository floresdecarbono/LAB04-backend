package com.Lab04Backend.TaskFlow.services;

import com.Lab04Backend.TaskFlow.config.ResourceNotFoundException;
import com.Lab04Backend.TaskFlow.dtos.member.MemberRequest;
import com.Lab04Backend.TaskFlow.dtos.member.MemberResponse;
import com.Lab04Backend.TaskFlow.dtos.member.TeamMemberResponse;
import com.Lab04Backend.TaskFlow.models.Members;
import com.Lab04Backend.TaskFlow.models.Teams;
import com.Lab04Backend.TaskFlow.repositories.MemberRepository;
import com.Lab04Backend.TaskFlow.repositories.TeamsRepository;
import com.Lab04Backend.TaskFlow.teammember.repository.TeamMemberRepository;
import org.springframework.stereotype.Service;

import java.lang.reflect.Member;
import java.util.List;
import java.util.UUID;

@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final TeamsRepository teamsRepository;
    private final TeamMemberRepository teamMemberRepository;

    public MemberService(MemberRepository memberRepository, TeamsRepository teamsRepository, TeamMemberRepository teamMemberRepository) {
        this.memberRepository = memberRepository;
        this.teamsRepository = teamsRepository;
        this.teamMemberRepository = teamMemberRepository;
    }

    public MemberResponse createMember(MemberRequest request) {
        Members member = Members
                .builder()
                .user(request.user())
                .build();

        return MemberResponse.fromEntity(memberRepository.save(member));

    }

    public List<TeamMemberResponse> listAllMembersByTeam(UUID teamId) {
        Teams team = teamsRepository.findById(teamId)
                .orElseThrow(() -> new ResourceNotFoundException("Team with ID " + teamId + " not found."));

        return teamMemberRepository.findTeamMemberByTeam(team)
                .stream().map(teamMember -> TeamMemberResponse.fromEntity(teamMember.getMember(), teamMember)).toList();
    }

    public MemberResponse getMemberById(UUID id) {
        Members member = memberRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Member with ID " + id + " not found."));

        return MemberResponse.fromEntity(member);

    }

    public void deleteMember(UUID id) {
        if (memberRepository.existsById(id)) memberRepository.deleteById(id);
        else throw new ResourceNotFoundException("Member with ID " + id + " not found.");
    }

}
