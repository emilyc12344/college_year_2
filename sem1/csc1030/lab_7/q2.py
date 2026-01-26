#!/usr/bin/env python3

class TreeNode:
    def __init__(self, val):
        self.val = val
        self._children = []
        self.left = None # can be this and not a list cause this is only for binary trees and they can only have 2 children (left and right)
        self.right = None

class Tree:
    def __init__(self, is_binary=True):
        self.is_binary = is_binary
        self.root = None
        self.size = 0

    def set_root(self, val):
        if self.root is None:
            self.root = TreeNode(val)
            self.size += 1
        return self.root

    def is_leaf(self, node): # has no children
        if self.is_binary:
            return node.left is None and node.right is None
        else:
            return len(node._children) == 0

    def children(self, node):
        if self.is_binary:
            children = []
            if node.left is not None:
                children.append(node.left)
            if node.right is not None:
                children.append(node.right)
            return children
        else:
            return node._children

    def add_child(self, parent, val):
        if self.is_binary:
            raise ValueError("use insert_left/insert_right for binary trees")
        child_node = TreeNode(val)
        parent._children.append(child_node)
        self.size += 1
        return child_node

    def insert_left(self, parent, val):
        if not self.is_binary:
            raise ValueError("use add_child for general trees")
        if parent.left is not None:
            raise ValueError("left already set")
        parent.left = TreeNode(val)
        self.size += 1
        return parent.left

    def insert_right(self, parent, val):
        if not self.is_binary:
            raise ValueError("use add_child for general trees")
        if parent.right is not None:
            raise ValueError("right already set")
        parent.right = TreeNode(val)
        self.size += 1
        return parent.right

    def preorder(self): # root -> left -> right / root -> children
        out = []
        def _pre(n):
            if not n:
                return
            out.append(n.val)
            if self.is_binary:
                _pre(n.left)
                _pre(n.right)
            else:
                for child in n._children:
                    _pre(child)
        _pre(self.root)
        return out

    def postorder(self): # left -> right -> root / children -> root
        out = []
        def _post(n):
            if not n:
                return
            if self.is_binary:
                _post(n.left)
                _post(n.right)
            else:
                for child in n._children:
                    _post(child)
            out.append(n.val)
        _post(self.root)
        return out

    def inorder(self): # left -> root -> right (only for binary)
        out = []
        def _in(n):
            if not n:
                return
            if self.is_binary:
                _in(n.left)
                out.append(n.val)
                _in(n.right)
            else:
                if len(n._children) == 0:
                    out.append(n.val)
                else:
                    _in(n._children[0])
                    out.append(n.val)
                    for c in n._children[1:]:
                        _in(c)
        _in(self.root)
        return out
