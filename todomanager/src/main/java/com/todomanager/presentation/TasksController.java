package com.todomanager.presentation;

import com.todomanager.application.TaskResponse;
import com.todomanager.application.TasksApplicationService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/tasks")
public class TasksController {
    private final TasksApplicationService tasksApplicationService;

    @GetMapping
    public List<TaskResponse> getTasks() {
        return tasksApplicationService.getTasks();
    }

}
