package com.todomanager.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.todomanager.domain.User;
import com.todomanager.domain.UserRepository;
import org.junit.jupiter.api.Test;

class UserRegistrationServiceTest {

  @Test
  void registersNewUserWhenRequestIsValid() {
    UserRepository repository = mock(UserRepository.class);
    when(repository.existsByUsername("alice")).thenReturn(false);
    when(repository.existsByEmail("alice@example.com")).thenReturn(false);
    when(repository.save(any(User.class))).thenAnswer(invocation -> {
      User user = invocation.getArgument(0);
      return new User(1L, user.username(), user.email());
    });

    UserRegistrationService service = new UserRegistrationService(repository);

    UserRegistrationResponse response =
        service.register(new UserRegistrationRequest("alice", "alice@example.com"));

    assertThat(response).isEqualTo(new UserRegistrationResponse(1L, "alice", "alice@example.com"));
    verify(repository).save(any(User.class));
  }

  @Test
  void rejectsMissingRequiredFields() {
    UserRepository repository = mock(UserRepository.class);
    UserRegistrationService service = new UserRegistrationService(repository);

    assertThatThrownBy(
        () -> service.register(new UserRegistrationRequest("   ", "alice@example.com")))
            .isInstanceOf(IllegalArgumentException.class).hasMessageContaining("username");
  }

  @Test
  void rejectsDuplicateUsernameOrEmail() {
    UserRepository repository = mock(UserRepository.class);
    when(repository.existsByUsername("alice")).thenReturn(true);

    UserRegistrationService service = new UserRegistrationService(repository);

    assertThatThrownBy(
        () -> service.register(new UserRegistrationRequest("alice", "alice@example.com")))
            .isInstanceOf(IllegalArgumentException.class).hasMessageContaining("already exists");
  }
}
