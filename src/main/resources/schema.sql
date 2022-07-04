CREATE TABLE IF NOT EXISTS employee (
    id   VARCHAR(32) PRIMARY KEY,
    name VARCHAR(64),
    age  INT
);

/* User master */
CREATE TABLE IF NOT EXISTS m_user (
    user_id       VARCHAR(32) PRIMARY KEY,
    password      VARCHAR(128),
    user_name     VARCHAR(64),
    birthday      DATE,
    age           INT,
    gender        INT,
    department_id INT,
    role          VARCHAR(64)
);

/* Department master */
CREATE TABLE IF NOT EXISTS m_department (
    department_id   INT PRIMARY KEY,
    department_name VARCHAR(64)
);

/* Salary table */
CREATE TABLE IF NOT EXISTS t_salary (
    user_id    VARCHAR(64),
    year_month VARCHAR(64),
    salary     INT,
    PRIMARY KEY (user_id, year_month)
);