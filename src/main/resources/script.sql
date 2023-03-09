DROP TABLE groups CASCADE;
CREATE TABLE groups
(
    id   serial PRIMARY KEY,
    name varchar(5) NOT NULL

);
DROP TABLE students CASCADE;
CREATE TABLE students
(
    id serial PRIMARY KEY,
    group_id   integer REFERENCES groups,
    first_name varchar(20),
    last_name  varchar(20)
);
DROP TABLE courses CASCADE;
CREATE TABLE courses
(
    id          serial PRIMARY KEY,
    name        varchar(30),
    description text
);
DROP TABLE student_course CASCADE;
CREATE TABLE student_course
(
    student_id integer REFERENCES students,
    course_id  integer REFERENCES courses
);