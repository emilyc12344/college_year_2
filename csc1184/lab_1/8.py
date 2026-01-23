series = [10, 12, 13, 15, 20, 18, 16, 14]

__TODO__ = None

window = 3
averages = []

# For each i where a full window fits, i runs 0..len(series)-window
for i in range(0, (len(series) - 2)):           # TODO: stop value so i+2 is in range
    a = series[i]
    b = series[i + 1]
    c = series[i + 2]
    avg = (a + b + c) / 3                      # TODO: average of a, b, c
    averages.append(avg)

print("Series:", series)
print("Moving average (w=3):", averages)

