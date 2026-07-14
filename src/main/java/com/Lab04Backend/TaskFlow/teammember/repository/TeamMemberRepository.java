package com.Lab04Backend.TaskFlow.teammember.repository;

import com.Lab04Backend.TaskFlow.models.Members;
import com.Lab04Backend.TaskFlow.models.Teams;
import com.Lab04Backend.TaskFlow.teammember.entity.TeamMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TeamMemberRepository extends JpaRepository<TeamMember, UUID> {

    List<TeamMember> findTeamMemberByTeam(Teams team);
    TeamMember findTeamMemberByMember(Members members);

}
