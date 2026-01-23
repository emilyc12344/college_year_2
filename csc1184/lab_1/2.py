scores = [59, 63, 71, 88, 42, 95, 77, 68]

__TODO__ = None

threshold = 60
count_scores = len(scores)

# Count how many scores >= threshold using a loop
num_passing = 0
for s in scores:
    if s >= threshold:                     # TODO: condition for passing
        num_passing += 1       # TODO: increment the counter (no += if you prefer)

# Compute pass rate in percent, rounded to 1 decimal
pass_rate = (num_passing / count_scores) * 100                # TODO: (num_passing / count_scores) * 100
pass_rate = round(pass_rate, 1)                 # TODO: round(..., 1)

print("Threshold:", threshold)
print("Passing:", num_passing, "out of", count_scores)
print("Pass rate (%):", pass_rate)

# Try afterwards:
# 1) Change threshold to 50, then 70. What happens?
# 2) Append two more scores and re-run.

