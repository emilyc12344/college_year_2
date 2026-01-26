#!/usr/bin/env python3

def sum_q1(n):
    if n == 0:
        return 0
    return sum_q1(n-1) + n

print(sum_q1(6))
