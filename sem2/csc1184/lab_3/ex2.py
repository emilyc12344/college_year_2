import numpy as np

# TODO 1: Create an array called 'zeros_10' with 10 zeros using np.zeros
zeros_10 = np.zeros(10)

# TODO 2: Create an array called 'ones_5' with 5 ones using np.ones
ones_5 = np.ones(5)

# TODO 3: Create an array called 'evens' with even numbers from 0 up to (but not including) 20
# Hint: use np.arange(start, stop, step)
evens = np.arange(0, 19, 2)

print("zeros_10:", zeros_10)
print("ones_5:", ones_5)
print("evens:", evens)

# Optional: check their shapes and dtypes
print("zeros_10 shape:", zeros_10.shape, "dtype:", zeros_10.dtype)
print("evens shape:", evens.shape, "dtype:", evens.dtype)