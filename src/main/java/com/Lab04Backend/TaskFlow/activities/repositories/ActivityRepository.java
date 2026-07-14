package com.Lab04Backend.TaskFlow.activities.repositories;

import com.Lab04Backend.TaskFlow.activities.entity.Activity;
import com.Lab04Backend.TaskFlow.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActivityRepository extends JpaRepository<Activity, String> {

    List<Activity> findTop7ByUserOrderByCreatedAtDesc(User user);

}