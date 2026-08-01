package com.todomanager.application;

import com.todomanager.domain.Task;
import com.todomanager.domain.TaskRepository;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TasksApplicationService {
    private final TaskRepository taskRepository;

    public List<TaskResponse> getTasks() {
        return taskRepository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    private TaskResponse toResponse(Task task) {
        return new TaskResponse(task.id(), task.title(), task.completed());
    }
}
