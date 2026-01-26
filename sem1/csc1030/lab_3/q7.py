#!/usr/bin/env python3

def log2(n):
    count = 0
    while n > 1:
        n //= 2
        count += 1
    return count
