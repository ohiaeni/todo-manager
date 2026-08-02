package com.todomanager.presentation;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
@WebAppConfiguration
@ActiveProfiles("integrationtest")
@Sql(scripts = "classpath:data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class TasksCrudApiIntegrationTest {

    @org.springframework.beans.factory.annotation.Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    void createsTaskForUser() throws Exception {
        mockMvc.perform(post("/api/v1/tasks")
                .header("X-User-Id", "1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\":\"Add integration test\",\"completed\":false}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.title").value("Add integration test"))
                .andExpect(jsonPath("$.completed").value(false));
    }

    @Test
    void updatesOwnedTask() throws Exception {
        mockMvc.perform(put("/api/v1/tasks/1")
                .header("X-User-Id", "1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\":\"Buy vegetables\",\"completed\":true}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("Buy vegetables"))
                .andExpect(jsonPath("$.completed").value(true));
    }

    @Test
    void deletesOwnedTask() throws Exception {
        mockMvc.perform(delete("/api/v1/tasks/2")
                .header("X-User-Id", "1"))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/api/v1/tasks").header("X-User-Id", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    void rejectsUpdatingOtherUsersTask() throws Exception {
        mockMvc.perform(put("/api/v1/tasks/1")
                .header("X-User-Id", "2")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\":\"Forbidden\",\"completed\":true}"))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").exists());
    }

    @Test
    void rejectsDeletingOtherUsersTask() throws Exception {
        mockMvc.perform(delete("/api/v1/tasks/1")
                .header("X-User-Id", "2"))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").exists());
    }

    @Test
    void returnsOnlyTasksOwnedByUser() throws Exception {
        mockMvc.perform(get("/api/v1/tasks").header("X-User-Id", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[1].id").value(2));

        mockMvc.perform(get("/api/v1/tasks").header("X-User-Id", "2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id").value(3));
    }
}
