parent(carol, paul).
parent(carol, jane).
parent(harry, tom).
parent(harry, mary).
parent(jim, tom).
parent(jim, mary).
parent(ann, tom).
parent(ann, mary).
parent(tim, harry).
parent(tim, carol).
parent(george, harry).
parent(george, carol).
parent(jenny, henry).
parent(jenny, carol).
parent(david, jim).
parent(david, joan).
parent(kate, jim).
parent(kate, joan).
parent(susan, dom).
parent(susan, ann).

male(paul).
male(tom).
male(harry).
male(jim).
male(dom).
male(tim).
male(george).
male(david).

female(jane).
female(mary).
female(carol).
female(joan).
female(ann).
female(jenny).
female(kate).
female(susan).

% Q1
mother(X, Y) :- 
    parent(X, Y), 
    female(X).

sibling(X, Y) :- 
    parent(Z, X), 
    parent(Z, Y), 
    X \= Y.

uncle(X, Y) :- 
    parent(Z, Y), 
    sibling(Z, X), 
    male(X).

% Q2
cousin(X, Y) :- 
    parent(P1, X), 
    parent(P2, Y), 
    sibling(P1, P2).

paternalgrandmother(X, Y) :- 
    parent(Father, Y), 
    male(Father), 
    parent(X, Father), 
    female(X).

% Q3
odd(X) :- X mod 2 =:= 1. 

oddsum(0, []).
oddsum(X, [H|T]) :- 
    odd(H),
    oddsum(Y, T),
    X is H + Y,
    !.
oddsum(X, [H|T]) :-
    \+ odd(H),
    oddsum(X, T),
    !.

max_of_two(A, B, A) :- A > B.
max_of_two(A, B, B) :- B > A.
maximum(0, []).
maximum(X, [H|T]) :-
    maximum(Y, T),
    max_of_two(H, Y, X).
