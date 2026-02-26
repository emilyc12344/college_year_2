/*
Facts:
Relations between atoms (start with lowercase)
End with a period .

Queries:
Variables start with uppercase or underscores
Atoms start with lowercase
; asks for the next solution
Press Enter to stop

Rules:
Head (left) is true if Body (right) is true
:- means "if"
Variables are "for all"

Recursive Rules:
Base Case stops recursion
Recursive case calls itself on smaller problem

Data Types:
Atoms
- Lowercase
- Special chars
- Single quotes

Numbers
- integers
- floats

Variables
- Uppercase
- Underscore
- Anonymous variable _: use hen value is not needed

Matching:
1. Constants match if identical
2. Variable matches anything 
3. Structures match if same functor and all components match

Lists:
[] - empty list
[a, b, c] - list of atoms
[a, [b, c]] - nested list
[Head|Tail] - Head = first element, Tail = rest
[x|_] - X is head, ignore tail

List Operations:
member(X, [X|_]) - X is head
member(X, [_|Tail]) :- member(X, Tail). - X is in tail

conc([], L, L).
conc([X|L1], L2, [X|L3]) :- conc(L1, L2, L3).

add(X, L, [X|L]). - add to front
delete(X, [X|L], L) - delete head
delete(X, [Y|Tail], [Y|Tail1]) :- delete(X, Tail, Tail1).

sublist(S, L) :- conc(L1, L2, L), conc(S, L3, L2).

permutation([], []).
permutation([X|L], P) :- permutation(L, L1), insert(X, L1, P).

Arithmetic:
X is 1 + 2 (not X = 1 + 2)
> < >= =< =:=(equal) =\=(not equal)

The Cut(!):
max(X, Y, X) :- X >= Y, !. - if true, stop here
max(X, Y, Y). otherwise try this
No alternative solutions considered

Programming Tips:
1. Use recursion with base/ recursive case
2. Generalise problems
3. Use meaningfull names for predictates and variables
4. Keep clauses short
5. Use indentation consistently
6. Comment what predictates do
7. Test small parts 
8. Be careul with cut - it changes backtracking behaviour
9. Tail recursion is more efficient
10. Avoid assert/ retract unless necessary
*/