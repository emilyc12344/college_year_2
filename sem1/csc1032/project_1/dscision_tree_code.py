import pandas as pd
from sklearn.preprocessing import LabelEncoder
from sklearn.model_selection import train_test_split
from sklearn.tree import DecisionTreeClassifier #, plot_tree
from sklearn.metrics import accuracy_score, classification_report, confusion_matrix
import seaborn as sns
import matplotlib.pyplot as plt
#import numpy as np

df = pd.read_excel('csc1032/car_database.xlsx')
df_processed = df.copy()

label_encoders = {} # dict of all encoders

col_names = list(df.columns)[:-2] # dont need to encode price column

for col in col_names:
    le = LabelEncoder()
    df_processed[col] = le.fit_transform(df_processed[col].astype(str)) # encodes each column to have a numericsal valuye instead of a string
    label_encoders[col] = le

target_encoder = LabelEncoder()
df_processed['encoded_price_class'] = target_encoder.fit_transform(df_processed['price class']) # makes cheap-0, high-1, mid-2

X = df_processed.drop(['price class', 'encoded_price_class', 'price'], axis=1) # all columns bar the one we need to predict
y = df_processed['encoded_price_class'] # col we need to predict

X_train, X_test, y_train, y_test = train_test_split(
    X, y,
    test_size=0.2,       # 20% for testing
    random_state=42,     # Makes the split reproducible
)

dt_classifier = DecisionTreeClassifier(
    random_state=42, # makes sure each time its ran results are similar
    min_samples_leaf=5, # min amount of matches to make a final leaf
    min_samples_split=3 # min amount of match to make a split
)

dt_classifier.fit(X_train, y_train) # trains the model
y_pred = dt_classifier.predict(X_test) # predicts the column

# accuracy
accuracy = accuracy_score(y_test, y_pred)
print(f'Accuracy: {accuracy}')

# class report
print(classification_report(y_test, y_pred, target_names=target_encoder.classes_)) # classification reprt with the unencoded names

# confusion matrix
cm = confusion_matrix(y_test, y_pred)
sns.heatmap(cm, annot=True, fmt='.1f', cmap='Greens', xticklabels=target_encoder.classes_, yticklabels=target_encoder.classes_)

plt.xlabel('Predicted')
plt.ylabel('Actual')
plt.title('Confusion Matrix (Percentages)')
plt.show()

feature_importances = pd.DataFrame({
    'feature': X.columns,
    'importance': dt_classifier.feature_importances_
}).sort_values('importance', ascending=False)

print(feature_importances)