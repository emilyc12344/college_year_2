scores = [12, 38, 41, 59, 60, 73, 88, 95, 39, 58]

__TODO__ = None

band_0_39 = 0
band_40_59 = 0
band_60_100 = 0

for s in scores:
    if s >= 0 and s <= 39:                      # TODO: 0..39 inclusive
        band_0_39 = band_0_39 + 1
    elif s >= 0 and s <= 59:                    # TODO: 40..59 inclusive
        band_40_59 = band_40_59 + 1
    elif s >= 0 and s <= 100:                    # TODO: 60..100 inclusive
        band_60_100 = band_60_100 + 1
    else:
        # out of expected range; ignore for now
        pass

print("[0–39]:", band_0_39)
print("[40–59]:", band_40_59)
print("[60–100]:", band_60_100)

