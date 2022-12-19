%figli iniziali dinosauri
init_sons(S) :- S is 0.

%dinosauri
dinosaur(60, 20, S)  :- init_sons(S).
dinosaur(63, 27, S)  :- init_sons(S).
dinosaur(67, 23, S)  :- init_sons(S).
dinosaur(65, 25, S)  :- init_sons(S).
dinosaur(57, 15, S)  :- init_sons(S).
dinosaur(70, 30, S)  :- init_sons(S).
dinosaur(45, 45, S)  :- init_sons(S).
dinosaur(30, 30, S)  :- init_sons(S).
dinosaur(50, 50, S)  :- init_sons(S).

%estensione e numero figli
reproduction_extension_and_increase_son(R, I) :- R is 2, I is 1.
reproduction_extension_and_increase_sons(R, I) :- R is 3, I is 2.

%riproduzione
reproduction(60, 20, R, I) :- reproduction_extension_and_increase_son(R, I).
reproduction(70, 30, R, I) :- reproduction_extension_and_increase_sons(R, I).

%somma
sum (A, B, C) :- C is (A + B).

%differenza
dif (A, B, C) :- C is (A - B).

%valori inclusi (sia somma che differenza che uguale)

includedIn(DIN, REP, EXT) :- dif(REP, EXT, REPEXTNEG), DIN = REPEXTNEG, !.
includedIn(DIN, REP, EXT) :- sum(REP, EXT, REPEXTPOS),
	  		     dif(REP, EXT, REPEXTNEG),
	                     (DIN < REPEXTPOS),
	                     (DIN > REPEXTNEG),
			     !.
includedIn(DIN, REP, EXT) :- sum(REP, EXT, REPEXTPOS), DIN = REPEXTPOS, !.

%trova dinosauri che si riproducono e relativi figli da aggiungere
doReproduction(dinosaur(DINX, DINY, SON), reproduction(REPX, REPY, EXT, INC)) :-
	   dinosaur(DINX, DINY, SON),
	   reproduction(REPX, REPY, EXT, INC),
	   includedIn(DINX, REPX, EXT),
	   includedIn(DINY, REPY, EXT).

%aumenta numero dei figli
increaseDino(doReproduction(dinosaur(DINX, DINY, SON), reproduction(REPX, REPY, EXT, INC))) :-
	   doReproduction(dinosaur(DINX, DINY, SONTOINC), reproduction(REPX, REPY, EXT, INC)),
	   sum(SONTOINC,INC,SON).

%increaseDino(doReproduction(dinosaur(DINX, DINY, SON), reproduction(REPX, REPY, EXT, INC))).
