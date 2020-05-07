package ds;

public class BasicQueue<X> {
    private X[] data;
    private int front, end;

    public BasicQueue() {
        // call the other constructor with a size of 1000
        this(1000);
    }

    public BasicQueue(int size) {
        data = (X[]) new Object[size];
        this.front = -1;
        this.end = -1;
    }

    public int size() {
        // if the queue is empty return 0
        if(front == -1 && end == -1) return 0;
        return end - front + 1;
    }

    public void enQueue(X item) {
        // check if the queue is full
        if((end + 1) % data.length == front) {
            throw new IllegalStateException("The Queue is full!");
        }
        // otherwise check to see if any items have been added to the queue yet
        else if(size() == 0) {
            front++;
            end++;
            data[end] = item;
        }
        // otherwise just add the item to the end of the queue
        else {
            end++;
            data[end] = item;
        }
    }

    public X deQueue() {
        X item = null;
        // if the queue is empty
        if(size() == 0) {
            throw new IllegalStateException("Can't dequeue because the queue is empty");
        }
        // otherwise if this is the last item on the queue, the queue needs to be reset to empty
        else if(front == end) {
            item = data[front];
            data[front] = null;
            front = -1;
            end = -1;
        }
        // otherwise grab the front of the queue, return it and adjust the pointer
        else {
            item = data[front];
            data[front] = null;
            front++;
        }
        return item;
    }

    public boolean contains(X item) {
        boolean found = false;

        if(size() == 0) return found;

        for(int i = front; i < end+1; i++) {
            if(data[i].equals(item)) {
                found = true;
                break;
            }
        }
        return found;
    }

    public X access(int position) {
        if(size() == 0 || position > size()) {
            throw new IllegalArgumentException("No items in the queue or the position greater than queue size. ");
        }
        int trueIndex = 0;
        for(int i = front; i < end+1; i++) {
            if(trueIndex == position) return data[i];
            trueIndex++;
        }
        throw new IllegalArgumentException("Could not get the queue item at position: " + position);
    }
}
