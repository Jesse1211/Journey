from InMemoryDB import InMemoryDB


class InMemoryDBImpl(InMemoryDB):

    def __init__(self):
        self.database = {}
        # {
        #     key:
        #         {
        #             field: (value, expiration_timestamp)
        #         }
        # }

    def set(self, timestamp: int, key: str, field: str, value: int) -> None:
        if key not in self.database:
            self.database[key] = {}

        self.database[key][field] = (value, float('inf'))
        return None

    def compare_and_set(self, timestamp: int, key: str, field: str, expected_value: int, new_value: int) -> bool:
        if key not in self.database or field not in self.database[key]:
            return False
        if self.database[key][field][0] == expected_value:
            if timestamp >= self.database[key][field][1]:
                return False
            self.database[key][field] = (new_value, float('inf'))
            return True
        return False

    def compare_and_delete(self, timestamp: int, key: str, field: str, expected_value: int) -> bool:
        if key not in self.database or field not in self.database[key]:
            return False
        if self.database[key][field][0] == expected_value:
            if timestamp >= self.database[key][field][1]:
                return False
            del self.database[key][field]
            return True
        return False

    def get(self, timestamp: int, key: str, field: str) -> int | None:
        if key in self.database and field in self.database[key]:
            value, expiration = self.database[key][field]
            if expiration <= timestamp:
                return None
            return value
        return None

    def scan(self, timestamp: int, key: str) -> list[str]:
        if key not in self.database:
            return []

        res = []
        for field in sorted(self.database[key].keys()):
            value, expiration = self.database[key][field]
            if expiration > timestamp:
                res.append(f"{field}({value})")
        return res

    def scan_by_prefix(self, timestamp: int, key: str, prefix: str) -> list[str]:
        res = []
        if key in self.database:
            for field in self.database[key]:
                value, expiration = self.database[key][field]
                if field.startswith(prefix) and expiration > timestamp:
                    res.append(f"{field}({value})")
        res.sort()
        return res

    def set_with_ttl(self, timestamp: int, key: str, field: str, value: int, ttl: int) -> None:
        if key not in self.database:
            self.database[key] = {}

        self.database[key][field] = (value, timestamp + ttl)
        return None

    def compare_and_set_with_ttl(self, timestamp: int, key: str, field: str, expected_value: int, new_value: int, ttl: int) -> bool:
        if key not in self.database or field not in self.database[key]:
            return False
        if timestamp >= self.database[key][field][1]:
            return False
        if self.database[key][field][0] == expected_value:
            self.database[key][field] = (new_value, timestamp + ttl)
            return True
        return False

# get_when(self, timestamp: int, key: str, field: str, at_timestamp: int) -> int | None — should return the value of field at at_timestamp from the record associated with key. If at_timestamp is 0, perform the get operation described in Level 1. It is guaranteed that at_timestamp will not be greater than timestamp. If the specified field or record did not exist at the given timestamp, return None.
    def get_when(self, timestamp: int, key: str, field: str, at_timestamp: int) -> int | None:
        if at_timestamp == 0:
            return self.get(timestamp, key, field)
        if key in self.database and field in self.database[key]:
            value, expiration = self.database[key][field]
            if expiration <= at_timestamp:
                return None
            return value
        return None
