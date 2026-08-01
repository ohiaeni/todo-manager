package com.todomanager.infrastructure;

import com.todomanager.domain.Task;
import com.todomanager.domain.TaskRepository;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcTaskRepository implements TaskRepository {
    private final JdbcTemplate jdbcTemplate;

    public JdbcTaskRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Task> findAll() {
        return jdbcTemplate.query(
                "SELECT id, title, completed FROM tasks ORDER BY id",
                (row, rowNum) -> new Task(
                        row.getLong("id"),
                        row.getString("title"),
                        row.getBoolean("completed")));
    }
}
