package DataStructure.Trie;

class TrieNode {
    TrieNode[] children = new TrieNode[26];
    boolean isWord;
}

class WordDictionary {

    TrieNode root;

    public WordDictionary() {
        root = new TrieNode();
    }

    public void addWord(String word) {
        TrieNode cur = root;
        for (char c : word.toCharArray()) {
            if (cur.children[c - 'a'] == null) {
                cur.children[c - 'a'] = new TrieNode();
            }
            cur = cur.children[c - 'a'];
        }
        cur.isWord = true;
    }

    public boolean search(String word) {
        return helper(word, 0, root);
    }

    private boolean helper(String word, int index, TrieNode curNode) {
        if (index == word.length()) {
            return curNode.isWord;
        }
        char ch = word.charAt(index);
        if (ch != '.') {
            return curNode.children[ch - 'a'] != null && helper(word, index + 1, curNode.children[ch - 'a']);
        }
        for (var child : curNode.children) {
            if (child != null && helper(word, index + 1, child)) {
                return true;
            }
        }

        return false;
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */