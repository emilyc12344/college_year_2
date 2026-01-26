def get_stats(numbers):
    # TODO: Calculate min and max of the list
    min_data = min(data)
    max_data = max(data)
    # TODO: Return both as a tuple: return minimum, maximum
    return min_data, max_data

data = [10, 5, 20, 2, 15]
low, high = get_stats(data)  # Unpacking

print(f"Lowest: {low}, Highest: {high}")

