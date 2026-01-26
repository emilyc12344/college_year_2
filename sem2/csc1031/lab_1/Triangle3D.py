#!/usr/bin/env python3
from math import sqrt

class Dot3D(object):
    def __init__(self, x, y, z, label=''):
        self.x = int(x)
        self.y = int(y)
        self.z = int(z)
        self.label = label

    def distance_to(self, other):
        xs = (other.x - self.x) ** 2
        ys = (other.y - self.y) ** 2
        zs = (other.z - self.z) ** 2
        return sqrt(xs +  ys + zs)

    def add_vector(self, other):
        new_x = self.x + other.x
        new_y = self.y + other.y
        new_z = self.z + other.z
        new_label = f'{self.label}+{other.label}'
        return Dot3D(new_x, new_y, new_z, new_label)

class Triangle3D(object):
    def __init__(self, d1, d2, d3):
        self.dot1 = d1
        self.dot2 = d2
        self.dot3 = d3
        self.edge1 = self.dot1.distance_to(self.dot2)
        self.edge2 = self.dot2.distance_to(self.dot3)
        self.edge3 = self.dot3.distance_to(self.dot1)

    def calculate_perimeter(self):
        return self.edge1 + self.edge2 + self.edge3

    def calculate_area(self):
        s = self.calculate_perimeter() / 2
        area = sqrt(s * (s - self.edge1) * (s - self.edge2) * (s - self.edge3))
        return area
