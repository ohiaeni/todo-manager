package com.todomanager.presentation;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.todomanager.application.TaskCreateRequest;
import com.todomanager.application.TaskResponse;
import com.todomanager.application.TaskUpdateRequest;
import com.todomanager.application.TasksApplicationService;
import com.todomanager.domain.TaskNotFoundException;
import com.todomanager.domain.TaskOwnershipException;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
class TasksControllerTest {

    @Mock
    private TasksApplicationService tasksApplicationService;

    @InjectMocks
    private TasksController tasksController;

    @Test
    void returnsTasksWithExpectedFields() throws Exception {
        MockMvc mockMvc = buildMockMvc();

        given(tasksApplicationService.getTasks(1L)).willReturn(List.of(
                new TaskResponse(1L, "Buy milk", false),
                new TaskResponse(2L, "Write tests", true)));

        mockMvc.perform(get("/api/v1/tasks").header("X-User-Id", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].title").value("Buy milk"))
                .andExpect(jsonPath("$[0].completed").value(false))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].title").value("Write tests"))
                .andExpect(jsonPath("$[1].completed").value(true));
    }

    @Test
    void returnsEmptyArrayWhenNoTasksExist() throws Exception {
        MockMvc mockMvc = buildMockMvc();

        given(tasksApplicationService.getTasks(1L)).willReturn(List.of());

        mockMvc.perform(get("/api/v1/tasks").header("X-User-Id", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    void createsTaskAndReturnsCreatedResponse() throws Exception {
        MockMvc mockMvc = buildMockMvc();

        TaskCreateRequest request = new TaskCreateRequest("Write tests", false);
        given(tasksApplicationService.createTask(1L, request))
                .willReturn(new TaskResponse(10L, "Write tests", false));

        mockMvc.perform(post("/api/v1/tasks")
                .header("X-User-Id", "1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\":\"Write tests\",\"completed\":false}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(10))
                .andExpect(jsonPath("$.title").value("Write tests"))
                .andExpect(jsonPath("$.completed").value(false));
    }

    @Test
    void updatesTaskAndReturnsUpdatedResponse() throws Exception {
        MockMvc mockMvc = buildMockMvc();

        TaskUpdateRequest request = new TaskUpdateRequest("Refactor", true);
        given(tasksApplicationService.updateTask(1L, 2L, request))
                .willReturn(new TaskResponse(2L, "Refactor", true));

        mockMvc.perform(put("/api/v1/tasks/2")
                .header("X-User-Id", "1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\":\"Refactor\",\"completed\":true}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(2))
                .andExpect(jsonPath("$.title").value("Refactor"))
                .andExpect(jsonPath("$.completed").value(true));
    }

    @Test
    void deletesTaskAndReturnsNoContent() throws Exception {
        MockMvc mockMvc = buildMockMvc();

        mockMvc.perform(delete("/api/v1/tasks/2").header("X-User-Id", "1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void returnsBadRequestWhenHeaderIsMissing() throws Exception {
        MockMvc mockMvc = buildMockMvc();

        mockMvc.perform(get("/api/v1/tasks"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").exists());
    }

    @Test
    void returnsNotFoundWhenTaskDoesNotExist() throws Exception {
        MockMvc mockMvc = buildMockMvc();
        willThrow(new TaskNotFoundException(99L)).given(tasksApplicationService)
                .updateTask(1L, 99L, new TaskUpdateRequest("missing", false));

        mockMvc.perform(put("/api/v1/tasks/99")
                .header("X-User-Id", "1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\":\"missing\",\"completed\":false}"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Task not found: 99"));
    }

    @Test
    void returnsForbiddenWhenTaskBelongsToAnotherUser() throws Exception {
        MockMvc mockMvc = buildMockMvc();
        willThrow(new TaskOwnershipException(2L, 1L)).given(tasksApplicationService)
                .deleteTask(1L, 2L);

        mockMvc.perform(delete("/api/v1/tasks/2").header("X-User-Id", "1"))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value("Task 2 does not belong to user 1"));
    }

    private MockMvc buildMockMvc() {
        return MockMvcBuilders.standaloneSetup(tasksController)
                .setControllerAdvice(new ApiExceptionHandler())
                .build();
    }
}
