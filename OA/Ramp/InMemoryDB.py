from abc import ABC


class InMemoryDB(ABC):
    """
    `InMemoryDB` interface.
    """

    def set(self, timestamp: int, key: str, field: str, value: int) -> None:
        """
        Should insert a `field`-`value` pair to the record
        associated with `key`.
        If the `field` in the record already exists, replace the
        existing value with the specified `value`.
        If the record does not exist, a new one is created.
        """
        # default implementation
        pass

    def compare_and_set(self, timestamp: int, key: str, field: str, expected_value: int, new_value: int) -> bool:
        """
        Should update the value of `field` in the record associated
        with `key` to `new_value` if the current value equals
        `expected_value`.
        If `expected_value` does not match the current value, or
        either `key` or `field` does not exist, this operation is
        ignored.
        This operation should return `True` if the `field` was
        updated and `False` otherwise.
        """
        # default implementation
        return False

    def compare_and_delete(self, timestamp: int, key: str, field: str, expected_value: int) -> bool:
        """
        Should remove the field `field` in the record associated
        with `key` if the previous value equals `expected_value`.
        If `expected_value` does not match the current value, or
        either `key` or `field` does not exist, this operation is
        ignored.
        This operation should return `True` if the `field` was
        removed and `False` otherwise.
        """
        # default implementation
        return False

    def get(self, timestamp: int, key: str, field: str) -> int | None:
        """
        Should return the value contained within `field` of the
        record associated with `key`.
        If the record or the `field` does not exist, should return
        `None`.
        """
        # default implementation
        return None
