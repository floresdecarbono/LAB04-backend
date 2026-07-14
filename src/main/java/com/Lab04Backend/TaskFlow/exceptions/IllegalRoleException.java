package com.Lab04Backend.TaskFlow.exceptions;

import org.springframework.http.HttpStatus;

public class IllegalRoleException extends RuntimeException {

    public IllegalRoleException(String role) {
        super(
                "Role " + role + " isn't valid. Please, select a valid one."
        );
    }
}
