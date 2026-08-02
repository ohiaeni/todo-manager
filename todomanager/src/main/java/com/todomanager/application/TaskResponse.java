package com.todomanager.application;

/**
 * タスクのレスポンスデータを表すレコードクラスです。
 * 
 * @param id タスクのID
 * @param title タスクのタイトル
 * @param completed タスクの完了状態
 */
public record TaskResponse(Long id, String title, boolean completed) {
}
