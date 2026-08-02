package com.todomanager.infrastructure;

import com.todomanager.domain.Task;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * タスクのマッパーインターフェースを表すクラスです。
 */
@Mapper
public interface TaskMapper {
  /**
   * 指定されたユーザーIDに関連するタスクのリストを取得します。
   * 
   * @param userId ユーザーID
   * @return タスクのリスト
   */
  List<Task> findByUserId(@Param("userId") Long userId);

  /**
   * 次のタスクIDを生成します。
   * 
   * @return 次のタスクID
   */
  Long nextId();

  /**
   * 指定されたタスクを挿入します。
   * 
   * @param id タスクID
   * @param userId ユーザーID
   * @param title タイトル
   * @param completed 完了状態
   * @return 挿入された行数
   */
  int insert(@Param("id") Long id, @Param("userId") Long userId, @Param("title") String title,
      @Param("completed") boolean completed);

  /**
   * 指定されたタスクIDが存在するかどうかを確認します。
   * 
   * @param taskId タスクID
   * @return 存在する場合はtrue、存在しない場合はfalse
   */
  boolean existsById(@Param("taskId") Long taskId);

  /**
   * 指定されたタスクIDとユーザーIDに関連するタスクを取得します。
   * 
   * @param taskId タスクID
   * @param userId ユーザーID
   * @return タスク
   */
  Task findByIdAndUserId(@Param("taskId") Long taskId, @Param("userId") Long userId);

  /**
   * 指定されたタスクIDとユーザーIDに関連するタスクを更新します。
   * 
   * @param taskId タスクID
   * @param userId ユーザーID
   * @param title タイトル
   * @param completed 完了状態
   * @return 更新された行数
   */
  int updateByIdAndUserId(@Param("taskId") Long taskId, @Param("userId") Long userId,
      @Param("title") String title, @Param("completed") boolean completed);

  /**
   * 指定されたタスクIDとユーザーIDに関連するタスクを削除します。
   * 
   * @param taskId タスクID
   * @param userId ユーザーID
   * @return 削除された行数
   */
  int deleteByIdAndUserId(@Param("taskId") Long taskId, @Param("userId") Long userId);
}
