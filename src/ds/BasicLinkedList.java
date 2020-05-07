package ds;

public class BasicLinkedList<X> {
    private Node first;
    private Node last;
    private int nodeCount;

    public BasicLinkedList() {
        first = null;
        last = null;
        nodeCount = 0;
    }

    // adds an item to the end of the list
    public void add(X item) {
        // if the list is empty
        if(size() == 0) {
            first = new Node(item);
            last = first;
        }
        // otherwise add the node and update the pointers
        else {
            Node newLastNode = new Node(item);
            last.setNextNode(newLastNode);
            last = newLastNode;
        }
        nodeCount++;
    }

    // insert a node after a given position
    public void insert(X item, int position) {
        if(size() < position) throw new IllegalStateException("The LinkedList is smaller than the position you supplied.");

        Node currentNode = first;
        // start at 1 because we are already on the first node
        for(int i = 1; i < position && currentNode != null; i++) {
            currentNode = currentNode.getNextNode();
        }
        // at this point currentNode points to node at the position we want to insert to
        Node newNode = new Node(item);
        Node nextNode = currentNode.getNextNode();
        currentNode.setNextNode(newNode);
        newNode.setNextNode(nextNode);
        nodeCount++;
    }

    public X get(int position) {
        if(first == null) throw new IllegalStateException("The LinkedList is empty.");
        Node currentNode = first;
        for(int i =1; i < size() && currentNode != null; i++) {
            if(i == position) return currentNode.getNodeItem();
            currentNode = currentNode.getNextNode();
        }

        return null;
    }
    public int find(X item) {
        if(first == null) throw new IllegalStateException("The LinkedList is empty.");
        Node currentNode = first;
        for(int i =1; i < size() && currentNode != null; i++) {
            if(currentNode.getNodeItem().equals(item)) return i;
            currentNode = currentNode.getNextNode();
        }
        return -1;
    }

    public X removeAt(int position) {
        if(size() < position) throw new IllegalStateException("The LinkedList is smaller than the position you supplied.");

        Node currentNode = first;
        Node previousNode = first;
        for(int i = 1; i < position && currentNode != null; i++) {
            previousNode = currentNode;
            currentNode = currentNode.getNextNode();
        }
        // at this point currentNode points to node before the position we want to insert to
        X nodeItem = currentNode.getNodeItem();
        previousNode.setNextNode(currentNode.getNextNode());
        nodeCount--;
        return nodeItem;

    }

    // removes the first Node
    public X remove() {
        if(first == null) throw new IllegalStateException("The LinkedList is empty and their are no items to remove");
        X nodeItem = first.getNodeItem();

        first = first.getNextNode();
        nodeCount--;
        return nodeItem;
    }

    @Override
    public String toString() {
        StringBuffer contents = new StringBuffer();
        Node curNode = first;
        while(curNode != null) {
            contents.append(curNode.getNodeItem());
            curNode = curNode.getNextNode();
            if(curNode != null) {
                contents.append(", ");
            }
        }
        return contents.toString();
    }

    public int size() {
        return nodeCount;
    }

    private class Node {
        private Node nextNode;
        private X nodeItem;

        public Node(X item) {
            this.nextNode = null;
            this.nodeItem = item;
        }

        public void setNextNode(Node nextNode) {
            this.nextNode = nextNode;
        }

        public Node getNextNode() {
            return nextNode;
        }

        public X getNodeItem() {
            return nodeItem;
        }
    }
}
