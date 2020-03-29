CREATE DATABASE exam_online_sys DEFAULT CHARACTER SET UTF8;

insert into SYS_USER(name_, login_name_, password_) VALUES ('管理员','admin','$2a$10$OZkHd5LbJmQcBs5NUwetL.xafO5ThfwIW3dYCwU514oVopgRmOghu');
insert into SYS_USER(name_, login_name_, password_) VALUES ('教师1','teacher1','$2a$10$OZkHd5LbJmQcBs5NUwetL.xafO5ThfwIW3dYCwU514oVopgRmOghu');
insert into SYS_USER(name_, login_name_, password_) VALUES ('学生1','student1','$2a$10$OZkHd5LbJmQcBs5NUwetL.xafO5ThfwIW3dYCwU514oVopgRmOghu');

insert into SYS_USER_ROLE(sys_user_id_, role_) values (1,'ROLE_ADMIN');
insert into SYS_USER_ROLE(sys_user_id_, role_) values (2,'ROLE_TEACHER');
insert into SYS_USER_ROLE(sys_user_id_, role_) values (3,'ROLE_STUDENT');