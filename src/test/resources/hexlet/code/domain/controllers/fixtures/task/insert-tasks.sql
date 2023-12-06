INSERT
INTO
  tasks
  (id, name, task_status_id, index, description, assignee_id, created_at)
VALUES
  (101, 'test first task', 101, 100, 'test first task description', 101, NOW());

INSERT
INTO
  tasks
  (id, name, task_status_id, index, description, assignee_id, created_at)
VALUES
  (102, 'test second task', 102, 200, 'test second task description', 102, NOW());

ALTER TABLE tasks ALTER COLUMN id RESTART WITH 103;
