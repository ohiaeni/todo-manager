package com.todomanager.domain;

import java.util.List;

public interface TaskRepository {
    List<Task> findByUserId(Long userId);

    Task create(Long userId, String title, boolean completed);

    boolean existsById(Long taskId);

    Task update(Long userId, Long taskId, String title, boolean completed);

    boolean delete(Long userId, Long taskId);
}
