#!/usr/bin/env python3

def func(l):
    new_l = []
    for curr in l:
        new = ''
        i = 1
        while i <= len(curr):
            new = new + curr[len(curr) - i].upper()
            i += 1
        new_l.append(new)
    return new_l

print(func(['hi', 'bye']))