/* FACTS */
parents(david, george, noreen).
parents(jennifer, george, noreen).
parents(georgejr, goerge, noreen).
parents(scott, george, noreen).
parents(joanne, george, noreen).
parents(jessica, david, edel).
parents(clara, david, edel).
parents(michael, david, edel).
parents(laura, georgejr, susan).
parents(anna, scott, siobhan).

/* RELATIONSHIPS */
father(X, Y) :- parents(Y, X, _).
male(X) :- father(X, _).

mother(X, Y) :- parents(Y, _, X).
female(X) :- mother(X, _).

grandfather(X, Y) :- father(X, Z), father(Z, Y).
grandfather(X, Y) :- father(X, Z), mother(Z, Y).

grandmother(X, Y) :- mother(X, Z), mother(Z, Y).
grandmother(X, Y) :- mother(X, Z), father(Z, Y).

brother(X, Y) :- male(X), father(Z, X), father(Z, Y).

sister(X, Y) :- female(X), father(Z, X), father(Z, Y).

uncle(X, Y) :- father(Z, Y), brother(Z, X).
uncle(X, Y) :- mother(Z, Y), brother(Z, X).

aunt(X, Y) :- father(Z, Y), sister(Z, X).
aunt(X, Y) :- mother(Z, Y), sister(Z, X).

cousin(X, Y) :- father(Z, X), father(Q, Y), brother(Z, Q).
cousin(X, Y) :- father(Z, X), mother(Q, Y), brother(Z, Q).
cousin(X, Y) :- father(Z, X), father(Q, Y), sister(Z, Q).
cousin(X, Y) :- father(Z, X), mother(Q, Y), sister(Z, Q).
