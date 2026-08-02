package com.todomanager.infrastructure;

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
import com.todomanager.domain.TaskOwnershipException;
import org.junit.jupiter.api.Test;

class JdbcTaskRepositoryTest {

    @Test
    void createsTaskUsingGeneratedId() {
        TaskMapper mapper = mock(TaskMapper.class);
        when(mapper.nextId()).thenReturn(10L);
        when(mapper.findByIdAndUserId(10L, 1L)).thenReturn(new Task(10L, 1L, "Write tests", false));
        JdbcTaskRepository repository = new JdbcTaskRepository(mapper);

        Task created = repository.create(1L, "Write tests", false);

        verify(mapper).insert(10L, 1L, "Write tests", false);
        assertThat(created).isEqualTo(new Task(10L, 1L, "Write tests", false));
    }

    @Test
    void updatesTaskWhenOwnershipMatches() {
        TaskMapper mapper = mock(TaskMapper.class);
        when(mapper.updateByIdAndUserId(2L, 1L, "Updated", true)).thenReturn(1);
        when(mapper.findByIdAndUserId(2L, 1L)).thenReturn(new Task(2L, 1L, "Updated", true));
        JdbcTaskRepository repository = new JdbcTaskRepository(mapper);

        Task updated = repository.update(1L, 2L, "Updated", true);

        assertThat(updated).isEqualTo(new Task(2L, 1L, "Updated", true));
    }

    @Test
    void throwsOwnershipExceptionWhenUpdateAffectsNoRowsButTaskExists() {
        TaskMapper mapper = mock(TaskMapper.class);
        when(mapper.updateByIdAndUserId(anyLong(), anyLong(), anyString(), anyBoolean())).thenReturn(0);
        when(mapper.existsById(3L)).thenReturn(true);
        JdbcTaskRepository repository = new JdbcTaskRepository(mapper);

        assertThatThrownBy(() -> repository.update(1L, 3L, "Forbidden", true))
                .isInstanceOf(TaskOwnershipException.class)
                .hasMessage("Task 3 does not belong to user 1");
    }

    @Test
    void throwsNotFoundExceptionWhenUpdateAffectsNoRowsAndTaskMissing() {
        TaskMapper mapper = mock(TaskMapper.class);
        when(mapper.updateByIdAndUserId(anyLong(), anyLong(), anyString(), anyBoolean())).thenReturn(0);
        when(mapper.existsById(99L)).thenReturn(false);
        JdbcTaskRepository repository = new JdbcTaskRepository(mapper);

        assertThatThrownBy(() -> repository.update(1L, 99L, "Missing", false))
                .isInstanceOf(TaskNotFoundException.class)
                .hasMessage("Task not found: 99");
    }

    @Test
    void deletesTaskWhenOwnershipMatches() {
        TaskMapper mapper = mock(TaskMapper.class);
        when(mapper.deleteByIdAndUserId(2L, 1L)).thenReturn(1);
        JdbcTaskRepository repository = new JdbcTaskRepository(mapper);

        boolean deleted = repository.delete(1L, 2L);

        assertThat(deleted).isTrue();
    }

    @Test
    void throwsOwnershipExceptionWhenDeleteAffectsNoRowsButTaskExists() {
        TaskMapper mapper = mock(TaskMapper.class);
        when(mapper.deleteByIdAndUserId(2L, 1L)).thenReturn(0);
        when(mapper.existsById(2L)).thenReturn(true);
        JdbcTaskRepository repository = new JdbcTaskRepository(mapper);

        assertThatThrownBy(() -> repository.delete(1L, 2L))
                .isInstanceOf(TaskOwnershipException.class)
                .hasMessage("Task 2 does not belong to user 1");
    }

    @Test
    void throwsNotFoundExceptionWhenDeleteAffectsNoRowsAndTaskMissing() {
        TaskMapper mapper = mock(TaskMapper.class);
        when(mapper.deleteByIdAndUserId(99L, 1L)).thenReturn(0);
        when(mapper.existsById(99L)).thenReturn(false);
        JdbcTaskRepository repository = new JdbcTaskRepository(mapper);

        assertThatThrownBy(() -> repository.delete(1L, 99L))
                .isInstanceOf(TaskNotFoundException.class)
                .hasMessage("Task not found: 99");
    }
}
