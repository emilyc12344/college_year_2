#!/usr/bin/env python3

def reverse(l):
    ln = len(l)
    if ln == 1:
        return l
    ln -= 1
    return reverse(l[1:]) + [l[0]]

print(reverse([1, 2, 3, 4]))
