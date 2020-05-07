package ds;

public class BasicHashTable<X, Y> {
    private HashEntry[] data;
    private int capacity; // tells us how much entries the data can accomodate
    private int size; // tells us how many entries we have in data

    public BasicHashTable(int tableSize) {
        this.capacity = tableSize;
        data = new HashEntry[this.capacity];
        this.size = 0;
    }

    // get takes in a key and returns a value
    public Y get(X key) {
        int hash = calculateHash(key);

        if(data[hash] == null) return null;
        else {
            return (Y)data[hash].getValue();
        }

    }

    public Y delete(X key) {
        // get the value
        Y value = get(key);

        if(value != null) {
            // get the hash for the key
            int hash = calculateHash(key);
            // delete it and reduce the size
            data[hash] = null;
            size--;

            //now we need to check for cells after the deleted cell
            // if the next cell is empty, then delete is complete if not we re-insert the cell
            hash = (hash + 1) % this.capacity;
            while(data[hash] != null) {
                HashEntry en = data[hash];
                data[hash] = null; // remove the value
                put((X)en.getKey(), (Y)en.getValue()); // put it back by moving it up
                // reduce size because put has increased it, we are not inserting anything new just rearranging
                size--;
                hash = (hash + 1) % this.capacity;
            }
            // if the data at the next cell of a deleted cell is empty, the loop terminates
        }

        return value;
    }

    public void put(X key, Y value) {
        int hash = calculateHash(key);

        data[hash] = new HashEntry<X, Y>(key, value);
        size++;
    }
    private int calculateHash(X key) {
        int hash = (key.hashCode() % this.capacity);
        // this is necessary to deal with collisions
        // loop until an empty slot is encountered or we find out the key already exist
        while(data[hash] != null && !data[hash].getKey().equals(key)) {
            // calculate the new hash using linear probing
            hash = (hash + 1) % this.capacity;
        }
        return hash;
    }

    public boolean hasKey(X key) {
        int hash = calculateHash(key);
        if(data[hash] == null) return false;
        else {
            if(data[hash].getKey().equals(key)) return true;
        }
        return false;
    }

    public boolean hasValue(Y value) {
        // loop through the whole array
        for(int i = 0; i < capacity; i++) {
            HashEntry entry = data[i];

            if(entry != null && entry.getValue().equals(value)) return true;
        }
        return false;
    }

    public int size() {
        return this.size;
    }

    private class HashEntry<X, Y> {
        private X key;
        private Y value;

        public HashEntry(X key, Y value) {
            this.key = key;
            this.value = value;
        }

        public X getKey() {
            return key;
        }

        public void setKey(X key) {
            this.key = key;
        }

        public Y getValue() {
            return value;
        }

        public void setValue(Y value) {
            this.value = value;
        }
    }
}
