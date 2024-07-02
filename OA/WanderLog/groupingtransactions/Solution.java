import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {

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

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        int transactionsCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<String> transactions = IntStream.range(0, transactionsCount).mapToObj(i -> {
            try {
                return bufferedReader.readLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        })
                .collect(toList());

        List<String> result = Result.groupTransactions(transactions);

        bufferedWriter.write(
                result.stream()
                        .collect(joining("\n"))
                        + "\n");

        bufferedReader.close();
        bufferedWriter.close();
    }
}
