% Facts
parent(pam, bob).
parent(tom, bob).
parent(tom, liz).
parent(bob, ann).
parent(bob, mary).
parent(mary, jim).

female(pam).
female(mary).
female(liz).
male(tom).
male(bob).
male(jim).

% Rules
sibling(Sib1, Sib2) :-
    parent(P1, Sib1),
    parent(P1, Sib2),
    Sib1 \= Sib2.

uncle(Uncle, Niece) :- 
    parent(P, Niece),
    sibling(P, Uncle),
    female(Uncle).

aunt(Aunt, Niece) :- 
    parent(P, Niece),
    sibling(P, Aunt),
    female(Aunt).

cousin()