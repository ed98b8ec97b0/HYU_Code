public class RecursiveList {
	private Node header;
	private Node trailer;
	private int size;

	private class Node {
		String data;
		Node next;
		Node prev;
		Node(String data) {
			this.data = data;
			next = null;
			prev = null;
		}
	}

	public RecursiveList() {
		header = new Node("Header");
		trailer = new Node("Trailer");
		header.next = trailer;
		trailer.prev = header;
		size = 0;
	}

	public void insertFirst(String data) {
		Node newNode = new Node(data);
		newNode.prev = header;
		newNode.next = header.next;
		header.next.prev = newNode;
		header.next = newNode;
		size++;
	}

	public void recursiveInsert(int size) {
		String value = String.valueOf((size - 1) * 5);

		if (size == 0) {
			return;
		} 
		else {
			insertFirst(value);
			recursiveInsert(size - 1);
		}
	}

	public void removeLast() {
		trailer = trailer.prev;
		size--;
	}

	public void recursiveDelete(int size) {
		if (size == 0) {
			return;
		} 
		else {
			removeLast();
			recursiveDelete(size - 1);
		}
	}

	public void printList() {
		Node temp = header.next;

		while (temp != trailer) {
			System.out.print(temp.data + " ");
			temp = temp.next;
		}
		System.out.println();
	}
}