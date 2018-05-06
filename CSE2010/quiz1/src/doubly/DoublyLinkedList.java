package doubly;

public class DoublyLinkedList {
	private Node header;
	private Node trailer;
	private int size;
	private class Node {
    	String name;
    	String phone;
    	Node prev;
	    Node next;

	    Node(String name, String phone) {
	        this.name = name;
	        this.phone = phone;
	        prev = null;
	        next = null;
	    }
		// 이름과 전화번호를 받아 Doubly Linked List용 Node를 만든다.
		// 이때, prev와 next는 따로 받지 않기때문에 null로 둔다. 
	}

	public DoublyLinkedList() {
		header = null;
		trailer = null;
		size = 0;
	}
	// doubly linked list 생성자로 처음에는 비어있기때문에 header와 trailer가 null을 가리킨다.

	public void addFirst(String name, String phone) {
		Node temp = new Node(name, phone);
		
		if (size == 0) {
			header = temp;
			trailer = temp;
		}
		else {
			temp.next = header;
			header.prev = temp;
			header = temp;
		}
		size++;
	}
	// 우선 넣고자하는 node를 임시로 temp라고 지정한 뒤, size가 0이면 header와 trailer 모두 temp를 가리키게 하고
	// 그 외의 경우에는 temp의 next가 현재의 header를 가리키고 현재의 header의 prev가 temp를 가리키게 만들었다.
	// 이렇게 한 뒤 header를 temp로 지정했다.  

	public void addLast(String name, String phone) {
		Node temp = new Node(name, phone);

		if (size == 0) {
			header = temp;
			trailer = temp;
		}
		else {
			temp.prev = trailer;
			trailer.next = temp;
			trailer = temp;
		}
		size++;
	}
	// addFirst와 비슷한 방식으로 했으나 size == 0 이 아닐 때 header 대신 trailer를 사용했을 뿐이다.
	// prev와 next가 가리키는 것도 조금 바뀌었다.

	public void addAt(int position, String name, String phone) {
		if (position > size || position < 0) {
			System.out.println("position value - out of range.");
			return;
		}

		if (position == 0) {
			addFirst(name, phone);
		}

		else if (position == size) {
			addLast(name, phone);
		}

		else {
			Node temp = header;
			for (int i = 1; i < position; i++) {
				temp = temp.next;
			}
			Node newNode = new Node(name, phone);
			newNode.next = temp.next;
			newNode.prev = temp;
			temp.next.prev = newNode;
			temp.next = newNode;
			size++;
		}
	}
	// 맨 끝에도 집어 넣을 수 있게 넣을 수 있는 index를 0~size까지 설정 해두었고
	// 넣고자하는 position에 따라 3가지의 경우로 나누었다.
	// 처음과 끝일 땐 기존에 만들어둔 것을 이용하였고
	// 사이에 끼어있을 경우 위치를 탐색하는 temp와 새로 넣을 newNode를 이용해 사이에 끼워넣었다.

	public void removeFirst() {
		if (size == 0) {
			System.out.println("Empty");
		}
		else {
			System.out.println("Delete (" + header.name + ": " + header.phone + ") ");
			header = header.next;
			size--;
		}
	}
	// size가 0일 경우 list가 비어있다는 걸 출력하게 했고 아닐 경우 header를 그 다음 node에 넘겨주는 것으로 현재의 header를 지워버렸다. 

	public void removeLast() {
		if (size == 0) {
			System.out.println("Empty");
		}
		else {
			System.out.println("Delete (" + trailer.name + ": " + trailer.phone + ") ");
			trailer = trailer.prev;
			size--;
		}
	}
	// removeFirst와 마찬가지로 하였으며 차이점은 header 대신 trailer를 이용하였을 뿐이다.

	public void removeAt(int position) {
		if (position >= size || position < 0) {
			System.out.println("position value - out of range.");
			return;
		}

		if (position == 0) {
			removeFirst();
		}

		else if (position == size - 1) {
			removeLast();
		}

		else {
			Node temp = header;
			for (int i = 1; i < position; i++) {
				temp = temp.next;
			}
			System.out.println("Delete (" + temp.next.name + ": " + temp.next.phone + ") ");
			temp.next.next.prev = temp;
			temp.next = temp.next.next;
			size--;
		}
	}
	// position의 범위를 0~size-1로 정한 이유는 추가할 때와 달리 삭제하고자 할 땐 정확히 index만큼만 필요해서이다. 
	// 이전의 addAt 과 마찬가지로 position이 적절한 숫자이면 3가지의 경우로 나누었다.
	// 중간에 있는 node를 지울 때, 지우고자 하는 노드를 가리키는 노드가 없도록 만들었다. 

	public void printList() {
		Node temp = header;
		System.out.print("[");
		for (int i = 0; i < size; i++) {
			System.out.print("(" + temp.name + ": " + temp.phone + ")");
			temp = temp.next;
			if (i < size-1) {
				System.out.print(" ");
			}
		}
		System.out.println("]");
	}
	// header부터 시작하여 trailer까지 돌아가는 for문을 작성하였다.
	// size를 이용하여 계산하였으며 출력의 형태는 list는 []로 표시하고 node는 (name: phone)으로 표시하였다.
	// 각각의 node 사이에는 공백이 한칸 존재한다.

	public void printListBack() {
		Node temp = trailer;
		System.out.print("[");
		for (int i = 0; i < size; i++) {
			System.out.print("(" + temp.name + ": " + temp.phone + ")");
			temp = temp.prev;
			if (i < size-1) {
				System.out.print(" ");
			}
		}
		System.out.println("]");
	}
	// printList와는 반대로 trailer에서부터 시작하여 header까지 돌아가는 for문이다.
	// 이 이외의 차이점은 없다.
}
