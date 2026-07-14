package com.Lab04Backend.TaskFlow.repositories;

import com.Lab04Backend.TaskFlow.models.Members;
import com.Lab04Backend.TaskFlow.models.Teams;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MemberRepository extends JpaRepository<Members, UUID> {}
