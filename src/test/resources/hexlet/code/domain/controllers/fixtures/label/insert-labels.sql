INSERT
INTO
  labels
  (id, name)
VALUES
  (101, 'test_bug');
INSERT
INTO
  labels
  (id, name)
VALUES
  (102, 'test_feature');
INSERT
INTO
  labels
  (id, name)
VALUES
  (103, 'test_obsolete');

ALTER TABLE labels ALTER COLUMN id RESTART WITH 104;