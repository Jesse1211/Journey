
import java.util.HashMap;
import java.util.Map;

class Solution {
    public int[] rearrangeBarcodes(int[] barcodes) {
        Map<Integer, Integer> map = new HashMap<>();
        int max = Integer.MIN_VALUE;
        int maxBarcode = -1;
        for (var barcode : barcodes) {
            map.put(barcode, map.getOrDefault(barcode, 0) + 1);
            if (map.get(barcode) >= max) {
                max = map.get(barcode);
                maxBarcode = barcode;
            }
        }

        int res[] = new int[barcodes.length];
        int index = 0;

        for (int i = 0; i < max; i++) {
            res[index] = maxBarcode;
            index += 2;
            // map.put(maxBarcode, map.get(maxBarcode) - 1); // optional
        }

        for (var entry : map.entrySet()) {
            if (entry.getKey() == maxBarcode) { // optional
                continue;
            }

            for (int i = 0; i < entry.getValue(); i++) {
                if (index >= res.length) {
                    index = 1;
                }

                res[index] = entry.getKey();
                index += 2;
            }
        }
        return res;
    }
}