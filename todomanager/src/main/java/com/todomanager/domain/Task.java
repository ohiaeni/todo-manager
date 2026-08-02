package com.todomanager.domain;

/**
 * タスクを表すレコードクラスです。
 * 
 * @param id タスクのID
 * @param userId タスクの所有者であるユーザーのID
 * @param title タスクのタイトル
 * @param completed タスクの完了状態
 */
public record Task(Long id, Long userId, String title, boolean completed) {
}
