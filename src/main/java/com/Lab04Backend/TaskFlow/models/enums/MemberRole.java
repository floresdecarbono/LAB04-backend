package com.Lab04Backend.TaskFlow.models.enums;

import com.Lab04Backend.TaskFlow.exceptions.IllegalRoleException;

public enum MemberRole {
    READ("read"),
    MEMBER("member"),
    ADMIN("admin"),
    ;

    private String label;
    MemberRole(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public MemberRole setLabel(String label) {
        return switch (label.toLowerCase()) {
            case "read" -> MemberRole.READ;
            case "member" -> MemberRole.MEMBER;
            case "admin" -> MemberRole.ADMIN;
            default -> throw new IllegalRoleException(label);
        };
    }
}
