package com.todomanager.domain;

public class TaskOwnershipException extends RuntimeException {
  public TaskOwnershipException(Long taskId, Long userId) {
    super("Task " + taskId + " does not belong to user " + userId);
  }
}
