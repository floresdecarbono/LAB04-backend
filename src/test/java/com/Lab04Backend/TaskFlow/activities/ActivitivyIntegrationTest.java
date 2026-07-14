package com.Lab04Backend.TaskFlow.activities;

import com.Lab04Backend.TaskFlow.activities.entity.Activity;
import com.Lab04Backend.TaskFlow.activities.repositories.ActivityRepository;
import com.Lab04Backend.TaskFlow.activities.services.ActivityService;
import com.Lab04Backend.TaskFlow.user.entity.User;
import com.Lab04Backend.TaskFlow.user.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class ActivityIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private ActivityService activityService;


    @Test
    void shouldReturnLastSevenActivities() throws Exception {

        User user = User.builder()
                .name("João")
                .email("joao@email.com")
                .password("123")
                .build();

        user = userRepository.save(user);

        for (int i = 1; i <= 10; i++) {

            activityService.register(
                    user,
                    "Atividade " + i
            );

        }

        mockMvc.perform(
                        get("/api/users/{id}/activities", user.getId())
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(7))
                .andExpect(jsonPath("$[0].description").value("Atividade 10"))
                .andExpect(jsonPath("$[6].description").value("Atividade 4"));

    }

    @Test
    void shouldReturn404WhenUserDoesNotExist() throws Exception {

        mockMvc.perform(
                        get("/api/users/123/activities")
                )
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnEmptyList() throws Exception {

        User user = User.builder()
                .name("Maria")
                .email("maria@email.com")
                .password("123")
                .build();

        user = userRepository.save(user);

        mockMvc.perform(
                        get("/api/users/{id}/activities", user.getId())
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(0));

    }
    @Test
    void shouldSaveActivity() {

        User user = User.builder()
                .name("Carlos")
                .email("carlos@email.com")
                .password("123")
                .build();

        user = userRepository.save(user);

        activityService.register(
                user,
                "Criou um Board"
        );

        List<Activity> activities =
                activityRepository.findTop7ByUserOrderByCreatedAtDesc(user);

        assertEquals(1, activities.size());

        assertEquals(
                "Criou um Board",
                activities.getFirst().getDescription()
        );
    }

}