import java.util.HashMap;
import java.util.Map;

class TrieNode {
    char ch;
    HashMap<Character, TrieNode> hashMap = new HashMap<>();
    boolean isEnd;

    public TrieNode(char ch) {
        this.ch = ch;
    }

    public TrieNode() {
        this.ch = '\0';
    }

    void markAsLeaf() {
        isEnd = true;
    }
}

class PrefixTree {
    private TrieNode root;

    public PrefixTree() {
        root = new TrieNode();
    }

    void insert(String word) {
        TrieNode current = root;

        for (int i = 0; i < word.length(); i ++) {
            char ch = word.charAt(i);

            if (!current.hashMap.containsKey(ch)) {
                TrieNode trieNode = new TrieNode(ch);
                current.hashMap.put(ch, trieNode);
            }
            current = current.hashMap.get(ch);

        }
        current.markAsLeaf();
    }

    boolean search(String word) {
        TrieNode current = root;

        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);

            if (!current.hashMap.containsKey(ch)) {
                return false;
            }

            current = current.hashMap.get(ch);
        }
        return current.isEnd;
    }

    boolean startsWith(String prefix) {
        TrieNode current = root;

        for (int i = 0; i < prefix.length(); i++) {
            char ch = prefix.charAt(i);

            if (!current.hashMap.containsKey(ch)) {
                return false;
            }

            current = current.hashMap.get(ch);
        }
        return true;
    }

    void traverse() {
        for (Map.Entry<Character, TrieNode> entry : root.hashMap.entrySet()) {
            traverseLoop(entry.getValue(), 0);
        }
    }

    private void traverseLoop(TrieNode node, int level) {

        for (int i = 0; i < level; i++) {
            System.out.print("  ");
        }
        System.out.print("  └── " + node.ch);

        if (node.isEnd) {
            System.out.print(" (end)");
        }

        System.out.println();

        for (Map.Entry<Character, TrieNode> entry : node.hashMap.entrySet()) {
            traverseLoop(entry.getValue(), level + 1);
        }
    }
}
