package com.todomanager.presentation.controller;

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

/**
 * タスクに関するAPIエンドポイントを提供するコントローラークラスです。
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/tasks")
public class TasksController {
  private final TasksApplicationService tasksApplicationService;

  /**
   * 指定されたユーザーIDに関連するタスクのリストを取得します。
   * 
   * @param userId ユーザーID
   * @return タスクのレスポンスデータのリスト
   */
  @GetMapping
  public List<TaskResponse> getTasks(@RequestHeader("X-User-Id") Long userId) {
    return tasksApplicationService.getTasks(userId);
  }

  /**
   * 指定されたユーザーIDに関連する新しいタスクを作成します。
   * 
   * @param userId ユーザーID
   * @param request タスク作成リクエスト
   * @return 作成されたタスクのレスポンスデータ
   */
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public TaskResponse createTask(@RequestHeader("X-User-Id") Long userId,
      @RequestBody TaskCreateRequest request) {
    return tasksApplicationService.createTask(userId, request);
  }

  /**
   * 指定されたユーザーIDとタスクIDに関連するタスクを更新します。
   * 
   * @param userId ユーザーID
   * @param taskId タスクID
   * @param request タスク更新リクエスト
   * @return 更新されたタスクのレスポンスデータ
   */
  @PutMapping("/{taskId}")
  public TaskResponse updateTask(@RequestHeader("X-User-Id") Long userId, @PathVariable Long taskId,
      @RequestBody TaskUpdateRequest request) {
    return tasksApplicationService.updateTask(userId, taskId, request);
  }

  /**
   * 指定されたユーザーIDとタスクIDに関連するタスクを削除します。
   * 
   * @param userId ユーザーID
   * @param taskId タスクID
   */
  @DeleteMapping("/{taskId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteTask(@RequestHeader("X-User-Id") Long userId, @PathVariable Long taskId) {
    tasksApplicationService.deleteTask(userId, taskId);
  }

}
