package com.todomanager.domain;

/**
 * タスクの所有権に関する例外を表すクラスです。
 */
public class TaskOwnershipException extends RuntimeException {
  /**
   * タスクの所有権が不正である場合にスローされる例外を作成します。
   * 
   * @param taskId タスクID
   * @param userId ユーザーID
   */
  public TaskOwnershipException(Long taskId, Long userId) {
    super("Task " + taskId + " does not belong to user " + userId);
  }
}
