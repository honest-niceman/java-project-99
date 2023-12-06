INSERT
INTO
  statuses
  (id, name, slug, created_at)
VALUES
  (101, 'test first status', 'test_first_status', NOW());

INSERT
INTO
  statuses
  (id, name, slug, created_at)
VALUES
  (102, 'test second status', 'test_second_status', NOW());

ALTER TABLE statuses ALTER COLUMN id RESTART WITH 103;