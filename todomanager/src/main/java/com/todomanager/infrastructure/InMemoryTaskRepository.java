package com.todomanager.infrastructure;

import com.todomanager.domain.Task;
import com.todomanager.domain.TaskRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryTaskRepository implements TaskRepository {
    private final List<Task> tasks = new ArrayList<>();

    @Override
    public List<Task> findAll() {
        return List.copyOf(tasks);
    }
}
