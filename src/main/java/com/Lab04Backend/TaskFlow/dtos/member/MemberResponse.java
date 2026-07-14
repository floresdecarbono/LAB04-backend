package com.Lab04Backend.TaskFlow.dtos.member;

import com.Lab04Backend.TaskFlow.models.Members;

import java.util.UUID;

public record MemberResponse(UUID id, String name, String email) {

    public static MemberResponse fromEntity(Members member) {
        return new MemberResponse(member.getId(), member.getUser().getName(), member.getUser().getEmail());
    }
}
