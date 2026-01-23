def load_data(filename):
    data = []
    with open(filename, 'r') as f:
        header = f.readline().strip().split(',')
        for row in f.readlines():
            curr_data = row.strip().split(',')
            correct_data = []
            for value in curr_data:
                if value.isdigit():
                    correct_data.append(int(value))
                elif value == 'N/A':
                    pass
                else:
                    correct_data.append(value)
                if len(correct_data) == len(header):
                    data.append(dict(zip(header, correct_data)))

    return data

sales = load_data("sales_data.csv")

def calculate_total_sales(sales_data):
    total = 0
    for curr in sales_data:
        curr_price = curr['price']
        curr_quant = curr['quantity']
        total += (curr_price * curr_quant)
    return total

print("Total Revenue:", calculate_total_sales(sales))
