%test part for that
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
