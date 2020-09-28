import java.util.List;
import java.util.NoSuchElementException;
public class SimpleLinkedList {
	public static String[] entry;
	public static int length;
	private static Node first;
	private static Node last;
	public SimpleLinkedList() {
		first = null;
		last = null;
		length = 0;
	}
	public SimpleLinkedList(List<String> list) { //converts list into linked list
		first = new Node (null, null, null);
		last = new Node (null, null, null);
		Node currentNode = first;
		for (int k = 0; k < list.size(); k++) {
			currentNode.entry = list.get(k);
			Node prevNode = currentNode;
			currentNode = new Node (currentNode, null, null);
			prevNode.forward = currentNode;
		}
		currentNode.entry = list.get(list.size()-1);
		last = currentNode;
		length = list.size();
	}
	public static void main(String[] args) {
	}
	public void add (int index, String given) { //adds string at given index
		if (index < 0 || index > length) {
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + length);
		}
		if (index == 0) {
			addFirst(given);
		} else if (index == length - 1) {
			addLast(given);
		} else {
			Node currentNode = getNode(index);
			Node newNode = new Node (currentNode.back, given, currentNode);
			currentNode.back.forward = newNode;
			currentNode.back = newNode;
			length++;
		}
	}
	public void addFirst (String given) { //adds string at the beginning of list
		Node currentNode = first;
		Node newNode = new Node (null, given, currentNode);
		first = newNode;
		if (length == 0) {
			last = newNode;
		} else {
			currentNode.back = newNode;
		}
		length++;
	}
	public void addLast (String given) { //adds string at the end of list
		Node currentNode = last;
		Node newNode = new Node (currentNode, given, null);
		last = newNode;
		if (length == 0) {
			first = newNode;
		} else {
			currentNode.forward = newNode;
		}
		length++;
	}
	public void clear () { //clears list
		first = new Node (null, null, null);
		last = new Node (null, null, null);
		length = 0;
		}
	public boolean contains (String given) { //checks if string is in list
		for(int x = 0; x < length; x++) {
			if (getNode(x).entry == given) {
				return true;
			}
		}
		return false;
	}
	public String get (int index) { //retrieves the string at given index
		if (index < 0 || index > length) {
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + length);
		}
		return getNode(index).entry;
	}
	public String getFirst () { //retrieves the string at beginning of list
		if (length == 0) {
			throw new NoSuchElementException();
		}
		return first.entry;
	}
	public String getLast () { //retrieves the string at end of list
		if (length == 0) {
			throw new NoSuchElementException();
		}
		return last.entry;
	}
	public int indexOf (String given) { //returns index of a string
		for(int k = 0; k < length; k++) {
			if (getNode(k).entry == given) {
				return k;
			}
		}
		return -1;
	}
	public String remove (int index) { //removes the string at a given index
		if (index < 0 || index > length) {
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + length);
		} else if (index == 0) {
			return removeFirst();
		} else if (index == length - 1) {
			return removeLast();
		} else {
			Node changedNode = getNode(index);
			changedNode.back.forward = changedNode.forward;
			changedNode.forward.back = changedNode.back;
			String changed = changedNode.entry;
			length--;
			return changed;
		}
	}
	public boolean remove (String given) { //removes string from list 
		boolean isFound = contains(given);
		if (isFound == false) {
			return false;
		} else {
			int index = indexOf(given);
			if (index == 0) {
				removeFirst();
				return true;
			} else if (index == length) {
				removeLast();
				return true;
			} else {
				Node changedNode = getNode(index);
				changedNode.back.forward = changedNode.forward;
				changedNode.forward.back = changedNode.back;
				length--;
				return true;
			}
		}
	}
	public String removeFirst () { //removes first string in list
		String changed = first.entry;
		Node changedNode = first;
		first = changedNode.forward;
		first.back = null;
		length--;
		return changed;
	}
	public String removeLast () { //removes last string in list
		String changed = last.entry;
		Node changedNode = last;
		last = changedNode.back;
		last.forward = null;
		length--;
		return changed;
	}
	public String set (int index, String given) { //replaces current string at given index with new string
		if (index < 0 || index > length) {
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + length);
		}
		Node currentNode = getNode(index);
		String changed = currentNode.entry;
		if (given == null) given = "null";
		currentNode.entry = given;
		return changed;
	}
	public int size () { //returns size of list
		return length;
	}
	public String toString () { //converts list into string
		String changed = "[";
		Node currentNode = first;
		if (length != 0) {
			for (int k = 0; k < length - 1; k++) {
				changed += currentNode.entry + ", ";
				currentNode = currentNode.forward;
			}
			changed += currentNode.entry + "]";
		} else {
			return "[]";
		}
		return changed;
	}
	public Node getNode(int index) {
		Node currentNode = first;
		for (int k = 0; k < index; k++) {
			currentNode = currentNode.forward;
		}
		return currentNode;
	}
	public static class Node {
		Node back;
		String entry;
		Node forward;
		public Node(Node back, String entry, Node forward) {
			this.back = back;
			this.entry = entry;
			this.forward = forward;
		}
	}
 }