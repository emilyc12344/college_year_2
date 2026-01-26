#!/usr/bin/env python3

def reverse(n):
    l = len(str(n))
    if l == 1:
        return n
    l -= 1
    return str(reverse(int(str(n)[1:]))) + str(n)[0] 

print(reverse(123))
