package com.todomanager.application;

/**
 * タスクを作成するためのリクエストデータを表すレコードクラスです。
 * 
 * @param title タスクのタイトル
 * @param completed タスクの完了状態
 */
public record TaskCreateRequest(String title, Boolean completed) {
}
