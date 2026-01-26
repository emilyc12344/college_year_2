basket = ["apple", "banana", "apple", "orange", "banana", "apple"]
counts = {}

# TODO: Loop through 'basket'.
# If fruit is NOT in 'counts', add it with value 1.
# If fruit IS in 'counts', increment its value.

for fruit in basket:
    if fruit in counts:
        counts[fruit] += 1
    else:
        counts[fruit] = 1

print(counts) 
# Expected: {'apple': 3, 'banana': 2, 'orange': 1}

