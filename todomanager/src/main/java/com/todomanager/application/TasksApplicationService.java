package com.todomanager.application;

import com.todomanager.domain.Task;
import com.todomanager.domain.TaskRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * タスクに関するアプリケーションサービスです。
 */
@Service
@RequiredArgsConstructor
public class TasksApplicationService {
  private final TaskRepository taskRepository;

  /**
   * 指定されたユーザーIDに関連するタスクのリストを取得します。
   * 
   * @param userId ユーザーID
   * @return タスクのレスポンスデータのリスト
   */
  public List<TaskResponse> getTasks(Long userId) {
    validateUserId(userId);
    return taskRepository.findByUserId(userId).stream().map(this::toResponse)
        .collect(Collectors.toList());
  }

  /**
   * 指定されたユーザーIDに関連する新しいタスクを作成します。
   * 
   * @param userId ユーザーID
   * @param request タスク作成リクエストデータ
   * @return 作成されたタスクのレスポンスデータ
   */
  public TaskResponse createTask(Long userId, TaskCreateRequest request) {
    validateUserId(userId);
    validateCreateRequest(request);
    Task created = taskRepository.create(userId, request.title().trim(),
        resolveCompletedFlag(request.completed()));
    return toResponse(created);
  }

  /**
   * 指定されたユーザーIDとタスクIDに関連するタスクを更新します。
   * 
   * @param userId ユーザーID
   * @param taskId タスクID
   * @param request タスク更新リクエストデータ
   * @return 更新されたタスクのレスポンスデータ
   */
  public TaskResponse updateTask(Long userId, Long taskId, TaskUpdateRequest request) {
    validateUserId(userId);
    validateTaskId(taskId);
    validateUpdateRequest(request);
    Task updated =
        taskRepository.update(userId, taskId, request.title().trim(), request.completed());
    return toResponse(updated);
  }

  /**
   * 指定されたユーザーIDとタスクIDに関連するタスクを削除します。
   * 
   * @param userId ユーザーID
   * @param taskId タスクID
   */
  public void deleteTask(Long userId, Long taskId) {
    validateUserId(userId);
    validateTaskId(taskId);
    taskRepository.delete(userId, taskId);
  }

  private TaskResponse toResponse(Task task) {
    return new TaskResponse(task.id(), task.title(), task.completed());
  }

  private void validateUserId(Long userId) {
    if (userId == null || userId <= 0) {
      throw new IllegalArgumentException("X-User-Id must be a positive number");
    }
  }

  private void validateTaskId(Long taskId) {
    if (taskId == null || taskId <= 0) {
      throw new IllegalArgumentException("taskId must be a positive number");
    }
  }

  private void validateCreateRequest(TaskCreateRequest request) {
    if (request == null) {
      throw new IllegalArgumentException("Request is required");
    }
    if (request.title() == null || request.title().isBlank()) {
      throw new IllegalArgumentException("title is required");
    }
  }

  private void validateUpdateRequest(TaskUpdateRequest request) {
    if (request == null) {
      throw new IllegalArgumentException("Request is required");
    }
    if (request.title() == null || request.title().isBlank()) {
      throw new IllegalArgumentException("title is required");
    }
    if (request.completed() == null) {
      throw new IllegalArgumentException("completed is required");
    }
  }

  private boolean resolveCompletedFlag(Boolean completed) {
    return completed != null && completed;
  }
}
