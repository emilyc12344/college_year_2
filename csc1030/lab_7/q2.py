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

# Create a general tree (non-binary)
org_tree = Tree(is_binary=False)

# Set the CEO as root
ceo = org_tree.set_root("CEO")

# Add department heads as children of CEO
cto = org_tree.add_child(ceo, "CTO")
cfo = org_tree.add_child(ceo, "CFO")
cmoo = org_tree.add_child(ceo, "CMO")

# Add teams under CTO
dev_lead = org_tree.add_child(cto, "Development Lead")
qa_lead = org_tree.add_child(cto, "QA Lead")

print("Preorder:", org_tree.preorder())
print("Postorder:", org_tree.postorder())
print("inorder:", org_tree.inorder())
# Output: Preorder: ['CEO', 'CTO', 'Development Lead', 'QA Lead', 'CFO', 'CMO']

# ----------------- test ----------------------

import unittest

class TestTree(unittest.TestCase):
    def test_binary_tree_structure_and_leaf(self):
        t = Tree(is_binary=True)
        r = t.set_root(10)
        self.assertTrue(t.is_leaf(r))
        l = t.insert_left(r, 5)
        self.assertFalse(t.is_leaf(r))
        self.assertTrue(t.is_leaf(l))
        r2 = t.insert_right(r, 15)
        self.assertEqual([n.val for n in t.children(r)], [5, 15])
        self.assertEqual(t.size, 3)

    def test_binary_traversals(self):
        t = Tree(is_binary=True)
        r = t.set_root(10)
        a = t.insert_left(r, 5)
        b = t.insert_right(r, 15)
        t.insert_left(a, 2)
        t.insert_right(a, 7)
        t.insert_left(b, 12)
        t.insert_right(b, 20)
        self.assertEqual(t.preorder(), [10, 5, 2, 7, 15, 12, 20])
        self.assertEqual(t.inorder(), [2, 5, 7, 10, 12, 15, 20])
        self.assertEqual(t.postorder(), [2, 7, 5, 12, 20, 15, 10])

    def test_binary_enforcement(self):
        t = Tree(is_binary=True)
        r = t.set_root(1)
        with self.assertRaises(ValueError):
            t.add_child(r, 2)
        t.insert_left(r, 2)
        with self.assertRaises(ValueError):
            t.insert_left(r, 3)

    def test_general_tree_structure_and_leaf(self):
        t = Tree(is_binary=False)
        r = t.set_root("A")
        self.assertTrue(t.is_leaf(r))
        b = t.add_child(r, "B")
        c = t.add_child(r, "C")
        d = t.add_child(r, "D")
        self.assertFalse(t.is_leaf(r))
        self.assertTrue(t.is_leaf(b))
        self.assertEqual([n.val for n in t.children(r)], ["B", "C", "D"])
        self.assertEqual(t.size, 4)

    def test_general_traversals(self):
        t = Tree(is_binary=False)
        r = t.set_root("A")
        b = t.add_child(r, "B")
        c = t.add_child(r, "C")
        d = t.add_child(r, "D")
        t.add_child(b, "E")
        t.add_child(b, "F")
        t.add_child(d, "G")
        self.assertEqual(t.preorder(), ["A", "B", "E", "F", "C", "D", "G"])
        self.assertEqual(t.postorder(), ["E", "F", "B", "C", "G", "D", "A"])
        self.assertEqual(t.inorder(), ["E", "B", "F", "A", "C", "G", "D"])

    def test_general_enforcement(self):
        t = Tree(is_binary=False)
        r = t.set_root(1)
        with self.assertRaises(ValueError):
            t.insert_left(r, 2)
        with self.assertRaises(ValueError):
            t.insert_right(r, 3)

if __name__ == "__main__":
    unittest.main()
