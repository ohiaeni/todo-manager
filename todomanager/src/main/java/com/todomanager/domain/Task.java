package com.todomanager.domain;

public record Task(Long id, Long userId, String title, boolean completed) {
}
