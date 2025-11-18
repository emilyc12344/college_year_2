#!/usr/bin/env python3

class Node:
    def __init__(self, val):
        self.val = val
        self.next = None

class SinglyLinkedList:

    def __init__(self):
        self.head = None
        self.tail = None
        self.size = 0

    def insert_start(self, val):
        node = Node(val)
        if self.head is None:
            self.tail = node
        else:
            node.next = self.head
        self.head = node
        self.size += 1
        return node

    def insert_end(self, val):
        node = Node(val)
        if self.head is None:
            self.head = node
        else:
            self.tail.next = node
        self.tail = node
        self.size += 1
        return node

    def insert_sorted(self, val):
        node = Node(val)
        if self.head is None or val < self.head.val:
            node.next = self.head
            self.head = node
            if self.tail is None:
                self.tail = node
        else:
            curr = self.head
            while curr.next and curr.next.val < val:
                curr = curr.next
            node.next = curr.next
            curr.next = node
            if node.next is None:
                self.tail = node
        self.size += 1
        return node

    def remove_start(self):
        if self.head is None:
            return None
        val = self.head.val
        self.head = self.head.next
        if self.head is None:
            self.tail = None
        self.size -= 1
        return val

    def remove_end(self):
        if self.head is None:
            return None
        if self.head == self.tail:
            val = self.head.val
            self.head = None
            self.tail = None
            self.size -= 1
            return val
        curr = self.head
        while curr.next != self.tail:
            curr = curr.next
        val = self.tail.val
        curr.next = None
        self.tail = curr
        self.size -= 1
        return val

    def find(self, val):
        curr = self.head
        while curr:
            if curr.val == val:
                return curr
            curr = curr.next
        return None

    def is_empty(self):
        return self.size == 0

    def __len__(self):
        return self.size

    def display(self):
        curr = self.head
        vals = []
        while curr:
            vals.append(str(curr.val))
            curr = curr.next
        return ' -> '.join(vals)
