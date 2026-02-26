fib(1, 1).
fib(0, 0).

fib(X, Y) :- % Y is fibonacci of X
    X > 1,
    X1 is X - 1,
    X2 is X - 2,
    fib(X1, Y1),
    fib(X2, Y2),
    Y is Y1 + Y2.