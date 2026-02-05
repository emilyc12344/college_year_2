import numpy as np

scores = np.array([55, 62, 71, 48, 90, 77, 68, 82])
print("Scores:", scores)

# TODO 1: Print the first score (position 0)
# TODO 2: Print the last score (use negative indexing)
# TODO 3: Print the first 3 scores (slice)
# TODO 4: Print scores from position 2 up to (but excluding) position 6
# TODO 5: Print every second score (use a step in the slice)

first = scores[0]
last = scores[-1]
first_three = scores[0:3]
middle_slice = scores[2:6]
every_second = scores[1::2]

print("First:", first)
print("Last:", last)
print("First three:", first_three)
print("Positions 2..5:", middle_slice)
print("Every second value:", every_second)
