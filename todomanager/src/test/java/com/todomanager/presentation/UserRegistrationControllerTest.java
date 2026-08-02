package com.todomanager.presentation;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.todomanager.application.UserRegistrationRequest;
import com.todomanager.application.UserRegistrationResponse;
import com.todomanager.application.UserRegistrationService;
import com.todomanager.presentation.controller.UserRegistrationController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
class UserRegistrationControllerTest {

  @Mock
  private UserRegistrationService userRegistrationService;

  @InjectMocks
  private UserRegistrationController userRegistrationController;

  @Test
  void registersUserAndReturnsCreatedResponse() throws Exception {
    MockMvc mockMvc = MockMvcBuilders.standaloneSetup(userRegistrationController).build();

    given(
        userRegistrationService.register(new UserRegistrationRequest("alice", "alice@example.com")))
            .willReturn(new UserRegistrationResponse(1L, "alice", "alice@example.com"));

    mockMvc
        .perform(post("/api/v1/users").contentType(MediaType.APPLICATION_JSON)
            .content("{\"username\":\"alice\",\"email\":\"alice@example.com\"}"))
        .andExpect(status().isCreated()).andExpect(jsonPath("$.id").value(1))
        .andExpect(jsonPath("$.username").value("alice"))
        .andExpect(jsonPath("$.email").value("alice@example.com"));
  }
}
