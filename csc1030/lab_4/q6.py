#!/usr/bin/env python3

def is_heteromecic(end, curr=0):
    tot = curr * (curr + 1)
    if tot == end:
        return True
    elif tot > end:
        return False
    else: 
        return is_heteromecic(end, curr + 1)

print(is_heteromecic(110))
