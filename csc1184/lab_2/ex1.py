names = ["Alice", "Bob", "Charlie", "David"]
scores = [85, 42, 90, 30]

# TODO 1: Use zip() and dict() to create a dictionary 
# named 'gradebook' mapping names to scores.

gradebook = dict(zip(names, scores))

# TODO 2: Loop through the dictionary items.
# Print "{Name} passed" if score >= 40
# Print "{Name} failed" if score < 40

print("--- Results ---")

for curr_name in gradebook:
    curr_score = gradebook[curr_name]
    if curr_score >= 40:
        print(f'{curr_name} passed')
    else:
        print(f'{curr_name} failed')
