package com.todomanager.infrastructure;

import com.todomanager.domain.Task;
import com.todomanager.domain.TaskRepository;
import com.todomanager.domain.TaskNotFoundException;
import com.todomanager.domain.TaskOwnershipException;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcTaskRepository implements TaskRepository {
    private final TaskMapper taskMapper;

    public JdbcTaskRepository(TaskMapper taskMapper) {
        this.taskMapper = taskMapper;
    }

    @Override
    public List<Task> findByUserId(Long userId) {
        return taskMapper.findByUserId(userId);
    }

    @Override
    public Task create(Long userId, String title, boolean completed) {
        Long id = taskMapper.nextId();
        taskMapper.insert(id, userId, title, completed);
        return taskMapper.findByIdAndUserId(id, userId);
    }

    @Override
    public boolean existsById(Long taskId) {
        return taskMapper.existsById(taskId);
    }

    @Override
    public Task update(Long userId, Long taskId, String title, boolean completed) {
        int updated = taskMapper.updateByIdAndUserId(taskId, userId, title, completed);
        if (updated == 0) {
            if (taskMapper.existsById(taskId)) {
                throw new TaskOwnershipException(taskId, userId);
            }
            throw new TaskNotFoundException(taskId);
        }
        return taskMapper.findByIdAndUserId(taskId, userId);
    }

    @Override
    public boolean delete(Long userId, Long taskId) {
        int deleted = taskMapper.deleteByIdAndUserId(taskId, userId);
        if (deleted > 0) {
            return true;
        }
        if (taskMapper.existsById(taskId)) {
            throw new TaskOwnershipException(taskId, userId);
        }
        throw new TaskNotFoundException(taskId);
    }
}
