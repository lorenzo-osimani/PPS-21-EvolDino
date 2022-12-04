%vita iniziale dinosauri
init_life(L) :- L is 100.

%dinosauri
dinosaur(60, 20, L)  :- init_life(L).
dinosaur(63, 27, L)  :- init_life(L).
dinosaur(67, 23, L)  :- init_life(L).
dinosaur(65, 25, L)  :- init_life(L).
dinosaur(57, 15, L)  :- init_life(L).
dinosaur(70, 30, L)  :- init_life(L).
dinosaur(45, 45, L)  :- init_life(L).
dinosaur(30, 30, L)  :- init_life(L).
dinosaur(50, 50, L)  :- init_life(L).

%estensione e danno disastri
disaster_extension_and_damage_terremoto(E, D) :- E is 5, D is 10.
disaster_extension_and_damage_meteorite(E, D) :- E is 0, D is 30.

%disastri
disaster(60, 20, E, D) :- disaster_extension_and_damage_terremoto(E, D).
disaster(70, 30, E, D) :- disaster_extension_and_damage_terremoto(E, D).
disaster(50, 50, E, D) :- disaster_extension_and_damage_meteorite(E, D).

%somma
sum (A, B, C) :- C is (A + B).

%differenza
dif (A, B, C) :- C is (A - B).

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

%danneggia dinosauri (sempre partendo dai life points iniziali)
damageDino(doDisaster(dinosaur(DINX, DINY, LIF), disaster(DISX, DISY, EXT, DAM))) :-
	   doDisaster(dinosaur(DINX, DINY, LIFTODAM), disaster(DISX, DISY, EXT, DAM)),
	   dif(LIFTODAM,DAM,LIF).

%damageDino(doDisaster(dinosaur(DINX, DINY, LIF), disaster(DISX, DISY, EXT, DAM))).