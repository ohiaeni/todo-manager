package com.todomanager.presentation;

import com.todomanager.application.TaskCreateRequest;
import com.todomanager.application.TaskResponse;
import com.todomanager.application.TaskUpdateRequest;
import com.todomanager.application.TasksApplicationService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/tasks")
public class TasksController {
  private final TasksApplicationService tasksApplicationService;

  @GetMapping
  public List<TaskResponse> getTasks(@RequestHeader("X-User-Id") Long userId) {
    return tasksApplicationService.getTasks(userId);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public TaskResponse createTask(@RequestHeader("X-User-Id") Long userId,
      @RequestBody TaskCreateRequest request) {
    return tasksApplicationService.createTask(userId, request);
  }

  @PutMapping("/{taskId}")
  public TaskResponse updateTask(@RequestHeader("X-User-Id") Long userId, @PathVariable Long taskId,
      @RequestBody TaskUpdateRequest request) {
    return tasksApplicationService.updateTask(userId, taskId, request);
  }

  @DeleteMapping("/{taskId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteTask(@RequestHeader("X-User-Id") Long userId, @PathVariable Long taskId) {
    tasksApplicationService.deleteTask(userId, taskId);
  }

}
