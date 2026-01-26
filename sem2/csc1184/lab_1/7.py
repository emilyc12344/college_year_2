scores = [42, 55, 63, 71, 88, 95]

__TODO__ = None

mn = min(scores)                        # TODO: min of scores
mx = max(scores)                         # TODO: max of scores

scaled = []

# Guard against division by zero if all values are equal
if mx == mn:
    # Policy choice: if all equal, map all to 100 (or 0). Pick one and be consistent.
    for s in scores:
        scaled.append(100)       # TODO: choose 0 or 100
else:
    for s in scores:
        # scale to 0..100 using (s - mn) / (mx - mn) * 100
        v = ((s - mn) / (mx - mn)) * 100                  # TODO: compute scaled value
        scaled.append(v)

print("Original:", scores)
print("Scaled 0..100:", scaled)

