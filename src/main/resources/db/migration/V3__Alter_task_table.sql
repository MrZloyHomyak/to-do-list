INSERT INTO "user" (id, username, password, email)
SELECT 1, 'default', 'password', 'default@example.com'
WHERE NOT EXISTS (SELECT 1 FROM "user" WHERE id = 1);

ALTER TABLE task ADD COLUMN user_id BIGINT;

UPDATE task SET user_id = 1;

ALTER TABLE task ALTER COLUMN user_id SET NOT NULL;
ALTER TABLE task ADD CONSTRAINT FK_TASK_USER FOREIGN KEY (user_id) REFERENCES "user" (id);