#!/usr/bin/env python3

def has_duplicate_pairs(l):
    for curr in l:
        ind = l.index(curr)
        new_l = l[ind+1:]
        for check in new_l:
            if curr == check:
                return True
    return False

print(has_duplicate_pairs([1, 2, 3, 2]))
