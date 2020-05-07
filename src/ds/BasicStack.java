package ds;

public class BasicStack <X> {
    private X[] data;
    private int stackPointer;

    public BasicStack() {
        data = (X[]) new Object[1000];
        stackPointer = 0;
    }

    public void push(X newItem) {
        // push element to position stackPointer and then increment stackPointer
        // this is the reason for using post increment
        data[stackPointer++] = newItem;
        // this way the stackPointer is always going to point to top of the stack that
        // is currently empty i.e above the last item
    }

    public X pop() {
        if(stackPointer == 0) {
            throw new IllegalStateException("No more items on the stack");
        }
        // first decrement the stackPointer and then return the element at stackPointer
        return data[--stackPointer];
    }

    public boolean contains(X item) {
//        for(X element : data) {
//            if(element.equals(item)) return true;
//        }
//        return false;
        boolean found = false;

        if(size() == 0) return found;

        for(int i = 0; i <stackPointer; i++) {
            if(data[i].equals(item)) {
                found = true;
                break;
            }
        }
        return found;
    }

    public X access(X item) {
//        for(X element : data) {
//            if(element.equals(item)) return element;
//        }
        while (stackPointer > 0) {
            X tmpItem = pop();
            if(item.equals(tmpItem)) return tmpItem;
        }
        throw new IllegalStateException("Could not find item on the stack: " + item);
    }

    public int size() {
        return stackPointer;
    }
}
