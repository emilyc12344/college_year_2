/* 1. Minimum of Two Numbers
Write a Prolog relation min(X, Y, Z) that is true if Z is the minimum of X and Y. 
Write two versions: one without a cut and one with a cut. Compare their efficiency.
*/
min(X, Y, Z) :- X < Y, Z is X, !.
min(X, Y, Z) :- Y < X, Z is X, !.

/* 2. Safe Division
Write a Prolog relation safeDivide(X, Y, Result) that is true if Result is X divided by Y. 
If Y is zero, the relation should fail immediately without attempting division. Use a cut.
*/
safeDivide(X, Y, Result) :- Y =:= 0, !, fail.
safeDivide(X, Y, Result) :-
    Result is X / Y.

/* 3. Grade Classification
Write a Prolog relation grade(Mark, Result) that classifies a numerical mark into:

fail if Mark < 40

pass if 40 ≤ Mark < 60

merit if 60 ≤ Mark < 70

distinction if Mark ≥ 70

Use cuts to ensure that once a grade is determined, no further clauses are tried.
*/

grade(M, fail) :- M < 40, !.
grade(M, pass) :- M >= 40, M < 60, !.
grade(M, merit) :- M >= 60, M < 70, !.
grade(M, distinction) :- M >= 70.

/* 4. First Occurrence
Write a Prolog relation firstOccurrence(X, List, Position) that is true if Position is the index (starting from 1) of the first occurrence of X in List. Use a cut to stop searching after finding X. For example:

text
?- firstOccurrence(b, [a,b,c,b,d], P).
P = 2
*/