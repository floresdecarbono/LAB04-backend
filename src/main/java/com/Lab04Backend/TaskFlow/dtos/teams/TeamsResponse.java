package com.Lab04Backend.TaskFlow.dtos.teams;

import com.Lab04Backend.TaskFlow.models.Members;
import com.Lab04Backend.TaskFlow.models.Teams;
import com.Lab04Backend.TaskFlow.teammember.entity.TeamMember;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public record TeamsResponse(UUID id, String name, Set<TeamMember> members) {

    public static TeamsResponse fromEntity(Teams team) {
        return new TeamsResponse(team.getId(), team.getName(), team.getMembers());
    }
}
