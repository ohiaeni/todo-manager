CREATE TABLE IF NOT EXISTS tasks (
    id BIGINT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    title VARCHAR(255) NOT NULL,
    completed BOOLEAN NOT NULL
);

CREATE INDEX IF NOT EXISTS idx_tasks_user_id ON tasks(user_id);
