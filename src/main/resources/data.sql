DROP TABLE IF EXISTS Tasks;
DROP TABLE IF EXISTS Projects;
-- #############################
-- #############################

CREATE TABLE IF NOT EXISTS Projects
(
    project_id   BIGINT      NOT NULL AUTO_INCREMENT,
    project_name VARCHAR(50) NOT NULL,
    description  VARCHAR(400),
    PRIMARY KEY (project_id)
);

CREATE TABLE IF NOT EXISTS Tasks
(
    task_id      INT          NOT NULL PRIMARY KEY AUTO_INCREMENT,
    title        VARCHAR(100) NOT NULL,
    description  VARCHAR(250),
    is_completed BOOLEAN,
    project_id   BIGINT       NOT NULL,

    FOREIGN KEY (project_id) REFERENCES Projects (project_id)
);
-- #############################
-- #############################


INSERT INTO projects(project_name, description)
VALUES ('Demo project #1', 'Demo description #1');

INSERT INTO projects(project_name, description)
VALUES ('Demo project #2', 'Demo description #2');

INSERT INTO projects(project_name, description)
VALUES ('Demo project #3', 'Demo description #3');

INSERT INTO projects(project_name, description)
VALUES ('Sample project #1', 'Sample description #1');

INSERT INTO projects(project_name, description)
VALUES ('Sample project #2', 'Sample description #2');
-- #############################


INSERT INTO Tasks(title, description, is_completed, project_id)
VALUES ('Task #1', 'Task #1 -> Project #1 Description', FALSE, 1);
INSERT INTO Tasks(title, description, is_completed, project_id)
VALUES ('Task #2', 'Task #2 -> Project #1 Description', FALSE, 1);
INSERT INTO Tasks(title, description, is_completed, project_id)
VALUES ('Task #3', 'Task #3 -> Project #1 Description', FALSE, 1);
INSERT INTO Tasks(title, description, is_completed, project_id)
VALUES ('Task #4', 'Task #4 -> Project #1 Description', FALSE, 1);
INSERT INTO Tasks(title, description, is_completed, project_id)
VALUES ('Task #5', 'Task #5 -> Project #1 Description', FALSE, 1);

INSERT INTO Tasks(title, description, is_completed, project_id)
VALUES ('Task #1', 'Task #1 -> Project #2 Description', FALSE, 2);
INSERT INTO Tasks(title, description, is_completed, project_id)
VALUES ('Task #2', 'Task #2 -> Project #2 Description', FALSE, 2);
INSERT INTO Tasks(title, description, is_completed, project_id)
VALUES ('Task #3', 'Task #3 -> Project #2 Description', FALSE, 2);

INSERT INTO Tasks(title, description, is_completed, project_id)
VALUES ('Task #1', 'Task #1 -> Project #4 Description', FALSE, 4);
INSERT INTO Tasks(title, description, is_completed, project_id)
VALUES ('Task #2', 'Task #2 -> Project #4 Description', FALSE, 4);
INSERT INTO Tasks(title, description, is_completed, project_id)
VALUES ('Task #3', 'Task #3 -> Project #4 Description', FALSE, 4);
