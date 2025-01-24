package BinarySearchExclusive;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        Arrays.sort(products);
        List<List<String>> res = new ArrayList<>();

        String prefix = "";
        for (int i = 0; i < searchWord.length(); i++) {
            prefix += searchWord.charAt(i);
            int start = lower(prefix, products);

            List<String> cur = new ArrayList<>();
            for (int j = start; j < products.length && cur.size() < 3; j++) {
                if (products[j].startsWith(prefix)) {
                    cur.add(products[j]);
                } else {
                    break;
                }
            }

            res.add(cur);
        }

        return res;
    }

    private int lower(String s, String[] products) {

        int left = 0;
        int right = products.length;

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (products[mid].compareTo(s) >= 0) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }
}