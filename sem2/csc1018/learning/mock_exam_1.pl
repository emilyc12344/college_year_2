% A
parent(pam, bob).
parent(tom, bob).
parent(tom, liz).
parent(bob, ann).
parent(bob, mary).
parent(mary, jim).
parent(ann, sarah).

female(pam).
female(mary).
female(liz).
male(tom).
male(bob).
male(jim).

sibling(X, Y) :- 
    parent(Z, X),
    parent(Z, Y).
    X \= Y.

grandchild(X, Y) :- 
    parent(Y, Z),
    parent(Z, X).

niece(X, Y) :- 
    sibling(Y, Z),
    female(X),
    parent(Z, X).

hasDescendants(X) :-
    parent(X, _).

% c
% BOOK FACTS
book(illiad, homer, study, 500).
book(c, richie, study, 150).
book(nt_bible, sams, reference, 480).
book(monty_python, close, comedy, 300).
book(hamlet, shakespeare, drama, 200).
book(hobbit, tolkien, fiction, 310).
book(odyssey, homer, crime, 450).

% BUILD LIBRARY
buildLibrary(Lib) :-
    findall(book(T,A,G,P), book(T,A,G,P), Lib).

shortBook(book(_, _, _, P)) :- 
    P < 200.

fictionBooks(Lib, Result(T, A, G, P)) :-
    findall(book(T, A, G, P), 
    (member(book(T, A, G, P), Lib), G = fiction), 
    Result).