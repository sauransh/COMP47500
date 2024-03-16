package corrector;

public class TrieNode implements ITrieNode {

	TrieNode[] children = new TrieNode[26];
	int count;
	boolean isEnd;

	@Override
	public int getValue() {
		return count;
	}

	@Override
	public void incrementValue() {
		count++;
	}

	@Override
	public ITrieNode[] getChildren() {
		return children;
	}
}
