INSERT
INTO
  users
  (email, password, first_name, last_name, created_at)
VALUES
  ('hexlet@example.com', '$2a$10$IcyJprZ0gplCS0N5umyW5Os2B62nIxsvy9QYK6f4rJJJp5kUVWGNi', 'John', 'Doe', NOW());

INSERT
INTO
  users
  (email, password, first_name, last_name, created_at)
VALUES
  ('email@email.com', '$2a$10$IcyJprZ0gplCS0N5umyW5Os2B62nIxsvy9QYK6f4rJJJp5kUVWGNi', 'Alice', 'Brown', NOW());

INSERT
INTO
  statuses
  (name, slug, created_at)
VALUES
  ('Draft', 'draft', NOW());

INSERT
INTO
  statuses
  (name, slug, created_at)
VALUES
  ('To Review', 'to_review', NOW());

INSERT
INTO
  statuses
  (name, slug, created_at)
VALUES
  ('To Be Fixed', 'to_be_fixed', NOW());

INSERT
INTO
  statuses
  (name, slug, created_at)
VALUES
  ('To Publish', 'to_publish', NOW());

INSERT
INTO
  statuses
  (name, slug, created_at)
VALUES
  ('Published', 'published', NOW());

INSERT
INTO
  labels
  (name, created_at)
VALUES
  ('feature', NOW());

INSERT
INTO
  labels
  (name, created_at)
VALUES
  ('bug', NOW());
