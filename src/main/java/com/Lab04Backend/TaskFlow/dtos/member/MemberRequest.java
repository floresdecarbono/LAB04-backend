package com.Lab04Backend.TaskFlow.dtos.member;

import com.Lab04Backend.TaskFlow.user.entity.User;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record MemberRequest(UUID id, @NotNull(message = "Every member has to be attached to an user") User user) {}
