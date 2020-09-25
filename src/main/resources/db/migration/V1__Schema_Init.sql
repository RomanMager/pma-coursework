DROP TABLE IF EXISTS Task_Employee;
DROP TABLE IF EXISTS Tasks;
DROP TABLE IF EXISTS Task_Type;
DROP TABLE IF EXISTS User_Role;
DROP TABLE IF EXISTS Project_Employee;
DROP TABLE IF EXISTS Employees;
DROP TABLE IF EXISTS Projects;
DROP TABLE IF EXISTS Project_Status;
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
    email       VARCHAR(30)  NOT NULL UNIQUE,
    password    VARCHAR(300) NOT NULL,
    active      BOOLEAN      NOT NULL,
    first_name  VARCHAR(50),
    last_name   VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS User_Role
(
    employee_id BIGINT NOT NULL,
    roles       VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS Project_Employee
(
    employee_id BIGINT NOT NULL,
    project_id  BIGINT NOT NULL,

    FOREIGN KEY (employee_id) REFERENCES Employees (employee_id),
    FOREIGN KEY (project_id) REFERENCES Projects (project_id),

    PRIMARY KEY (employee_id, project_id)
);

CREATE TABLE IF NOT EXISTS Task_Employee
(
    employee_id BIGINT NOT NULL,
    task_id     BIGINT NOT NULL,

    FOREIGN KEY (employee_id) REFERENCES Employees (employee_id),
    FOREIGN KEY (task_id) REFERENCES Tasks (task_id),

    PRIMARY KEY (employee_id, task_id)
);
-- #############################
-- #############################
