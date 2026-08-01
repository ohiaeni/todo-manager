package com.todomanager.presentation;

import static org.assertj.core.api.Assertions.assertThat;

import com.todomanager.application.TaskResponse;
import com.todomanager.application.TasksApplicationService;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = {
        "spring.datasource.url=jdbc:h2:mem:todomanager-seeded;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE"
})
class TasksApiSeededIntegrationTest {

    @Autowired
    private TasksApplicationService tasksApplicationService;

    @Test
    void returnsSeededTasksFromSqlInitialization() throws Exception {
        List<TaskResponse> tasks = tasksApplicationService.getTasks();

        assertThat(tasks).hasSize(3);
        assertThat(tasks.get(0)).isEqualTo(new TaskResponse(1L, "Buy groceries", false));
    }
}
