package com.todomanager.presentation;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
@WebAppConfiguration
@ActiveProfiles("integrationtest")
class TasksApiSeededIntegrationTest {

  @Autowired
  private WebApplicationContext context;

  private MockMvc mockMvc;

  @BeforeEach
  void setUp() {
    mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
  }

  @Test
  void returnsSeededTasksFromEndpoint() throws Exception {
    mockMvc.perform(get("/api/v1/tasks").header("X-User-Id", "1")).andExpect(status().isOk())
        .andExpect(jsonPath("$").isArray()).andExpect(jsonPath("$[0].id").value(1))
        .andExpect(jsonPath("$[0].title").value("Buy groceries"))
        .andExpect(jsonPath("$[0].completed").value(false));
  }
}
