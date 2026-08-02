package com.todomanager.application;

/**
 * ユーザー登録リクエストデータを表すレコードクラスです。
 * 
 * @param username ユーザー名
 * @param email ユーザーのメールアドレス
 */
public record UserRegistrationRequest(String username, String email) {
}
