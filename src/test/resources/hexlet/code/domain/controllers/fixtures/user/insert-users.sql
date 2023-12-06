INSERT
INTO
  users
  (id, first_name, last_name, email, password, created_at, updated_at)
VALUES
  (101, 'admin', 'admin', 'admin', 'admin', '2023-11-27T20:01:13.340624Z', '2023-11-27T20:01:13.340624Z');

INSERT
INTO
  users
  (id, first_name, last_name, email, password, created_at, updated_at)
VALUES
  (102, 'user', 'user', 'user', 'user', '2023-11-27T20:01:13.340624Z', '2023-11-27T20:01:13.340624Z');

ALTER TABLE users ALTER COLUMN id RESTART WITH 103;