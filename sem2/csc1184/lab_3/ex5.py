import numpy as np

scores = np.array([12, 38, 41, 59, 60, 73, 88, 95, 39, 58])
print("Scores:", scores)

# Define bands: 0–39, 40–59, 60–100 (inclusive)
# TODO: Build masks for each band using comparisons (& for "and")
band_0_39_mask = (scores >= 0) & (scores <= 39)
band_40_59_mask = (scores >= 40) & (scores <= 59)
band_60_100_mask = (scores >= 60) & (scores <= 100)

band_0_39 = scores[band_0_39_mask]
band_40_59 = scores[band_40_59_mask]
band_60_100 = scores[band_60_100_mask]

print("[0–39]:", band_0_39, "Count:", len(band_0_39))
print("[40–59]:", band_40_59, "Count:", len(band_40_59))
print("[60–100]:", band_60_100, "Count:", len(band_60_100))

# Optional: check that counts add up
total_count = len(scores)
sum_counts = len(band_0_39) + len(band_40_59) + len(band_60_100)
print("Total:", total_count, "Sum of band counts:", sum_counts)
