use BDA_VQ;
/*Question 1*/
SELECT COUNT(*) FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_TYPE = 'BASE TABLE';
/*Question 2*/
SELECT count(*) FROM users;
/*Question 3*/
set @mint = (SELECT MIN(TABLE_ROWS) FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_TYPE = 'BASE TABLE');
SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_TYPE = 'BASE TABLE'and TABLE_ROWS = @mint;
/*Question 4*/
select count(correct_option) from multiple_choice_questions;
/*Question 5*/
select count(*) from quiz_course_close_assoc where courseid = 3;
/*Question 6*/
select count(*), courseid from quiz_course_close_assoc GROUP BY courseid;
/*Question 7*/
select MAX(question_id) from code_answers;/*26*/
select DISTINCT quizid from code_answers where question_id = 26;/*53*/
select courseid from quiz_course_close_assoc where quizid = 53;/*11, 12*/
select * from courses where courseid = 11 or courseid = 12;
/*Question 8*/
set @funAll = (select count(distinct quizid, question_id, userid) from code_answers where question_type = 1);
set @funC = (select count(distinct quizid, question_id, userid) from code_answers where question_type = 1 AND correct = 1);
select @funC / @funAll; 
/*Question 9*/
set @qlambda = (select quizid from lambda_assoc);/*11*/
select count(*) from quiz_course_close_assoc where quizid = @qlambda;
/*Question 10*/
set @maxC = (select max(total) from (select count(*) as total from code_answers where question_type = 1 AND correct = 1 GROUP BY userid) as result);
select user from (select count(*) as total, userid as user 
from code_answers where question_type = 1 AND correct = 1 GROUP BY userid) as result where total = @maxC;
/*Question 11*/
SELECT s_t FROM (SELECT count(*) AS ct, spec_type AS s_t 
FROM variable_specifications GROUP BY spec_type) AS top3 ORDER BY ct DESC LIMIT 3;
/*Question 12*/
set @codeQ = (select count(distinct quizid) from code_answers);
set @allQ = (select count(*) from quizzes);
select @codeQ / @allQ;
/*Question 13*/
set @mulQ = (select count(*) from multiple_choice_assoc);
select @mulQ/@allQ;
/*Question 14*/
SET @codeQQ = (SELECT COUNT(DISTINCT quizid, question_id) FROM  code_answers);
SELECT @codeQQ/@allQ;
/*Question 15*/
SELECT MAX(attemps) from (SELECT COUNT(*) AS attemps FROM code_answers GROUP BY userid) as maxAttempts;




