#!/usr/bin/env python3

import pandas as pd
import matplotlib
import seaborn as sns

df = pd.read_csv('https://www.ssa.gov/oact/babynames/background.html')
print(df.head())