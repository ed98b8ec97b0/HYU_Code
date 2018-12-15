
public class Queue_prob {
	private Node front;
	private Node back;
	
	private class Node{
		private int data;
		private Node next;
		Node(int data){
			this.data = data;
		}
	}
	public Queue_prob() {
		front = null;
		back = null;
	}

	public void enqueue(int data){
		Node temp = new Node(data);
		
		if (isEmpty()) {
			front = temp;
			back = temp;
			return;
		}
		temp.next = back;
		back = temp;
	}

	public Node dequeue(){
		Node iterator = back;
		
		if (isEmpty()) {
			return null;
		}
		while (iterator.next != front) {
			iterator = iterator.next;
		}
		Node temp = iterator.next;
		iterator.next = null;
		front = iterator;

		return temp;
	}

	public void printQueue(){
		Node iterator = back;

		while (iterator != null) {
			System.out.print(iterator.data + " ");
			iterator = iterator.next;
		}
		System.out.println();
	}

	public boolean isEmpty(){
		if(front == null){
			return true;
		} else {
			return false;
		}
	}

}
