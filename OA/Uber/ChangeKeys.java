// Your task is to count the number of times that the user changed keys while typing the sequence, 
// considering that the uppercase and lowercase letters for a given letter require the user to press the same key 
// (ignoring modifiers like Shift or Caps Lock). 
// For example, typing 'W' and 'w' require the user to press the same key, 
// whereas typing 'W' and 'E' or typing 'w' and 'e' require the user to change keys.

// Example
// For recording = ['W', 'w', 'a', 'A', 'a', 'b', 'B'], the output should be solution(recording) = 2.

// Explanation:
// Typing 'W' and 'w' require the same key 'w'.
// Typing 'A' and 'a' require the same key 'a'.
// Typing 'b' and 'B' require the same key 'b'.
// So, the user changed keys in the following order: 'w' -> 'a' -> 'b', and the total number of key changes is 2.

// For recording = ['w', 'w', 'a', 'w', 'a'], the output should be solution(recording) = 3.

class Solution {
    public int solution(char[] recording) {
        int keyChanges = 0;
        char currentKey = Character.toLowerCase(recording[0]);

        for (int i = 1; i < recording.length; i++) {
            char nextKey = Character.toLowerCase(recording[i]);
            if (nextKey != currentKey) {
                keyChanges++;
                currentKey = nextKey;
            }
        }

        return keyChanges;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        char[] recording1 = { 'W', 'w', 'a', 'A', 'a', 'b', 'B' };
        System.out.println(s.solution(recording1)); // 2

        char[] recording2 = { 'w', 'w', 'a', 'w', 'a' };
        System.out.println(s.solution(recording2)); // 3
    }
}
