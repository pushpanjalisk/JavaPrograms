class Node {
    int data;
    Node prevNode;
    Node nextNode;

    Node(int data) {
        this.data = data;
        this.prevNode = null;
        this.nextNode = null;
    }
}

class Queue {
    int size;
    Node head;
    Node top;

    Queue() {
        this.head = null;
        this.top = null;
        this.size = 0;
    }

    boolean isEmpty() {
        if (this.head == null)
            return true;
        else
            return false;
    }

    Node getTop() {
        return this.top;
    }

    Node getHead() {
        return this.head;
    }

    int getSize() {
        return size;
    }

    void push(int data) {
        Node newNode = new Node(data);

        if (this.isEmpty()) {
            this.head = newNode;
            this.top = this.head;
        } else {
            Node tmpNode = this.top;
            this.top = newNode;
            this.top.prevNode = tmpNode;
            this.top.prevNode.nextNode = this.top;
        }
        this.size++;
    }

    void printQueueData() {
        Node currentNode = this.head;
        while (currentNode != null) {
            System.out.println(currentNode.data);
            currentNode = currentNode.nextNode;
        }
    }

    int peek() {
        return this.head.data;
    }

    int pop() {
        Node popNode = this.head;
        this.head = this.head.nextNode;
        this.size--;
        return popNode.data;
    }

    void deleteNode(int data) {
        Node currentNode = this.head;

        if (data == this.head.data) {
            this.head = this.head.nextNode;
            this.size--;
        } else {
            while (currentNode != null) {
                if (data == currentNode.data) {
                    currentNode.prevNode.nextNode = currentNode.nextNode;
                    this.size--;
                    break;
                }
                currentNode = currentNode.nextNode;
            }
        }

    }
}

class simpleQueue {
    public static void main(String[] args) {
        System.out.println("Stack implementation in Java");

        Queue visitorQueue = new Queue();
        visitorQueue.push(1);
        visitorQueue.push(2);
        visitorQueue.push(3);
        visitorQueue.push(4);
        visitorQueue.push(5);

        visitorQueue.printQueueData();
        System.out.println("Delete 3 : ");
        visitorQueue.deleteNode(1);
        visitorQueue.printQueueData();
        // System.out.println("Peek : " + visitorQueue.peek());

        // visitorQueue.printQueueData();
    }
}