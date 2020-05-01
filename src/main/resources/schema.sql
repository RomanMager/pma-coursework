DROP TABLE IF EXISTS Tasks;
DROP TABLE IF EXISTS project;

CREATE TABLE IF NOT EXISTS Tasks
(
    task_id   INT          NOT NULL PRIMARY KEY AUTO_INCREMENT,
    task_name VARCHAR(300) NOT NULL
);

CREATE TABLE IF NOT EXISTS project
(
    project_id   BIGINT      NOT NULL AUTO_INCREMENT,
    description  VARCHAR(400),
    project_name VARCHAR(50) NOT NULL,
    PRIMARY KEY (project_id)
);
