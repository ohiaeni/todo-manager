package com.todomanager.domain.repository;

import com.todomanager.domain.User;

/**
 * ユーザーリポジトリのインターフェースを表すクラスです。
 */
public interface UserRepository {
  /**
   * 指定されたユーザー名が既に存在するかどうかを確認します。
   * 
   * @param username 確認するユーザー名
   * @return 指定されたユーザー名が既に存在する場合はtrue、存在しない場合はfalse
   */
  boolean existsByUsername(String username);

  /**
   * 指定されたメールアドレスが既に存在するかどうかを確認します。
   * 
   * @param email 確認するメールアドレス
   * @return 指定されたメールアドレスが既に存在する場合はtrue、存在しない場合はfalse
   */
  boolean existsByEmail(String email);

  /**
   * 指定されたユーザーを保存します。
   * 
   * @param user 保存するユーザー
   * @return 保存されたユーザー
   */
  User save(User user);
}
