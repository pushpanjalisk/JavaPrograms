class Node {
    int data;
    Node prevNode;

    Node(int data) {
        this.data = data;
        this.prevNode = null;
    }
}

class Stack {
    int size;
    Node top;

    Stack() {
        this.top = null;
        this.size = 0;
    }

    boolean isEmpty() {
        if (this.top == null)
            return true;
        else
            return false;
    }

    Node getTop() {
        return this.top;
    }

    int getSize() {
        return size;
    }

    void push(int data) {
        Node newTop = new Node(data);

        if (this.isEmpty()) {
            this.top = newTop;
        } else {
            Node tmpTop = this.top;
            this.top = newTop;
            newTop.prevNode = tmpTop;
        }
        this.size++;
    }

    void printStackData() {
        Node currentNode = this.top;
        while (currentNode != null) {
            System.out.println(currentNode.data);
            currentNode = currentNode.prevNode;
        }
    }

    int peek() {
        return this.top.data;
    }

    int pop() {
        Node popNode = this.top;
        this.top = this.top.prevNode;
        this.size--;
        return popNode.data;
    }

    void deleteNode(int data) {
        Node currentNode = this.top;
        Node nextNode = this.top;
        if (data == this.top.data) {
            this.top = this.top.prevNode;
        } else {
            while (currentNode != null) {
                if (data == currentNode.data) {
                    nextNode.prevNode = currentNode.prevNode;
                    this.size--;
                    break;
                }
                nextNode = currentNode;
                currentNode = currentNode.prevNode;
            }
        }

    }
}

class simpleStack {
    public static void main(String[] args) {
        System.out.println("Stack implementation in Java");

        Stack bookStack = new Stack();
        bookStack.push(1);
        bookStack.push(2);
        bookStack.push(3);
        bookStack.push(4);
        bookStack.push(5);

        // bookStack.printStackData();
        // System.out.println(bookStack.pop());
        // System.out.println("Peeking: " + bookStack.peek());
        // System.out.println("Size: " + bookStack.getSize());

        // System.out.println(bookStack.pop());
        // System.out.println("Peeking: " + bookStack.peek());
        // System.out.println("Size: " + bookStack.getSize());

        bookStack.deleteNode(5);
        bookStack.printStackData();
    }
}