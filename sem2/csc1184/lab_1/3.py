raw_scores = [59, 63, 71, -5, 104, 88, 42, 120, 95, 77, 68]

__TODO__ = None

# Build a cleaned list: keep only values between 0 and 100 inclusive
cleaned = []
invalid_count = 0

for s in raw_scores:
    if s < 0 or s > 100:                     # TODO: True when s is invalid (<0 or >100)
        invalid_count = invalid_count + 1
        # skip invalid
    else:
        cleaned.append(s)                     # TODO: append valid s to 'cleaned'

print("Raw:", raw_scores)
print("Cleaned:", cleaned)
print("Invalid values removed:", invalid_count)

# Summaries on cleaned data
n = len(cleaned)
total = sum(cleaned) 
avg = total / n
print("Count:", n, "Total:", total, "Average:", avg, "Min:", min(cleaned), "Max:", max(cleaned))

