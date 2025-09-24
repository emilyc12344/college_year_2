#!/usr/bin/env python3

def list_square(nums):
    new = []

    for i in nums:
        new.append(int(i) ** 2)
    print(new)

list_square([1, 2, 3, 4])