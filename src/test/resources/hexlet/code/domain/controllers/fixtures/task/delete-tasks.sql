DELETE
FROM
  tasks_labels;

DELETE
FROM
  tasks
WHERE id IN (101,102);
