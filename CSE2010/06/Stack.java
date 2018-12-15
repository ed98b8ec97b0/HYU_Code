public class Stack {       
    private Node top; 
    private class Node {
        private String data;
        private Node next;
        Node(String data){
         this.data = data;
        }
    }
    
    // Constructor (생성자)
    public Stack() {
        top = null;
    }

    /*
     * Stack 안에 있는 elements 의 개수를 반환한다.
     */
    public int size(){
        int n = 0;
        Node temp = top;

        while (temp != null) {
            temp = temp.next;
            n++;
        }

        return n;
    }

    /*
     * Stack이 비어있는지를 반환한다.
     */
    public boolean isEmpty(){
        if (top == null) {
            return true;
        }
        else {
            return false;
        }
    }

    /*
     * Stack의 top 자리에 data를 가지는 Node를 삽입한다.
     */
    public void push(String data){
        Node temp = new Node(data);
        temp.next = top;
        top = temp;
    }
   
    /*
     * Stack의 top을 반환한다. (제거 x)
     */    
    public String top() {
        if (top == null) {
            System.out.println("There is no data.");

            return null;
        }
        System.out.println("Top data = " + top.data);

        return top.data;
    }


    /*
     * Stack의 top을 제거하며, 제거한 Node를 반환한다.
     */    
    public Node pop(){
        if (top == null) {
            System.out.println("There is no data.");

            return null;
        }
        Node temp = top;
        top = top.next;
        
        return temp;
    }
   

    /*
     * Stack 을 top부터 마지막 Node까지 출력한다.
     */    
    public void printStack(){
        Node temp = top;

        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
        System.out.println();
    }


}
