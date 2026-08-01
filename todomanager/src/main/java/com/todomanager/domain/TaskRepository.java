package com.todomanager.domain;

import java.util.List;

public interface TaskRepository {
    List<Task> findAll();
}
