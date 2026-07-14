package com.Lab04Backend.TaskFlow.services;

import com.Lab04Backend.TaskFlow.boards.config.ResourceNotFoundException;
import com.Lab04Backend.TaskFlow.dtos.teams.TeamsRequest;
import com.Lab04Backend.TaskFlow.dtos.teams.TeamsResponse;
import com.Lab04Backend.TaskFlow.models.Members;
import com.Lab04Backend.TaskFlow.models.Teams;
import com.Lab04Backend.TaskFlow.models.enums.MemberRole;
import com.Lab04Backend.TaskFlow.repositories.MemberRepository;
import com.Lab04Backend.TaskFlow.repositories.TeamsRepository;
import com.Lab04Backend.TaskFlow.teammember.entity.TeamMember;
import com.Lab04Backend.TaskFlow.teammember.repository.TeamMemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TeamsService {

    private final TeamsRepository teamsRepository;
    private final MemberRepository memberRepository;
    private final TeamMemberRepository teamMemberRepository;

    public TeamsService(TeamsRepository teamsRepository, MemberRepository memberRepository, TeamMemberRepository teamMemberRepository) {
        this.teamsRepository = teamsRepository;
        this.memberRepository = memberRepository;
        this.teamMemberRepository = teamMemberRepository;
    }

    public TeamsResponse createTeam(TeamsRequest request) {
        Teams teams = Teams.builder()
                .name(request.name())
                .build();

        return TeamsResponse.fromEntity(teamsRepository.save(teams));
    }

    public List<TeamsResponse> findAll() {
        return teamsRepository.findAll().stream().map(TeamsResponse::fromEntity).toList();
    }

    public TeamsResponse findOne(UUID id) {
        Teams team = teamsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Team with ID doesn't exist."));

        return TeamsResponse.fromEntity(team);
    }

    public TeamsResponse addToTeam(UUID teamId, UUID memberId) {
        Teams team = teamsRepository.findById(teamId)
                .orElseThrow(() -> new ResourceNotFoundException("Team with ID not found."));
        Members member = memberRepository.findById(memberId)
                .orElseThrow(() -> new ResourceNotFoundException("Team with ID not found."));

        TeamMember teamMember = TeamMember.builder()
                        .member(member)
                        .team(team)
                        .role(MemberRole.MEMBER)
                        .build();

        team.addMember(teamMemberRepository.save(teamMember));
        return TeamsResponse.fromEntity(team);
    }

    public void removeFromTeam(UUID teamId, UUID memberId) {
        Teams team = teamsRepository.findById(teamId)
                .orElseThrow(() -> new ResourceNotFoundException("Team with ID not found."));
        Members member = memberRepository.findById(memberId)
                .orElseThrow(() -> new ResourceNotFoundException("Member with this ID not found."));
        TeamMember teamMember = teamMemberRepository.findTeamMemberByMember(member);

        if (teamMember.getTeam().equals(team)) {
            team.removeMember(teamMember);
            teamMemberRepository.delete(teamMember);
        } else {
            throw new IllegalArgumentException();
        }
    }

    public TeamsResponse update(TeamsRequest request) {
        if (teamsRepository.existsById(request.id())) {
            return TeamsResponse.fromEntity(teamsRepository.save(Teams.builder()
                    .id(request.id())
                    .name(request.name())
                    .build()));
        }
        else throw new IllegalArgumentException("Team with this ID doesn't exist.");
    }

    public void delete(UUID id) {
        if (teamsRepository.existsById(id)) teamsRepository.deleteById(id);
    }

}
