INSERT
INTO
  statuses
  (id, name, slug, created_at)
VALUES
  (1, 'first status', 'first_status', NOW());

INSERT
INTO
  statuses
  (id, name, slug, created_at)
VALUES
  (2, 'second status', 'second_status', NOW());

ALTER TABLE statuses ALTER COLUMN id RESTART WITH 3;