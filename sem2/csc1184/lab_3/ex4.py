import numpy as np

scores = np.array([55, 62, 71, 48, 90, 77, 68, 82])
threshold = 60

print("Scores:", scores)
print("Threshold:", threshold)

# TODO 1: Build a boolean mask for scores >= threshold
mask = scores >= threshold

# TODO 2: Use the mask to create a new array 'passing'
passing = scores[mask]

# TODO 3: Print the mask and the passing scores
print("Mask (scores >= threshold):", mask)
print("Passing scores:", passing)

# TODO 4: Compute and print how many passed and the pass rate (%)
# Hint: len(passing), len(scores)
num_passing = len(passing)
num_total = len(scores)
pass_rate = (num_passing / num_total) * 100

print("Passing:", num_passing, "out of", num_total)
print("Pass rate (%):", round(pass_rate, 1))
