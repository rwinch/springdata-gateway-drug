delete from message;
delete from user;
commit;


insert into user(id,email,firstname,lastname) values (0,'rob@example.com','Rob','Winch');
insert into user(id,email,firstname,lastname) values (1,'ollie@example.com','Oliver','Gierke');
insert into user(id,email,firstname,lastname) values (2,'josh@example.com','Josh','Long');
insert into user(id,email,firstname,lastname) values (3,'rod@example.com','Rod','Johnson');
insert into user(id,email,firstname,lastname) values (4,'juergen@example.com','Juergen','Hoeller');
insert into user(id,email,firstname,lastname) values (5,'chris@example.com','Chris','Beams');
insert into user(id,email,firstname,lastname) values (6,'phil@example.com','Phil','Webb');
insert into user(id,email,firstname,lastname) values (7,'luke@example.com','Luke','Taylor');
insert into user(id,email,firstname,lastname) values (8,'brian@example.com','Brian','Clozel');
insert into user(id,email,firstname,lastname) values (9,'dave@example.com','Dave','Syer');
insert into user(id,email,firstname,lastname) values (10,'greg@example.com','Greg','Turnquist');

insert into message(id,to_id,text) values (100,0,'This message is for Rob');
insert into message(id,to_id,text) values (101,0,'Second for Rob');
insert into message(id,to_id,text) values (102,0,'3rd for Rob');
insert into message(id,to_id,text) values (103,0,'4th for Rob');
insert into message(id,to_id,text) values (104,0,'5th for Rob');
insert into message(id,to_id,text) values (105,0,'6th for Rob');
insert into message(id,to_id,text) values (106,0,'7th for Rob');
insert into message(id,to_id,text) values (107,0,'8th for Rob');
insert into message(id,to_id,text) values (108,0,'9th for Rob');
insert into message(id,to_id,text) values (109,0,'10th for Rob');

insert into message(id,to_id,text) values (110,1,'This message is for Oliver');
insert into message(id,to_id,text) values (120,2,'This message is for Rob');
insert into message(id,to_id,text) values (130,3,'This message is for Oliver');
insert into message(id,to_id,text) values (140,4,'This message is for Rob');
insert into message(id,to_id,text) values (150,5,'This message is for Oliver');
insert into message(id,to_id,text) values (160,6,'This message is for Rob');
insert into message(id,to_id,text) values (170,7,'This message is for Oliver');
insert into message(id,to_id,text) values (180,8,'This message is for Rob');
insert into message(id,to_id,text) values (190,9,'This message is for Oliver');
insert into message(id,to_id,text) values (200,10,'This message is for Rob');
