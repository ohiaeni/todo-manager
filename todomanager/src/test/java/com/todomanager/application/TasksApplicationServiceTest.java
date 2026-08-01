package com.todomanager.application;

import static org.assertj.core.api.Assertions.assertThat;

import com.todomanager.domain.Task;
import com.todomanager.domain.TaskRepository;
import java.util.List;
import org.junit.jupiter.api.Test;

class TasksApplicationServiceTest {

    @Test
    void returnsMappedTasksWhenTasksExist() {
        TaskRepository repository = () -> List.of(
                new Task(1L, "Buy milk", false),
                new Task(2L, "Write tests", true));
        TasksApplicationService service = new TasksApplicationService(repository);

        List<TaskResponse> responses = service.getTasks();

        assertThat(responses)
                .containsExactly(
                        new TaskResponse(1L, "Buy milk", false),
                        new TaskResponse(2L, "Write tests", true));
    }

    @Test
    void returnsEmptyListWhenNoTasksExist() {
        TaskRepository repository = List::of;
        TasksApplicationService service = new TasksApplicationService(repository);

        List<TaskResponse> responses = service.getTasks();

        assertThat(responses).isEmpty();
    }
}
