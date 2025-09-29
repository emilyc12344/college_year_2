#!/usr/bin/env python3

def reverse(s):
    l = len(s)
    if l == 1:
        return s
    l -= 1
    return reverse(s[1:]) + s[0] 

print(reverse('hello'))
