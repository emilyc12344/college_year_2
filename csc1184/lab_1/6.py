values = [7, 3, 9, 2, 5, 11, 4]

__TODO__ = None

# Initialise candidates using the first element
current_min = values[0]               # TODO: set to first element
current_max = values[0]               # TODO: set to first element

# Loop from the second element onward
for i in range(1, len(values)):
    v = values[i]
    if v < current_min:                     # TODO: update min if v is smaller
        current_min = v
    if v > current_max:                     # TODO: update max if v is larger
        current_max = v

print("Min:", current_min, "Max:", current_max)
# Optional check afterwards:
# print(min(values), max(values))

