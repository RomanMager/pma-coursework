DROP TABLE IF EXISTS Tasks;

CREATE TABLE IF NOT EXISTS Tasks
(
    task_id   INT          NOT NULL PRIMARY KEY AUTO_INCREMENT,
    task_name VARCHAR(300) NOT NULL
);
