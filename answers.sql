use BDA_VQ;
/*Question 1*/
SELECT COUNT(*)
FROM INFORMATION_SCHEMA.TABLES
WHERE TABLE_TYPE = 'BASE TABLE';
/*Question 2*/
SELECT count(*) FROM users;
/*Question 3*/

/*Question 4*/
select count(correct_option) from multiple_choice_questions;
/*Question 5*/
select count(*) from quiz_course_close_assoc where courseid = 3;

