import numpy as np

# Imagine this list came from your CSV loader in Week 2
students = [
    {"id": "1", "name": "Ana",   "programme": "CS", "exam_score": "55"},
    {"id": "2", "name": "Bob",   "programme": "CS", "exam_score": "62"},
    {"id": "3", "name": "Ciara", "programme": "DS", "exam_score": "71"},
    {"id": "4", "name": "Dan",   "programme": "DS", "exam_score": "48"},
    {"id": "5", "name": "Eli",   "programme": "CS", "exam_score": "90"},
]

# TODO 1: Build a plain Python list of integers called exam_scores_list
# Hint: loop over students, read row["exam_score"], convert to int, append.

exam_scores_list = []

# TODO: your loop here

for i in students:
    exam_scores_list.append(int(i["exam_score"]))

print("Exam scores list:", exam_scores_list)

# TODO 2: Convert exam_scores_list into a NumPy array called exam_scores
exam_scores = np.array(exam_scores_list)

print("Exam scores array:", exam_scores)
print("Mean:", exam_scores.mean())
print("Scores >= 60:", exam_scores[exam_scores >= 60])