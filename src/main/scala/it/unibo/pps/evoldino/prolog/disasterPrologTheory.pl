dinosaur_position(60, 20).
dinosaur_position(63, 27).
dinosaur_position(67, 23).
dinosaur_position(65, 25).
dinosaur_position(57, 15).
dinosaur_position(70, 30).
dinosaur_position(45, 45).
dinosaur_position(30, 30).
dinosaur_position(50, 50).

dis_extension_terremoto(E) :- E is 5.
dis_extension_meteorite(E) :- E is 0.

dis_position(60, 20, E) :- dis_extension_terremoto(E).
dis_position(70, 30, E) :- dis_extension_terremoto(E).
dis_position(50, 50, E) :- dis_extension_meteorite(E).

%somma
sum (A, B, C) :- C is (A + B).

%differenza
dif (A, B, C) :- C is (A - B).

%values included

includedIn(DIN, DIS, EXT) :- sum(DIS, EXT, DISEXTPOS),
	  		     dif(DIS, EXT, DISEXTNEG),
	                     (DIN < DISEXTPOS),
	                     (DIN > DISEXTNEG),
			     !.

includedIn(DIN, DIS, EXT) :- dif(DIS, EXT, DISEXTNEG), DIN = DISEXTNEG, !.

includedIn(DIN, DIS, EXT) :- sum(DIS, EXT, DISEXTPOS), DIN = DISEXTPOS, !.

doDisaster(dinosaur_position(DINX, DINY), dis_position(DISX,DISY,EXT)) :-
	dinosaur_position(DINX, DINY),
	dis_position(DISX, DISY, EXT),
	includedIn(DINX, DISX, EXT),
	includedIn(DINY, DISY, EXT).

%doDisaster(dinosaur_position(DINX, DINY), dis_position(DISX, DISY, EXT)).