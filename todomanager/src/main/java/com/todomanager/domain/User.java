package com.todomanager.domain;

/**
 * ユーザーを表すレコードクラスです。
 * 
 * @param id ユーザーのID
 * @param username ユーザー名
 * @param email ユーザーのメールアドレス
 */
public record User(Long id, String username, String email) {
}
