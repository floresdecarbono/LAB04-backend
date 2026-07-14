package com.Lab04Backend.TaskFlow.models;

import com.Lab04Backend.TaskFlow.exceptions.IllegalRoleException;
import com.Lab04Backend.TaskFlow.models.enums.MemberRole;
import com.Lab04Backend.TaskFlow.teammember.entity.TeamMember;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor

@Builder
@Data
@Entity
public class Teams {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @NonNull private String name;

    @OneToMany(fetch = FetchType.LAZY)
    private Set<TeamMember> members;


    public void addMember(TeamMember member) {
        if (member.getRole() == MemberRole.READ) throw new IllegalRoleException("Viewers cannot be added in teams");
        members.add(member);
    }

    public void removeMember(TeamMember member) {
        members.removeIf(m -> m.equals(member));
    }

}
