%estensione e danno disastri
disaster_extension_and_damage_terremoto(E, D) :- E is 5, D is 10.
disaster_extension_and_damage_meteorite(E, D) :- E is 0, D is 30.

%disastri
disaster(60, 20, E, D) :- disaster_extension_and_damage_terremoto(E, D). %hit from 55 to 65 and from 15 to 25 with damage 10.
disaster(70, 30, E, D) :- disaster_extension_and_damage_terremoto(E, D). %hit from 65 to 75 and from 25 to 35 with damage 10.
disaster(94, 86, E, D) :- disaster_extension_and_damage_terremoto(E, D). %hit from 89 to 99 and from 81 to 91 with damage 10.
disaster(50, 50, E, D) :- disaster_extension_and_damage_meteorite(E, D). %hit 50 and 50 with damage 30.
disaster(90, 90, E, D) :- disaster_extension_and_damage_meteorite(E, D). %hit 90 and 90 with damage 30.

%vita iniziale dinosauri
init_life(L) :- L is 100.

%dinosauri
dinosaur(60, 20, L)  :- init_life(L). %could be hit by 1st disaster. life 90.
dinosaur(63, 27, L)  :- init_life(L). %could be NOT hit. life 100.
dinosaur(67, 23, L)  :- init_life(L). %could be NOT hit. life 100.
dinosaur(65, 25, L)  :- init_life(L). %could be hit by 1st and 2nd disaster. life 80.
dinosaur(57, 15, L)  :- init_life(L). %could be hit by 1st disaster. life 90.
dinosaur(70, 30, L)  :- init_life(L). %could be hit by 2nd disater. life 90.
dinosaur(45, 45, L)  :- init_life(L). %could be NOT hit. life 100.
dinosaur(30, 30, L)  :- init_life(L). %could be NOT hit. life 100.
dinosaur(50, 50, L)  :- init_life(L). %could be hit by 4th. life 70.
dinosaur(90, 90, L)  :- init_life(L). %could be hit by 3rd and 5th. life 60.

%somma
sum(A, B, C) :- C is (A + B).

%differenza
dif(A, B, C) :- C is (A - B).

%valori inclusi (sia somma che differenza che uguale)

includedIn(DIN, DIS, EXT) :- dif(DIS, EXT, DISEXTNEG), DIN = DISEXTNEG, !.
includedIn(DIN, DIS, EXT) :- sum(DIS, EXT, DISEXTPOS),
          dif(DIS, EXT, DISEXTNEG),
                      (DIN < DISEXTPOS),
                      (DIN > DISEXTNEG),
        !.
includedIn(DIN, DIS, EXT) :- sum(DIS, EXT, DISEXTPOS), DIN = DISEXTPOS, !.

%trova dinosauri colpiti e relativi disastri applicati
doDisaster(dinosaur(DINX, DINY, LIF), disaster(DISX, DISY, EXT, DAM)) :-
    dinosaur(DINX, DINY, LIF),
    disaster(DISX, DISY, EXT, DAM),
    includedIn(DINX, DISX, EXT),
    includedIn(DINY, DISY, EXT).

listDisasters([disaster(60, 20, E1, D1), disaster(70, 30, E2, D2), disaster(94, 86, E3, D3), disaster(50, 50, E4, D4), disaster(90, 90, E5, D5)]).

%danneggia dinosauri (sempre partendo dai life points iniziali)
damageDino(dinosaur(DINX, DINY, LIF)) :-
    listDisasters([disaster(DISX, DISY, EXT, DAM)|T]),
    damageDino2(dinosaur(DINX, DINY, LIF), [disaster(DISX, DISY, EXT, DAM)|T]).

damageDino2(dinosaur(DINX, DINY, LIF), [disaster(DISX, DISY, EXT, DAM)|T]) :-
 damageDino2(dinosaur(DINX, DINY, LIF), T), not(doDisaster(dinosaur(DINX, DINY, _), disaster(DISX, DISY, EXT, DAM))).

damageDino2(dinosaur(DINX, DINY, LIF), [disaster(DISX, DISY, EXT, DAM)|T]) :-
  doDisaster(dinosaur(DINX, DINY, _), disaster(DISX, DISY, EXT, DAM)), damageDino2(dinosaur(DINX, DINY, LIFTODAM), T), dif(LIFTODAM,DAM,LIF).

damageDino2(dinosaur(DINX, DINY, LIF), []) :- dinosaur(DINX, DINY, LIF).

dinoEX(DIX,DINY,LIF) :- DINX is 60, DINY is 20, LIF is 90.
dinoEX(DIX,DINY,LIF) :- DINX is 63, DINY is 27, LIF is 100.
dinoEX(DIX,DINY,LIF) :- DINX is 67, DINY is 23, LIF is 100.
dinoEX(DIX,DINY,LIF) :- DINX is 65, DINY is 25, LIF is 80.
dinoEX(DIX,DINY,LIF) :- DINX is 57, DINY is 15, LIF is 90.
dinoEX(DIX,DINY,LIF) :- DINX is 70, DINY is 30, LIF is 90.
dinoEX(DIX,DINY,LIF) :- DINX is 45, DINY is 45, LIF is 100.
dinoEX(DIX,DINY,LIF) :- DINX is 30, DINY is 30, LIF is 100.
dinoEX(DIX,DINY,LIF) :- DINX is 50, DINY is 50, LIF is 70.
dinoEX(DIX,DINY,LIF) :- DINX is 90, DINY is 90, LIF is 60.

test(dinosaur(DINXEX, DINYEX, LIFEX)) :- dinoEX(DINXEX,DINYEX,LIFEX), damageDino(dinosaur(DINXEX, DINYEX, LIFEX)).

%test(damageDino(dinosaur(DINX, DINY, LIF))) :-  dinoEX(DINXEX,DINYEX,LIFEX), DINXEX is DINX, DINYEX is DINY, LIFEX is LIF.
%test(dinosaur(DINX, DINY, LIF)) :- damageDino(dinosaur(DINX, DINY, LIF)), dinoEX(DINXEX,DINYEX,LIFEX).
%test(dinosaur(DINXEX, DINYEX, LIFEX)) :- dinoEX(DINXEX,DINYEX,LIFEX), damageDino(dinoEX(DINXEX, DINYEX, LIFEX)).
%(63,27,100), (67,23,100), (65,25,80), (57,15,90), (70,30,90), (45,45,100), (30,30,100), (50,50,70), (90,90,60).



