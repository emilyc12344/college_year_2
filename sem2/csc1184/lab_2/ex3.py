header = ["id", "name", "exam_score", "labs_attended"]
row1 = ["101", "Amal", "72", "7"]
row2 = ["102", "Grace", "64", "5"]

# TODO: Use zip() + dict() to convert each row into a dictionary.
# Cast exam_score and labs_attended to int inside the dict construction.

students = []

for row in [row1, row2]:
    # Zip header and row, convert values for specific keys
    row_dict = {}
    for key, value in zip(header, row):
        if key in ["exam_score", "labs_attended"]:
            row_dict[key] = int(value)
        else:
            row_dict[key] = value
    students.append(row_dict)

print(students)
