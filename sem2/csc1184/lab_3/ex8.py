import numpy as np

students = [
    {"id": "1", "name": "Ana",   "programme": "CS", "exam_score": "55"},
    {"id": "2", "name": "Bob",   "programme": "CS", "exam_score": "62"},
    {"id": "3", "name": "Ciara", "programme": "DS", "exam_score": "71"},
    {"id": "4", "name": "Dan",   "programme": "DS", "exam_score": "48"},
    {"id": "5", "name": "Eli",   "programme": "CS", "exam_score": "90"},
]

# Step 1: Build arrays for programmes and scores
programmes = []
scores_list = []

for row in students:
    programmes.append(row["programme"])
    scores_list.append(int(row["exam_score"]))

programmes = np.array(programmes)   # array of strings
scores = np.array(scores_list)      # array of ints

print("Programmes:", programmes)
print("Scores:", scores)

# TODO 1: Build masks for CS and DS rows
mask_cs = programmes == 'CS'
mask_ds = programmes == 'DS'

# TODO 2: Use masks to create arrays cs_scores and ds_scores
cs_scores = scores[mask_cs]
ds_scores = scores[mask_ds]

print("\nCS scores:", cs_scores)
print("DS scores:", ds_scores)

# TODO 3: Compute and print the mean score for each group
cs_mean = cs_scores.mean()
ds_mean = ds_scores.mean()

print("\nMean CS score:", cs_mean)
print("Mean DS score:", ds_mean)
