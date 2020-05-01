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

--
CREATE TABLE IF NOT EXISTS project
(
    project_id   BIGINT      NOT NULL AUTO_INCREMENT,
    description  VARCHAR(400),
    project_name VARCHAR(50) NOT NULL,
    PRIMARY KEY (project_id)
);


INSERT INTO project(project_name, description)
VALUES ('Demo project #1', 'Demo description #1');

INSERT INTO project(project_name, description)
VALUES ('Demo project #2', 'Demo description #2');

INSERT INTO project(project_name, description)
VALUES ('Demo project #3', 'Demo description #3');

INSERT INTO project(project_name, description)
VALUES ('Sample project #1', 'Sample description #1');

INSERT INTO project(project_name, description)
VALUES ('Sample project #2', 'Sample description #2');
