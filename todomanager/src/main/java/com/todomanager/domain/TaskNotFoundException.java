package com.todomanager.domain;

/**
 * タスクが見つからない場合にスローされる例外を表すクラスです。
 */
public class TaskNotFoundException extends RuntimeException {
  /**
   * タスクが見つからない場合にスローされる例外を作成します。
   * 
   * @param taskId 見つからなかったタスクのID
   */
  public TaskNotFoundException(Long taskId) {
    super("Task not found: " + taskId);
  }
}
