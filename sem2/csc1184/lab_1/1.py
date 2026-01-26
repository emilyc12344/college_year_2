# A list of exam scores (0–100)
scores = [59, 63, 71, 88, 42, 95, 77, 68]

# Replace every __TODO__ with working code before running.
__TODO__ = None

print("Scores:", scores)

# --- Built-in summaries ---
count_scores = len(scores)
total_scores = sum(scores)          # TODO: use a built-in to get the total
average_score = total_scores / count_scores         # TODO: compute total / count
scores.sort()
lowest = scores[0]                # TODO: smallest value
highest = scores[1]               # TODO: largest value

print("Count:", count_scores)
print("Total:", total_scores)
print("Average:", average_score)
print("Min:", lowest, "Max:", highest)

# --- Manual total using a loop (confirm understanding of sum) ---
manual_total = 0
for s in scores:
    manual_total = manual_total + s      # TODO: add s into manual_total

print("Manual total (loop):", manual_total)
print("Totals match:", manual_total == total_scores)
