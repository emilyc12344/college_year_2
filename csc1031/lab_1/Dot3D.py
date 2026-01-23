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

