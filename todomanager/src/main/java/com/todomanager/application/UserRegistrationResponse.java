package com.todomanager.application;

/**
 * ユーザー登録レスポンスデータを表すレコードクラスです。
 * 
 * @param id ユーザーのID
 * @param username ユーザー名
 * @param email ユーザーのメールアドレス
 */
public record UserRegistrationResponse(Long id, String username, String email) {
}
