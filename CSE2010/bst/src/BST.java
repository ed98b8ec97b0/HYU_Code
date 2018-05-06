
public class BST {
	private Node root;
	
	private class Node{
		private int key;
		private Node left_child;
		private Node right_child;
		private Node parent;
		
		Node(int key){
			this.key = key;
		}
	}
	
	public void insert(int key){

		if(this.search(key) != null){
			System.out.println(key + " is already in the tree");
			return;
		}
		Node newNode = new Node(key);	// assume the data is same as key
		if (root == null) {
			root = newNode;
			return;
		}
		Node head = root;
		while (true) {
			if (newNode.key < head.key) {
				if (head.left_child == null) {
					newNode.parent = head;
					head.left_child = newNode;
					return;
				}
				head = head.left_child;
			}
			else { // newNode.key > head.key
				if (head.right_child == null) {
					newNode.parent = head;
					head.right_child = newNode;
					return;
				}
				head = head.right_child;
			}
		}
	}

	public void delete(int key){
		Node head = this.search(key);

		if (head.left_child == null && head.right_child == null) {
			if (head.key < head.parent.key) {
				head.parent.left_child = null;
			}
			else {
				head.parent.right_child = null;
			}
			head.parent = null;
			head = null;
			return;
		}
		else if (head.left_child != null && head.right_child == null) {
			if (head.key < head.parent.key) {
				head.left_child.parent = head.parent;
				head.parent.left_child = head.left_child;
			}
			else {
				head.left_child.parent = head.parent;
				head.parent.right_child = head.left_child;
			}
			head.parent = null;
			head.left_child = null;
			head = null;
			return;
		}
		else if (head.left_child == null && head.right_child != null) {
			if (head.key < head.parent.key) {
				head.right_child.parent = head.parent;
				head.parent.left_child = head.right_child;
			}
			else {
				head.right_child.parent = head.parent;
				head.parent.right_child = head.right_child;
			}
			head.parent = null;
			head.right_child = null;
			head = null;
			return;
		}
		else {
			Node biggest = head.left_child;
			while (biggest.right_child != null) {
				biggest = biggest.right_child;
			}
			if (biggest.parent.key < biggest.key) {
				biggest.parent.right_child = null;
				biggest.parent = head.parent;
				biggest.left_child = head.left_child;
				biggest.right_child = head.right_child;
				head.left_child.parent = biggest;
				head.right_child.parent = biggest;
			}
			else { // left_child에서 바로 끝날 때.
				head.left_child = null;
				biggest.parent = head.parent;
				biggest.right_child = head.right_child;
				head.right_child.parent = biggest;
			}
			if (head == root) {
				root = biggest;
			}
			else {
				if (head.key < head.parent.key) {
					head.parent.left_child = biggest;
				}
				else {
					head.parent.right_child = biggest;
				}
			}
			head.parent = null;
			head.left_child = null;
			head.right_child = null;
			head = null;
			return;
		}
	}

	public Node search(int key){
		Node head = root;

		while (head != null) {
			if (key < head.key) {
				head = head.left_child;
			}
			else if (key > head.key) {
				head = head.right_child;
			} 
			else { // key = head.key;
				return head;
			}
		}
		return null;
	}
	public void printPreorder(Node node){

		System.out.printf("%d ", node.key);
		if (node.left_child != null) {
			printPreorder(node.left_child);
		}
		if (node.right_child != null) {
			printPreorder(node.right_child);
		}
	}
	public void printInorder(Node node){

		if (node.left_child != null) {
			printInorder(node.left_child);
		}
		System.out.printf("%d ",node.key);
		if (node.right_child != null) {
			printInorder(node.right_child);
		}
	}
	public void printPostorder(Node node){
		if (node.left_child != null) {
			printPostorder(node.left_child);
		}	
		if (node.right_child != null) {
			printPostorder(node.right_child);
		}
		System.out.printf("%d ",node.key);	
	}
	public Node getRoot(){
		return root;
	}
}