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

% RECOMMENDATIONS
holidays(book(T, A, G, P), Lib) :-
    member(book(T, A, G, P), Lib),
    P < 400,
    G \= study,
    G \= reference.

revision(book(T, A, G, P), Lib) :- 
    member(book(T, A, G, P), Lib),
    (G = study ; G = reference),
    P > 300.

literary(book(T, A, G, P), Lib) :- 
    member(book(T, A, G, P), Lib), 
    G = drama.

leisure(book(T, A, G, P), Lib) :- 
    member(book(T, A, G, P), Lib),
    (G = comedy ; G = fiction).