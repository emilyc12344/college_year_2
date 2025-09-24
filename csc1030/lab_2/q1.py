#!/usr/bin/env python3

class Horse(object):
    def __init__(self, rank=0, value=0):
        self.rank = rank
        self.value = value

    def display(self):
        return f'Rank: {self.rank} Value: £{self.value}'

horses = {}

i = 100
v = 1000
while i >= 0:
    horses[i] = Horse(i, v)
    v = v + (v // 10)
    i -= 1

while True:
    find = int(input('Please enter rank of horse you want to find: '))
    if find >= 1 and find <= len(horses.keys()):
        print(horses[find].display())
    elif find < 0 and len(horses.keys()) + find > 0:
        print(horses[len(horses.keys()) + find].display())
    else:
        print(f'A horse with rank {find} was not found!')
