package corrector;

import java.util.Arrays;

public class Trie implements ITrie {
    private TrieNode root = new TrieNode();

    @Override
    public void add(String word) {
        TrieNode current = root;
        for (char c : word.toCharArray()) {
            int index = c - 'a';
            if (current.children[index] == null) {
                current.children[index] = new TrieNode();
            }
            current = current.children[index];
        }
        current.incrementValue();
        current.isEnd = true;
    }

    @Override
    public ITrieNode find(String word) {
        TrieNode current = root;
        for (char c : word.toCharArray()) {
            int index = c - 'a';
            if (current.children[index] == null) {
                return null;
            }
            current = current.children[index];
        }
        return current.isEnd ? current : null;
    }

    @Override
    public int getNodeCount() {
        return countNodesInTrie(root);
    }

    private int countNodesInTrie(TrieNode node) {
        if (node == null) {
            return 0;
        }
        int count = 0;
        for (TrieNode child : node.children) {
            if (child != null) {
                count += countNodesInTrie(child);
            }
        }
        return 1 + count;
    }

    @Override
    public int getWordCount() {
        return countWordsInTrie(root);
    }

    private int countWordsInTrie(TrieNode node) {
        int count = 0;
        if (node.isEnd) {
            count++;
        }
        for (TrieNode child : node.children) {
            if (child != null) {
                count += countWordsInTrie(child);
            }
        }
        return count;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        printAllWords(root, new char[50], 0, sb);
        return sb.toString().trim();
    }

    private void printAllWords(TrieNode node, char[] wordArray, int pos, StringBuilder sb) {
        if (node == null) {
            return;
        }
        if (node.isEnd) {
            sb.append('\n').append(Arrays.copyOfRange(wordArray, 0, pos));
        }
        for (int i = 0; i < 26; i++) {
            if (node.children[i] != null) {
                wordArray[pos] = (char) (i + 'a');
                printAllWords(node.children[i], wordArray, pos + 1, sb);
            }
        }
    }

    @Override
    public int hashCode() {
        return calculateHashCode(root);
    }

    private int calculateHashCode(TrieNode node) {
        if (node == null) {
            return 0;
        }
        int hash = 17;
        for (TrieNode child : node.children) {
            if (child != null) {
                hash = 31 * hash + calculateHashCode(child);
            }
        }
        return hash;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Trie) {
            Trie other = (Trie) o;
            return compareTrie(root, other.root);
        }
        return false;
    }

    private boolean compareTrie(TrieNode p, TrieNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        if (p.isEnd != q.isEnd || p.count != q.count) {
            return false;
        }
        for (int i = 0; i < 26; i++) {
            if (!compareTrie(p.children[i], q.children[i])) {
                return false;
            }
        }
        return true;
    }
}
