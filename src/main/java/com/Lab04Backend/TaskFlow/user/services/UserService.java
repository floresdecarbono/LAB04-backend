package com.Lab04Backend.TaskFlow.user.services;

import com.Lab04Backend.TaskFlow.models.Members;
import com.Lab04Backend.TaskFlow.repositories.MemberRepository;
import com.Lab04Backend.TaskFlow.user.dtos.RegisterUserDTO;
import com.Lab04Backend.TaskFlow.user.entity.User;
import com.Lab04Backend.TaskFlow.user.exceptions.EmailAlreadyExistsException;
import com.Lab04Backend.TaskFlow.user.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final MemberRepository memberRepository;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public User register(RegisterUserDTO dto) {

        if (repository.existsByEmail(dto.email())) {
            throw new EmailAlreadyExistsException(dto.email());
        }

        User user = User.builder()
                .name(dto.name())
                .email(dto.email())
                .password(encoder.encode(dto.password()))
                .avatar(dto.avatar())
                .birthday(dto.birthday())
                .build();

        Members member = Members.builder()
                .user(user)
                .build();

        user = repository.save(user);
        memberRepository.save(member);
        return user;
    }
}
