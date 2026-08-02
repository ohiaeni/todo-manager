package com.todomanager.presentation;

/**
 * エラーレスポンスを表すレコードクラスです。
 * 
 * @param message エラーメッセージ
 */
public record ErrorResponse(String message) {
}
