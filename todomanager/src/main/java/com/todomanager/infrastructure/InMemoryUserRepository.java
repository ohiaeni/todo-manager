package com.todomanager.infrastructure;

import com.todomanager.domain.User;
import com.todomanager.domain.UserRepository;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryUserRepository implements UserRepository {
  private final Map<Long, User> users = new ConcurrentHashMap<>();
  private final AtomicLong sequence = new AtomicLong();

  @Override
  public boolean existsByUsername(String username) {
    return users.values().stream().anyMatch(user -> username.equals(user.username()));
  }

  @Override
  public boolean existsByEmail(String email) {
    return users.values().stream().anyMatch(user -> email.equals(user.email()));
  }

  @Override
  public User save(User user) {
    long id = sequence.incrementAndGet();
    User savedUser = new User(id, user.username(), user.email());
    users.put(id, savedUser);
    return savedUser;
  }
}
