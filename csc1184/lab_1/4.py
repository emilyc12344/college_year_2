scores = [59, 63, 71, 88, 42, 95, 77, 68]

__TODO__ = None

with_bonus = []

for s in scores:
    boosted = s + 5               # TODO: add 5 points to s
    if boosted > 100:
        boosted = 100
    with_bonus.append(boosted)                          # TODO: append boosted to with_bonus

print("Original:", scores)
print("With bonus:", with_bonus)

# Compare summaries before vs after (no user-defined functions)
orig_total = sum(scores)
orig_avg   = orig_total / len(scores)
new_total  = sum(with_bonus)
new_avg    = new_total / len(with_bonus)

print("Original -> Count:", len(scores), "Total:", orig_total, "Average:", orig_avg, "Min:", min(scores), "Max:", max(scores))
print("With bonus -> Count:", len(with_bonus), "Total:", new_total, "Average:", new_avg, "Min:", min(with_bonus), "Max:", max(with_bonus))

