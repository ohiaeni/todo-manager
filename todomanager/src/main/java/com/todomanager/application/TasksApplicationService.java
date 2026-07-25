package com.todomanager.application;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TasksApplicationService {
    public List<String> getTasks() {
        return List.of("Hello, World!");
    }
}
