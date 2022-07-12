INSERT INTO employee(id, name, age)
VALUES ('1', 'Tom', 30);

/* User Master */
INSERT INTO m_user(user_id, password, user_name, birthday, age, gender, department_id, role)
VALUES ('system@co.jp', '$2a$10$RNM65itbGqR7AYCSp01.sO./ltcwXe8xYD8HUxpx1Yf01PWRB36Ey',
        'System Administrator',
        '2000-01-01', 21, 1, 1,
        'ROLE_ADMIN'),

       ('user@co.jp', '$2a$10$QoJQ9rVDvMD3lRGG.qzoy.YzhosZj1cVz43jtY2i4pBTctLrN.bsK',
        'User1',
        '2000-01-01', 21, 2, 2,
        'ROLE_GENERAL');

/* Department Master */
INSERT INTO m_department(department_id, department_name)
VALUES (1, 'System Management'),
       (2, 'Sales');

/* Salary Table */
INSERT INTO t_salary(user_id, year_month, salary)
VALUES ('user@co.jp', '11/2020', 2800),
       ('user@co.jp', '12/2020', 2900),
       ('user@co.jp', '01/2021', 3000);

INSERT INTO m_user(user_id, password, user_name, birthday, age, gender, department_id, role)
VALUES ('tom@gmail.com',
        '$2a$10$/aBhFUYx0UHBZ.kb0TBQguZVZbaL0sJ1PstlPZqw.1qbETK.rnwtS',
        'Tom',
        '1986-11-05', 35, 1, 2,
        'ROLE_GENERAL');