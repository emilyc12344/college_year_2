#!/usr/bin/env python3

def multiply(n, times):
    if n < 0:
        n = n * -1
    if times < 0:
        times = times * -1
    if times == 1:
        return n
    if times == 0:
        return 0
    return multiply(n, times-1) + n

print(multiply(-51, 0))
