INSERT
INTO
  tasks
  (id, name, task_status_id, index, description, assignee_id, created_at)
VALUES
  (1, 'first task', 1, 100, 'first task description', 1, NOW());

INSERT
INTO
  tasks
  (id, name, task_status_id, index, description, assignee_id, created_at)
VALUES
  (2, 'second task', 2, 200, 'second task description', 2, NOW());

ALTER TABLE tasks ALTER COLUMN id RESTART WITH 3;
