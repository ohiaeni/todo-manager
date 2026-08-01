package com.todomanager.presentation;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.todomanager.application.TaskResponse;
import com.todomanager.application.TasksApplicationService;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
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
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(tasksController).build();

        given(tasksApplicationService.getTasks()).willReturn(List.of(
                new TaskResponse(1L, "Buy milk", false),
                new TaskResponse(2L, "Write tests", true)));

        mockMvc.perform(get("/api/v1/tasks"))
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
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(tasksController).build();

        given(tasksApplicationService.getTasks()).willReturn(List.of());

        mockMvc.perform(get("/api/v1/tasks"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").isEmpty());
    }
}
