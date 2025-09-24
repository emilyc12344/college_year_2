#!/usr/bin/env python3

class BankAccount(object):
    def __init__(self, name, phone, balance, a_id, a_type='Bank Account'):
        self._name = name
        self._account_balance = balance
        self._account_id = a_id
        self._account_type = a_type
        self._phone_number = phone

    def deposit(self, amount):
        self._account_balance = self._account_balance + amount

    def withdraw(self, amount):
        if self._account_balance - amount >= 0:
            self._account_balance = self._account_balance - amount

    def get_balance(self):
        return f'Your current balance for your {self._account_type} is {self._account_balance}'

    def get_account_type(self):
        return self._account_type

class SavingsAccount(BankAccount):
    def __init__(self, name, phone, balance, a_id):
        super().__init__(name, phone, balance, a_id, a_type='Savings')
    def apply_interest(self, rate):
        self.__interest_rate = rate
    def add_interest(self):
        self._account_balance = self._account_balance + (self._account_balance * self.__interest_rate)

class CurrentAccount(BankAccount):
    def __init__(self, name, phone, balance, a_id):
        super().__init__(name, phone, balance, a_id, a_type='Current')
    def apply_interest(self, rate):
        self.__interest_rate = rate
    def deposit(self, amount):
        return super().deposit(amount + self.add_interest())
    def add_interest(self):
        return (self._account_balance * self.__interest_rate)

account = BankAccount("Joe", "phone", 0, 123)
savings_account = SavingsAccount("Peter", "phone", 0, 123)
current_account = CurrentAccount("Jane", "phone", 0, 123)

print(account.get_account_type())
print(savings_account.get_account_type())

savings_account.apply_interest(.02)
current_account.apply_interest(.01)

savings_account.deposit(100)
current_account.deposit(500)

print(savings_account.get_balance())
print(current_account.get_balance())
