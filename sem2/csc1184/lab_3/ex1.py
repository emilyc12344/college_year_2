import numpy as np

# A tiny dataset of exam scores and study hours
scores_list = [59, 63, 71, 88, 42, 95, 77, 68]
hours_list  = [5,  6,  4,  9,  3,  10, 7,  6]

print("Scores list:", scores_list)
print("Hours list:", hours_list)

# TODO 1: Convert both lists to NumPy arrays named 'scores' and 'hours'
scores = np.array(scores_list)
hours = np.array(hours_list)

# TODO 2: Print each array, its length (len), shape, and dtype
# Hint: use len(scores), scores.shape, scores.dtype

print("\nScores array:", scores)
print("Length:", len(scores), "Shape:", scores.shape, "dtype:", scores.dtype)

print("\nHours array:", hours)
print("Length:", len(hours), "Shape:", hours.shape, "dtype:", hours.dtype)