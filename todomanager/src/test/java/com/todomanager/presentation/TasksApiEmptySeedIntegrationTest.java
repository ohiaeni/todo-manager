package com.todomanager.presentation;

import static org.assertj.core.api.Assertions.assertThat;

import com.todomanager.application.TasksApplicationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("empty-seed")
class TasksApiEmptySeedIntegrationTest {

    @Autowired
    private TasksApplicationService tasksApplicationService;

    @Test
    void returnsEmptyListWhenSeedRowsAreAbsent() throws Exception {
        assertThat(tasksApplicationService.getTasks(1L)).isEmpty();
    }
}
