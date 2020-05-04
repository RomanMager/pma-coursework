DROP TABLE IF EXISTS Tasks;
DROP TABLE IF EXISTS Projects;
DROP TABLE IF EXISTS Project_Status;

CREATE TABLE IF NOT EXISTS Project_Status
(
    status_id BIGINT      NOT NULL PRIMARY KEY AUTO_INCREMENT,
    title     VARCHAR(20) NOT NULL
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

    FOREIGN KEY (project_id) REFERENCES Projects (project_id)
);
