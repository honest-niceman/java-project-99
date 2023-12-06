INSERT
INTO
  labels
  (id, name)
VALUES
  (1, 'bug');
INSERT
INTO
  labels
  (id, name)
VALUES
  (2, 'feature');
INSERT
INTO
  labels
  (id, name)
VALUES
  (3, 'obsolete');

ALTER TABLE labels ALTER COLUMN id RESTART WITH 4;