from math import floor


M = 86400000


class Account:
    def __init__(self, ID):
        self.balance = 0
        self.ID = ID
        self.history = None
        self.rank = 0

    def deposit(self, amount):
        self.balance += amount
        return self.balance

    def withdraw(self, amount):
        if self.balance < amount:
            return None
        self.balance -= amount
        self.rank += amount
        return self.balance


class BankingSystemImpl(BankingSystem):
    def __init__(self):
        self.accounts = {}
        self.account_list = []
        self.pending_transactions = []
        self.next_payment_id = 1
        self.payment_status = {}

    def create_account(self, timestamp, account_id):
        if account_id in self.accounts:
            return False

        self.accounts[account_id] = Account(account_id)
        self.account_list.append(self.accounts[account_id])
        self.payment_status[account_id] = {}
        return True

    def deposit(self, timestamp, account_id, amount):
        self.resolve(timestamp)
        if account_id not in self.accounts:
            return None
        return self.accounts[account_id].deposit(amount)

    def transfer(self, timestamp, source_account_id, target_account_id, amount):
        self.resolve(timestamp)
        if (source_account_id not in self.accounts or
                target_account_id not in self.accounts or
                source_account_id == target_account_id or
                self.accounts[source_account_id].balance < amount):
            return None

        self.accounts[source_account_id].withdraw(amount)
        self.accounts[target_account_id].deposit(amount)
        return self.accounts[source_account_id].balance

    def top_spenders(self, timestamp, n):
        self.resolve(timestamp)
        sorted_accounts = sorted(
            self.account_list, key=lambda a: (-a.rank, a.ID))
        top_accounts = []
        for i in range(min(n, len(self.account_list))):
            top_accounts.append(
                f"{sorted_accounts[i].ID}({sorted_accounts[i].rank})")
        return top_accounts

    def pay(self, timestamp, account_id, amount):
        self.resolve(timestamp)
        if account_id not in self.accounts or self.accounts[account_id].balance < amount:
            return None

        self.accounts[account_id].withdraw(amount)
        payment_due_time = timestamp + M
        cashback_amount = floor(amount * 0.02)
        payment_id = f"payment{self.next_payment_id}"
        self.pending_transactions.append(
            (payment_due_time, account_id, cashback_amount, payment_id))
        self.payment_status[account_id][payment_id] = payment_due_time
        self.next_payment_id += 1
        return payment_id

    def get_payment_status(self, timestamp, account_id, payment):
        self.resolve(timestamp)
        if account_id not in self.accounts or payment not in self.payment_status[account_id]:
            return None

        if self.payment_status[account_id][payment] > timestamp:
            return "IN_PROGRESS"
        else:
            return "CASHBACK_RECEIVED"

    def resolve(self, timestamp):
        while len(self.pending_transactions) > 0 and timestamp >= self.pending_transactions[0][0]:
            self.accounts[self.pending_transactions[0][1]].deposit(
                self.pending_transactions[0][2])
            self.pending_transactions.pop(0)

    def merge_accounts(self, timestamp, account_id_1, account_id_2):
        # To be implemented
        pass
