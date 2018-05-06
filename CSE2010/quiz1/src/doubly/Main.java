package doubly;
import java.util.*;

public class Main {

	public static void listCall(DoublyLinkedList list, int atAdd, int atRemove) {
		list.addFirst("A", "01000000000");
		list.printList();
		list.addLast("B", "01011111111");
		list.printList();
		list.addLast("C", "01022222222");
		list.printList();
		list.addAt(atAdd, "Insert", "01033333333");
		list.printList();
		list.removeAt(atRemove);
		list.printList();
		list.removeFirst();
		list.printList();
		list.removeLast();
		list.printList();

		// linked list를 받아 총 4개의 node를 넣고 위치지정 제거, 처음 node 제거, 마지막 node 제거를 차례대로 수행하며
		// 1회 수행시마다 list를 화면에 표시한다.
	}

	public static void listBackCall(DoublyLinkedList list, int atAdd, int atRemove) {
		list.addFirst("A", "01000000000");
		list.printListBack();
		list.addLast("B", "01011111111");
		list.printListBack();
		list.addLast("C", "01022222222");
		list.printListBack();
		list.addAt(atAdd, "Insert", "01033333333");
		list.printListBack();
		list.removeAt(atRemove);
		list.printListBack();
		list.removeFirst();
		list.printListBack();
		list.removeLast();
		list.printListBack();

		// listCall과의 차이점은 list를 역순으로 표시한다는 것이다.
	}

	public static void main(String[] args) {
		int command, atAdd, atRemove; 
		Scanner scan = new Scanner(System.in);

		do {
			DoublyLinkedList list = new DoublyLinkedList();
			command = scan.nextInt();
			if (command == 0) {
				break;
			}
			atAdd = scan.nextInt();
			atRemove = scan.nextInt();
			switch (command) {
				case 1: 
					listCall(list, atAdd, atRemove);
					break;
				case 2:
					listBackCall(list, atAdd, atRemove);
					break;
			}
		} while (true);
		scan.close();

		// DoublyLinkedList를 만든 뒤 command를 받는다.
		// 이때 command가 0이라면 바로 while loop를 종료한다.
		// 이후 Node를 추가할 때 쓰는 position을 받는 atAdd, 제거할 때 쓰는 position을 받는 atRemove를 받는다.
		// command가 1이라면 본래 순서대로, 2라면 역순으로 list를 표시하는 함수를 호출한다.
	}
}
