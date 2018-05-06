
public class LinkedList {
	private Node head;
	private int count = 0;

	private class Node {
		int data;
		Node next;

		Node(int input) {
			data = input;
			next = null;
		}
	}

	public void insert(int input) {
		Node temp = new Node(input);
		if (head != null) {
			temp.next = head;
		}
		head = temp;
		count++;
	}
	public void insert(int position, int input){
		if(position > count || position <0) {
			System.out.println("position value - out of range.");
			return;
		}
		
		
		if (position == 0) {
			insert(input);
			return;
		}

		Node node = head;
		for (int i = 0; i < position; i++) {
			if (i == position-1) {
				Node temp = new Node(input);
				temp.next = node.next;
				node.next = temp;
				break;
			}
			else {
				node = node.next;
			}
		}
		count++;
	}
	public boolean isEmpty() {
		if (head == null) {
			return true;
		} else {
			return false;
		}
	}

	public void delete() {
		Node target = head;
		Node temp = head.next;

		target = null;
		head = temp;
		count--;
	}
	public void delete(int position) {
		if(position > count || position <0){
			System.out.println("position value - out of range.");
			return;
		}

		if (position == 0) {
			delete();
			return;
		}
		
		Node temp = head;

		for (int i = 0; i < position; i++) {
			if (i == position - 1) {
				Node target = temp.next;

				temp.next = temp.next.next;
				target = null;
				break;
			}
			else {
				temp = temp.next;
			}
		}
		count--;
	}
	public void print_list() {
		Node temp = head;
		for (int i = 0; i < count; i++) {
			System.out.print(temp.data + "  ");
			temp = temp.next;
		}
		System.out.println("");
	}

}
