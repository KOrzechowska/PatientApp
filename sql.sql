insert into uzytkownicy (username, password, enabled) values ('jan.kowal@wp.pl', '123456789', true);
insert into uzytkownicy (username, password, enabled) values ('test@test.com', 'passw0rd', true);

insert into role_uzytkownikow (user_id, role) values (1, 'ROLE_DOCTOR');
insert into role_uzytkownikow (user_id, role) values (2, 'ROLE_NURSE');
