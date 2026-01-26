#!/usr/bin/env python3

class Queue(object):
    def __init__(self):
        self.list = []

    def enqueue(self, val):
        self.list.append(val)

    def dequeue(self):
        return self.list.pop(0)

    def is_empty(self):
        return len(self.list) == 0

    def __str__(self):
        return f'{self.list}'

    def minimum(self, curr=None, count=0):
        if curr == None:
            curr = self.list[0]
        if count == len(self.list):
            return curr
        if self.list[count] < curr:
            curr = self.list[count]
            return self.minimum(curr, count+1)
        else:
            return self.minimum(curr, count+1)

    def reverse(self):
        if self.is_empty():
            return
        new = self.dequeue()
        self.reverse()
        self.enqueue(new)

import random

test = Queue()
for i in range(1, 10):
    test.enqueue(random.randint(1, 50))

print(test)
print(test.minimum())
test.reverse()
print(test)

def dec_to_bin(n):
    curr = Queue()
    while n > 0:
        curr.enqueue(str(n % 2))
        n //= 2
    return ''.join(curr.list[::-1])

def find_bin_numbers(n):
    bin_numbers = Queue()
    for i in range(1, n+1):
        bin_numbers.enqueue(dec_to_bin(i))
    print(bin_numbers)

#find_bin_numbers(16)

class Stack(object):
    def __init__(self):
        self.list = []

    def is_empty(self):
        return self.list == []

    def push(self, item):
        self.list.append(item)

    def pop(self):
        return self.list.pop()

    def top(self):
        return self.items[len(self.list) - 1]

    def size(self):
        return len(self.list)

test = 'EAS*Y*QUE***ST***IO*N***'
test_stack = Stack()
ans = []
for char in test:
    if char == '*':
        ans.append(test_stack.pop())
    else:
        test_stack.push(char)
print(ans)

test_queue = Queue()
ans = []
for char in test:
    if char == '*':
        ans.append(test_queue.dequeue())
    else:
        test_queue.enqueue(char)
print(ans)

def reverse(s):
    ans = ''
    stack = Stack()
    for char in s:
        stack.push(char)
    for i in range(0, len(stack.list)):
        ans = ans + stack.pop()
    print(ans)
reverse('This is the right way'[::-1])

class Deque(object):
    def __init__(self):
        self.list = []
    def add_first(self, val):
        self.list.insert(0, val)
    def add_last(self, val):
        self.list.append(val)
    def pop_first(self):
        return self.list.pop(0)
    def pop_last(self):
        return self.list.pop()

test_1 = 'apple'
test_2 = 'navan'

def is_pal(s):
    s = s.lower()
    pal = Deque()
    for char in s:
        pal.add_last(char)
    while len(pal.list) > 1:
        if pal.pop_first() != pal.pop_last():
            return False
    return True

print(is_pal(test_1))
print(is_pal(test_2))