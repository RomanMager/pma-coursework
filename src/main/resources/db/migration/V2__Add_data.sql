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
VALUES ('Update project Angular version to v8', 'Front-end stuff', 1);
-- #############################


-- #############################
INSERT INTO Tasks(title, description, is_completed, project_id, type_id)
VALUES ('Update npm dependency', '', FALSE, 1, 1);

INSERT INTO Tasks(title, description, is_completed, project_id, type_id)
VALUES ('Change version in package.json', 'after update test the application', FALSE, 1, 1);

INSERT INTO Tasks(title, description, is_completed, project_id, type_id)
VALUES ('Fix missing dependencies', 'library for parsing still does not work', FALSE, 1, 2);
-- #############################


-- #############################
-- password: roman-mager-1002
INSERT INTO Employees(login, email, password, active, first_name, last_name)
VALUES ('roman_mager', 'roman.mager@outlook.com', '$2y$08$nfKPadmqtl7/W2ldU27wOuNVy8NLcmcV4BPGhyqwPgijLEIEw73e6', TRUE,
        'Roman', 'Mager');

-- password: john-doe
INSERT INTO Employees(login, email, password, active, first_name, last_name)
VALUES ('john_doe', 'john_doe@ecorp.com', '$2y$08$up90CD0vXsiDsxi5pJukKuv6G1On1sIwwVA7Cln3HeRV9LKZLceUm', TRUE, 'John',
        'Doe');

-- password: a-warton
INSERT INTO Employees(login, email, password, active, first_name, last_name)
VALUES ('a_warton', 'alex_warton@gmail.com', '$2y$08$9JMb/5f8ifk5slnixAs.9eJ5Mo1lDPO5ndt20zRMHkS1BwzixIEL.', TRUE,
        'Alex', 'Warton');
-- #############################


-- #############################
INSERT INTO User_Role(employee_id, roles)
VALUES (1, 'USER'),
       (1, 'ADMIN'),
       (2, 'USER'),
       (3, 'USER');
-- #############################
