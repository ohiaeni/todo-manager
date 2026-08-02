package com.todomanager.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.todomanager.domain.Task;
import com.todomanager.domain.TaskNotFoundException;
import com.todomanager.domain.TaskRepository;
import java.util.List;
import org.junit.jupiter.api.Test;

class TasksApplicationServiceTest {

    @Test
    void returnsMappedTasksWhenTasksExist() {
        TaskRepository repository = mock(TaskRepository.class);
        when(repository.findByUserId(1L)).thenReturn(List.of(
                new Task(1L, 1L, "Buy milk", false),
                new Task(2L, 1L, "Write tests", true)));
        TasksApplicationService service = new TasksApplicationService(repository);

        List<TaskResponse> responses = service.getTasks(1L);

        assertThat(responses)
                .containsExactly(
                        new TaskResponse(1L, "Buy milk", false),
                        new TaskResponse(2L, "Write tests", true));
    }

    @Test
    void returnsEmptyListWhenNoTasksExist() {
        TaskRepository repository = mock(TaskRepository.class);
        when(repository.findByUserId(1L)).thenReturn(List.of());
        TasksApplicationService service = new TasksApplicationService(repository);

        List<TaskResponse> responses = service.getTasks(1L);

        assertThat(responses).isEmpty();
    }

    @Test
    void createsTaskForUser() {
        TaskRepository repository = mock(TaskRepository.class);
        when(repository.create(1L, "New task", false)).thenReturn(new Task(10L, 1L, "New task", false));
        TasksApplicationService service = new TasksApplicationService(repository);

        TaskResponse response = service.createTask(1L, new TaskCreateRequest("New task", null));

        assertThat(response).isEqualTo(new TaskResponse(10L, "New task", false));
    }

    @Test
    void trimsTitleAndDefaultsCompletedOnCreate() {
        TaskRepository repository = mock(TaskRepository.class);
        when(repository.create(1L, "Trim me", false)).thenReturn(new Task(11L, 1L, "Trim me", false));
        TasksApplicationService service = new TasksApplicationService(repository);

        TaskResponse response = service.createTask(1L, new TaskCreateRequest("  Trim me  ", null));

        verify(repository).create(1L, "Trim me", false);
        assertThat(response).isEqualTo(new TaskResponse(11L, "Trim me", false));
    }

    @Test
    void updatesTaskForUser() {
        TaskRepository repository = mock(TaskRepository.class);
        when(repository.update(1L, 2L, "Updated", true)).thenReturn(new Task(2L, 1L, "Updated", true));
        TasksApplicationService service = new TasksApplicationService(repository);

        TaskResponse response = service.updateTask(1L, 2L, new TaskUpdateRequest("Updated", true));

        assertThat(response).isEqualTo(new TaskResponse(2L, "Updated", true));
    }

    @Test
    void trimsTitleOnUpdate() {
        TaskRepository repository = mock(TaskRepository.class);
        when(repository.update(1L, 2L, "Updated title", true)).thenReturn(new Task(2L, 1L, "Updated title", true));
        TasksApplicationService service = new TasksApplicationService(repository);

        TaskResponse response = service.updateTask(1L, 2L, new TaskUpdateRequest("  Updated title  ", true));

        verify(repository).update(1L, 2L, "Updated title", true);
        assertThat(response).isEqualTo(new TaskResponse(2L, "Updated title", true));
    }

    @Test
    void deletesTaskForUser() {
        TaskRepository repository = mock(TaskRepository.class);
        TasksApplicationService service = new TasksApplicationService(repository);

        service.deleteTask(1L, 2L);

        verify(repository).delete(1L, 2L);
    }

    @Test
    void rejectsInvalidUserId() {
        TaskRepository repository = mock(TaskRepository.class);
        TasksApplicationService service = new TasksApplicationService(repository);

        assertThatThrownBy(() -> service.getTasks(0L))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("X-User-Id");
    }

    @Test
    void requiresCompletedFieldOnUpdate() {
        TaskRepository repository = mock(TaskRepository.class);
        TasksApplicationService service = new TasksApplicationService(repository);

        assertThatThrownBy(() -> service.updateTask(1L, 1L, new TaskUpdateRequest("title", null)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("completed");
    }

    @Test
    void rejectsInvalidTaskIdOnUpdate() {
        TaskRepository repository = mock(TaskRepository.class);
        TasksApplicationService service = new TasksApplicationService(repository);

        assertThatThrownBy(() -> service.updateTask(1L, 0L, new TaskUpdateRequest("title", true)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("taskId");
    }

    @Test
    void propagatesTaskNotFoundOnUpdate() {
        TaskRepository repository = mock(TaskRepository.class);
        when(repository.update(anyLong(), anyLong(), anyString(), anyBoolean()))
                .thenThrow(new TaskNotFoundException(99L));
        TasksApplicationService service = new TasksApplicationService(repository);

        assertThatThrownBy(() -> service.updateTask(1L, 99L, new TaskUpdateRequest("t", false)))
                .isInstanceOf(TaskNotFoundException.class);
    }
}
