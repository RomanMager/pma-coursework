CREATE TABLE IF NOT EXISTS Tasks
(
    task_id   INT          NOT NULL PRIMARY KEY AUTO_INCREMENT,
    task_name VARCHAR(300) NOT NULL
);

INSERT INTO Tasks(task_name)
VALUES ('Task #1');

INSERT INTO Tasks(task_name)
VALUES ('Task #2');

INSERT INTO Tasks(task_name)
VALUES ('Task #3');
