DROP TABLE IF EXISTS Tasks;
DROP TABLE IF EXISTS Projects;
DROP TABLE IF EXISTS Project_Status;
DROP TABLE IF EXISTS Task_Type;
DROP TABLE IF EXISTS Employees;
DROP TABLE IF EXISTS User_Role;
-- #############################
-- #############################

CREATE TABLE IF NOT EXISTS Project_Status
(
    status_id BIGINT      NOT NULL PRIMARY KEY AUTO_INCREMENT,
    title     VARCHAR(20) NOT NULL
);

CREATE TABLE IF NOT EXISTS Task_Type
(
    type_id BIGINT      NOT NULL PRIMARY KEY AUTO_INCREMENT,
    title   VARCHAR(20) NOT NULL
);

CREATE TABLE IF NOT EXISTS Projects
(
    project_id   BIGINT      NOT NULL AUTO_INCREMENT,
    project_name VARCHAR(50) NOT NULL,
    description  VARCHAR(400),

    status_id    BIGINT      NOT NULL,

    PRIMARY KEY (project_id),
    FOREIGN KEY (status_id) REFERENCES Project_Status (status_id)
);

CREATE TABLE IF NOT EXISTS Tasks
(
    task_id      BIGINT       NOT NULL PRIMARY KEY AUTO_INCREMENT,
    title        VARCHAR(100) NOT NULL,
    description  VARCHAR(250),
    is_completed BOOLEAN,

    project_id   BIGINT       NOT NULL,
    type_id      BIGINT       NOT NULL,

    FOREIGN KEY (project_id) REFERENCES Projects (project_id),
    FOREIGN KEY (type_id) REFERENCES Task_Type (type_id)
);

CREATE TABLE IF NOT EXISTS Employees
(
    employee_id BIGINT       NOT NULL PRIMARY KEY AUTO_INCREMENT,
    login       VARCHAR(100) NOT NULL UNIQUE,
    email       VARCHAR(30)  NOT NULL,
    password    VARCHAR(30)  NOT NULL,
    active      BOOLEAN      NOT NULL,
    first_name  VARCHAR(50),
    last_name   VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS User_Role
(
    employee_id BIGINT NOT NULL,
    roles       VARCHAR(255)
);
-- #############################
-- #############################

INSERT INTO Project_Status(status_id, title)
VALUES (1, 'Active');

INSERT INTO Project_Status(status_id, title)
VALUES (2, 'On Hold');

INSERT INTO Project_Status(status_id, title)
VALUES (3, 'Completed');
-- #############################


-- #############################
INSERT INTO Task_Type(type_id, title)
VALUES (1, 'Task');

INSERT INTO Task_Type(type_id, title)
VALUES (2, 'Fix');

INSERT INTO Task_Type(type_id, title)
VALUES (3, 'Bug');
-- #############################


-- #############################
INSERT INTO projects(project_name, description, status_id)
VALUES ('Demo project #1', 'Demo description #1', 1);

INSERT INTO projects(project_name, description, status_id)
VALUES ('Demo project #2', 'Demo description #2', 2);

INSERT INTO projects(project_name, description, status_id)
VALUES ('Demo project #3', 'Demo description #3', 1);

INSERT INTO projects(project_name, description, status_id)
VALUES ('Sample project #1', 'Sample description #1', 1);

INSERT INTO projects(project_name, description, status_id)
VALUES ('Sample project #2', 'Sample description #2', 3);
-- #############################


-- #############################
INSERT INTO Tasks(title, description, is_completed, project_id, type_id)
VALUES ('Task #1', 'Task #1 -> Project #1 Description', FALSE, 1, 1);
INSERT INTO Tasks(title, description, is_completed, project_id, type_id)
VALUES ('Task #2', 'Task #2 -> Project #1 Description', FALSE, 1, 1);
INSERT INTO Tasks(title, description, is_completed, project_id, type_id)
VALUES ('Task #3', 'Task #3 -> Project #1 Description', FALSE, 1, 2);
INSERT INTO Tasks(title, description, is_completed, project_id, type_id)
VALUES ('Task #4', 'Task #4 -> Project #1 Description', FALSE, 1, 3);
INSERT INTO Tasks(title, description, is_completed, project_id, type_id)
VALUES ('Task #5', 'Task #5 -> Project #1 Description', FALSE, 1, 1);

INSERT INTO Tasks(title, description, is_completed, project_id, type_id)
VALUES ('Task #1', 'Task #1 -> Project #2 Description', FALSE, 2, 1);
INSERT INTO Tasks(title, description, is_completed, project_id, type_id)
VALUES ('Task #2', 'Task #2 -> Project #2 Description', FALSE, 2, 1);
INSERT INTO Tasks(title, description, is_completed, project_id, type_id)
VALUES ('Task #3', 'Task #3 -> Project #2 Description', FALSE, 2, 1);

INSERT INTO Tasks(title, description, is_completed, project_id, type_id)
VALUES ('Task #1', 'Task #1 -> Project #4 Description', FALSE, 4, 2);
INSERT INTO Tasks(title, description, is_completed, project_id, type_id)
VALUES ('Task #2', 'Task #2 -> Project #4 Description', FALSE, 4, 1);
INSERT INTO Tasks(title, description, is_completed, project_id, type_id)
VALUES ('Task #3', 'Task #3 -> Project #4 Description', FALSE, 4, 3);
-- #############################


-- #############################
INSERT INTO Employees(login, email, password, active, first_name, last_name)
VALUES ('j_doe', 'john_doe@ecorp.com', 'john-doe', TRUE, 'John', 'Doe');

INSERT INTO Employees(login, email, password, active, first_name, last_name)
VALUES ('a_warton', 'alex_warton@gmail.com', 'a-warton', TRUE, 'Alex', 'Warton');

INSERT INTO Employees(login, email, password, active, first_name, last_name)
VALUES ('r_connor', 'ronald_connor@outlook.com', 'ronald-c', TRUE, 'Ronald', 'Connor');
-- #############################


-- #############################
INSERT INTO User_Role(employee_id, roles)
VALUES (1, 'USER'),
       (1, 'ADMIN');
-- #############################
