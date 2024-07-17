package OA.WanderLog;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class GroupTransactions {

    /*
     * Complete the 'groupTransactions' function below.
     *
     * The function is expected to return a STRING_ARRAY.
     * The function accepts STRING_ARRAY transactions as parameter.
     */

    // group all of the transactions by item name. Return an array of strings where
    // each string contains the item name followed by a space and the number of
    // associated transactions.
    // Note: Sort the array descending by transaction count, then ascending
    // alphabetically by item name for items with matching transaction counts.

    // Example
    // transactions = ['notebook', 'notebook', 'mouse', 'keyboard', 'mouse']

    // There are two items with 2 transactions each: 'notebook' and 'mouse'. In
    // alphabetical order, they are 'mouse', 'notebook'.

    // There is one item with 1 transaction: 'keyboard'.

    // The return array, sorted as required, is ['mouse 2', 'notebook 2', 'keyboard
    // 1'].
    public static List<String> groupTransactions(List<String> transactions) {

        Map<String, Integer> map = new HashMap<>();
        for (String transaction : transactions) {
            map.put(transaction, map.getOrDefault(transaction, 0) + 1);
        }

        List<String> result = new ArrayList<>();
        map.entrySet().stream().sorted((e1, e2) -> {
            if (e1.getValue() == e2.getValue()) {
                return e1.getKey().compareTo(e2.getKey());
            }
            return e2.getValue() - e1.getValue();
        }).forEach(e -> {
            result.add(e.getKey() + " " + e.getValue());
        });

        return result;
    }

}
