#!/usr/bin/env python3

def length(s):
    if len(s) <= 0:
        return 0
    return length(s[:-1]) + 1

print(length('hello'))
