package corrector;

public interface ITrieNode {
    int getValue();

    void incrementValue();

    ITrieNode[] getChildren();
}