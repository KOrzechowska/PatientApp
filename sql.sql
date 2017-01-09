insert into uzytkownicy (username, password, enabled) values ('jan.kowal@wp.pl', '123456789', true);
insert into uzytkownicy (username, password, enabled) values ('test@test.com', 'passw0rd', true);

insert into role_uzytkownikow (user_id, role) values (1, 'ROLE_DOCTOR');
insert into role_uzytkownikow (user_id, role) values (2, 'ROLE_NURSE');


insert into slownik_operacji (operacja_id, nazwa, opis, typ) values (1, 'TSH', 'Poziom tyreotropiny we krwi. Wartości referencyjne w zakresie 0,35-4 uIU/mL.', 'BADANIE');
insert into slownik_operacji (operacja_id, nazwa, opis, typ) values (2, 'Glukoza na czczo', 'Prawidłowa glikemia na czczo w zakresie 70-99 mg/dl. Nieprawidłowa glikemia na czczo w zakresie 100-125 mg/dl. Cukrzyca, po uzyskaniu w dwukrotnym badaniu >126 mg/dl.', 'BADANIE');
insert into slownik_operacji (operacja_id, nazwa, opis, typ) values (3, 'Insulina', 'Poziom insuliny we krwi. Wartości referencyjne w zakresie 2-22 uIU/mL.', 'BADANIE');
insert into slownik_operacji (operacja_id, nazwa, opis, typ) values (4, 'Witamina D', 'Poziom witaminy D we krwi. Rekomendacja w zakresie 30-50 ng/ml.', 'BADANIE');

insert into slownik_operacji (operacja_id, nazwa, opis, typ) values (5, 'Laparoskopia', 'Zabieg umożliwiający wizualną ocenę narządów miednicy mniejszej. W przeddzień oraz w dniu zabiegu należy przestrzegać specjalnej diety.', 'ZABIEG');
insert into slownik_operacji (operacja_id, nazwa, opis, typ) values (6, 'Wymiana zastawki serca', 'Zabieg kardiochirurgiczny. Wskazane jest odstawienie leków przeciwkrzepliwych. W przeddzień operacji należy zgłosić się do szpitala.', 'ZABIEG');
insert into slownik_operacji (operacja_id, nazwa, opis, typ) values (7, 'Septoplastyka', 'Korekcja przegrody nosa. Na kilka dni przed zabiegiem należy odbyć kosultację anastezjologiczną. Pozostać na czczo na 4-6 godzin przed zabiegiem.', 'ZABIEG');
insert into slownik_operacji (operacja_id, nazwa, opis, typ) values (8, 'Operacja jaskry', 'Obniżenie ciśnienia śródgałkowego. Szczegóły przygotowania do zabiegu należy omówić z lekarzem.', 'ZABIEG');
