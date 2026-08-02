DELETE FROM tasks;

INSERT INTO tasks (id, user_id, title, completed) VALUES (1, 1, 'Buy groceries', FALSE);
INSERT INTO tasks (id, user_id, title, completed) VALUES (2, 1, 'Refactor task service', TRUE);
INSERT INTO tasks (id, user_id, title, completed) VALUES (3, 2, 'Write API tests', FALSE);
