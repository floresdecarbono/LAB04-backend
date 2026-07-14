package com.Lab04Backend.TaskFlow.models;

import com.Lab04Backend.TaskFlow.models.enums.MemberRole;
import com.Lab04Backend.TaskFlow.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor

@Data
@Builder
@Entity
public class Members {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false,
                updatable = false, unique = true)
    @NonNull private User user;

}
