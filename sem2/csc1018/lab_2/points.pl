% Facts
% _ is west of _
west(a, b).
west(b, c).
west(c, d).
west(d, e).
west(f, g).
west(g, h).
west(h, i).
west(i, j).
west(k, l).
west(l, m).
west(m, n).
west(n, o).
west(p, q).
west(q, r).
west(r, s).
west(s, t).

% _ is north _
north(a, f).
north(b, g).
north(c, h).
north(d, i).
north(e, j).
north(f, k).
north(g, l).
north(h, m).
north(i, n).
north(j, o).
north(k, p).
north(l, q).
north(m, r).
north(n, s).
north(o, t).

% Rules

east(P1, P2) :- 
    west(P2, P1).

south(P1, P2) :- 
    north(P2, P1).

duenorth(P1, P2) :- % Base Case
    north(P1, P2).
duenorth(P1, P2) :- % Recursive Case
    north(P1, P3),
    duenorth(P3, P2).

duesouth(P1, P2) :- % Base Case
    south(P1, P2).
duesouth(P1, P2) :- % Recursive Case
    south(P1, P3),
    duesouth(P3, P2).

dueeast(P1, P2) :- % Base Case
    east(P1, P2).
dueeast(P1, P2) :- % Recursive Case
    east(P1, P3),
    dueeast(P3, P2).

duewest(P1, P2) :- % Base Case
    west(P1, P2).
duewest(P1, P2) :- % Recursive Case
    west(P1, P3),
    duewest(P3, P2).

northwest(P1, P2) :- % base case
    north(P3, P2),
    west(P1, P3).
northwest(P1, P2) :- % recursive case
    north(P3, P2),
    west(P4, P3),
    northwest(P1, P4).

northeast(P1, P2) :- 
    north(P3, P2),
    east(P1, P3).
northeast(P1, P2) :- 
    north(P3, P2),
    east(P4, P3),
    northeast(P1, P4).

southwest(P1, P2) :-
    south(P3, P2),
    west(P1, P3).
southwest(P1, P2) :- 
    south(P3, P2),
    west(P4, P3),
    southwest(P1, P4).

southeast(P1, P2) :-
    south(P3, P2),
    east(P1, P3).
southeast(P1, P2) :- 
    south(P3, P2),
    east(P4, P3),
    southeast(P1, P4).
