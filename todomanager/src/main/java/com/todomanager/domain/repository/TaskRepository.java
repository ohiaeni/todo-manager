package com.todomanager.domain.repository;

import java.util.List;
import com.todomanager.domain.Task;

/**
 * タスクのリポジトリインターフェースを表すクラスです。
 */
public interface TaskRepository {
  /**
   * 指定されたユーザーIDに関連するタスクのリストを取得します。
   * 
   * @param userId ユーザーID
   * @return タスクのリスト
   */
  List<Task> findByUserId(Long userId);

  /**
   * 指定されたユーザーIDに関連する新しいタスクを作成します。
   * 
   * @param userId ユーザーID
   * @param title タスクのタイトル
   * @param completed タスクの完了状態
   * @return 作成されたタスク
   */
  Task create(Long userId, String title, boolean completed);

  /**
   * 指定されたユーザーIDとタスクIDに関連するタスクを更新します。
   * 
   * @param taskId タスクID
   * @return 更新されたタスク
   */
  boolean existsById(Long taskId);

  /**
   * 指定されたユーザーIDとタスクIDに関連するタスクを更新します。
   * 
   * @param userId ユーザーID
   * @param taskId タスクID
   * @param title タスクのタイトル
   * @param completed タスクの完了状態
   * @return 更新されたタスク
   */
  Task update(Long userId, Long taskId, String title, boolean completed);

  /**
   * 指定されたユーザーIDとタスクIDに関連するタスクを削除します。
   * 
   * @param userId ユーザーID
   * @param taskId タスクID
   * @return 削除が成功した場合はtrue、タスクが存在しない場合はfalse
   */
  boolean delete(Long userId, Long taskId);
}
