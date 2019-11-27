CREATE TABLE `sys_class`  (
  `class_id` varchar(36)  NOT NULL,
  `class_name` varchar(50)  NOT NULL COMMENT '班级名称',
  PRIMARY KEY (`class_id`)
) COMMENT='用户表';

CREATE TABLE `sys_student`  (
  `student_id` varchar(36)  NOT NULL,
  `student_name` varchar(50)  NOT NULL COMMENT '学生名称',
	`class_id` varchar(36)  NOT NULL,
  PRIMARY KEY (`student_id`),
	INDEX `fk_student_class`(`class_id`) ,
  CONSTRAINT `fk_student_class` FOREIGN KEY (`class_id`) REFERENCES `sys_class` (`class_id`) ON DELETE NO ACTION ON UPDATE RESTRICT
) COMMENT='学生表';


CREATE TABLE `sys_course`  (
  `course_id` varchar(36)  NOT NULL,
  `course_name` varchar(50)  NOT NULL COMMENT '课程名称',
  PRIMARY KEY (`course_id`)
) COMMENT='课程表';

CREATE TABLE `sys_course_student`  (
  `course_student_id` varchar(36) NOT NULL,
  `course_id` varchar(36)  NOT NULL,
  `student_id` varchar(36)  NOT NULL,
  `score` int(11) DEFAULT NULL,
  PRIMARY KEY (`course_student_id`),
  INDEX `fk_course_student_course`(`course_id`) ,
  INDEX `fk_course_student_student`(`student_id`) ,
  CONSTRAINT `fk_course_student_course` FOREIGN KEY (`course_id`) REFERENCES `sys_course` (`course_id`) ON DELETE NO ACTION ON UPDATE RESTRICT,
  CONSTRAINT `fk_course_student_student` FOREIGN KEY (`student_id`) REFERENCES `sys_student` (`student_id`) ON DELETE NO ACTION ON UPDATE RESTRICT
) COMMENT='课程学生表';

CREATE TABLE `sys_course_class`  (
  `course_class_id` varchar(36) NOT NULL,
  `course_id` varchar(36)  NOT NULL,
  `class_id` varchar(36)  NOT NULL,
  PRIMARY KEY (`course_class_id`),
  INDEX `fk_course_class_course`(`course_id`) ,
  INDEX `fk_course_class_class`(`class_id`) ,
  CONSTRAINT `fk_course_class_course` FOREIGN KEY (`course_id`) REFERENCES `sys_course` (`course_id`) ON DELETE NO ACTION ON UPDATE RESTRICT,
  CONSTRAINT `fk_course_class_class` FOREIGN KEY (`class_id`) REFERENCES `sys_class` (`class_id`) ON DELETE NO ACTION ON UPDATE RESTRICT
) COMMENT='课程班级表';

insert into sys_class (class_id,class_name)  values("1","一班");
insert into sys_class (class_id,class_name)  values("2","二班");

insert into sys_course (course_id,course_name)  values("1","数学");
insert into sys_course (course_id,course_name)  values("2","语文");

insert into sys_student (student_id,student_name,class_id)  values("1","陈昭宇","1");
insert into sys_student (student_id,student_name,class_id)  values("2","周杰伦","1");

insert into sys_course_student(course_student_id,course_id,student_id,score) values("1","1","1","10");
insert into sys_course_student(course_student_id,course_id,student_id,score) values("2","2","1","60");

insert into sys_course_student(course_student_id,course_id,student_id,score) values("3","1","2","45");
insert into sys_course_student(course_student_id,course_id,student_id,score) values("4","2","2","56");


insert into sys_course_class(course_class_id,course_id,class_id) values("1","1","1");
insert into sys_course_class(course_class_id,course_id,class_id) values("2","2","1");

insert into sys_course_class(course_class_id,course_id,class_id) values("3","1","2");
insert into sys_course_class(course_class_id,course_id,class_id) values("4","2","2");


insert into sys_student (student_id,student_name,class_id)  values("3","杨万鹏","2");
insert into sys_student (student_id,student_name,class_id)  values("4","高子健","2");

insert into sys_course_student(course_student_id,course_id,student_id,score) values("5","1","3","23");
insert into sys_course_student(course_student_id,course_id,student_id,score) values("6","2","3","63");

insert into sys_course_student(course_student_id,course_id,student_id,score) values("7","1","4","78");
insert into sys_course_student(course_student_id,course_id,student_id,score) values("8","2","4","95");


--查出学生课程总分
select s.student_name,sum(sc.score) 
from sys_student s
inner join sys_course_student sc on s.student_id=sc.student_id
group by s.student_id

--课程平均分
select c.course_name,avg(sc.score) 
from sys_course c
inner join sys_course_student sc on c.course_id=sc.course_id
GROUP BY sc.course_id

--学生各课程分数
select s.student_name,c.course_name ,sc.score
from sys_student s
inner join sys_course_student sc on s.student_id=sc.student_id
inner join sys_course c on sc.course_id=c.course_id
order by s.student_name

--各班级各课程平均分
select cla.class_name,c.course_name,avg(cs.score)  
from sys_class cla
inner join sys_student s on cla.class_id=s.class_id
inner join sys_course_student cs on s.student_id =cs.student_id
inner join sys_course c on cs.course_id=c.course_id
GROUP BY cla.class_id,cs.course_id