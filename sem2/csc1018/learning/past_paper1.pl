road(northbog, westhome, 3).
road(oldtown, westhome, 4).
road(oldtown, poshville, 5).
road(villanua, oldtown, 2).
road(eastwick, poshville, 2).
road(poshville, northbog, 3).
road(westpark, villanua, 2).
road(westpark, southfork, 7).
road(southfork, eastwick, 2).

% Q1
between(X, Y, Z) :- road(X, Y, _), road(Y, Z, _).
closeto(X, Y) :- road(X, Y, Len), Len =< 3.

% Q2
route(A, B) :- road(A, B, _).
route(A, B) :- road(A, C, _), route(C, B).

% Q3
sum(0, []).
sum(X, [H|T]) :-
    sum(Y, T),
    X is H + Y.


max_of_two(A, B, A) :- A >= B.
max_of_two(A, B, B) :- A < B.

maximum(X, [X]).
maximum(X, [H|T]) :-
    maximum(Y, T),
    max_of_two(H, Y, X),
    !.