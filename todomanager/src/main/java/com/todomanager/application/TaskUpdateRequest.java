package com.todomanager.application;

/**
 * タスクを更新するためのリクエストデータを表すレコードクラスです。
 * 
 * @param title タスクのタイトル
 * @param completed タスクの完了状態
 */
public record TaskUpdateRequest(String title, Boolean completed) {
}
