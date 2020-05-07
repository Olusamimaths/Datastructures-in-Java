package ds;

public class BasicBinaryTree<X extends Comparable<X>> {
    private Node root;
    private int size;

    public BasicBinaryTree() {
        this.root = null;
    }

    public int size() {
        return size;
    }

    public void add(X item) {
        Node node = new Node(item);

        if(root == null) {
            this.root = node;
            System.out.println("Set root: " + node.getItem());
            this.size++;
        }
        else {
            insert(this.root, node);
        }
    }

    public boolean contains(X item) {
        Node currentNode = getNode(item);

        if(currentNode == null) return false;
        else return true;
    }

    public boolean delete(X item) {
        boolean deleted = false;

        if(this.root == null) return false;

        Node currentNode = getNode(item);

        if(currentNode != null) {
            // if the currentNode has no child just delete it
            if(currentNode.getRight() == null && currentNode.getLeft() == null) {
                unlink(currentNode, null);
                deleted = true;
            }
            // otherwise if the currentNode has a right but no left child, replace it with its right child
            else if(currentNode.getRight() != null && currentNode.getLeft() == null) {
                unlink(currentNode, currentNode.getRight());
                deleted = true;
            }
            // otherwise if it has no right child but only a left child, replace it with its left child
            else if(currentNode.getRight() == null && currentNode.getLeft() != null) {
                unlink(currentNode, currentNode.getLeft());
                deleted = true;
            }
            // otherwise if the node has both right and left nodes, we swap it out with the rightmost node on the left side
            else {
                Node child = currentNode.getLeft();
                // we are looking for a leaf node, so we terminate when we encounter a node that has no left or right child
                while (child.getRight() != null && child.getLeft() != null) {
                    child = child.getRight();
                }
                // we have the right must leaf, set the right of its parent to null
                child.getParent().setRight(null);

                child.setLeft(currentNode.getLeft());
                child.setRight(currentNode.getRight());

                unlink(currentNode, child);
                deleted = true;

            }
        }
        if(deleted) this.size--;

        return deleted;
    }

    // unlink replaces a node with another ?
    private void unlink(Node currentNode, Node newNode) {
        // if we want to unlink the root and it child,
        // the child will just take the place of the root
        if(currentNode == this.root) {
            newNode.setLeft(currentNode.getLeft());
            newNode.setRight(currentNode.getRight());
            this.root = newNode;
        }
        //otherwise if the currentNode is the right child of its parent, set the newNode to take its place
        else if(currentNode.getParent().getRight() == currentNode) {
            currentNode.getParent().setRight(newNode);
        }
        // otherwise it is the left child of its parent, set the newNode to take its place
        else {
            currentNode.getParent().setLeft(newNode);
        }
    }

    private Node getNode(X item) {
        Node currentNode = this.root;

        while(currentNode != null) {
            int value = item.compareTo(currentNode.getItem());
            // we have found the item is value is 0;
            if(value == 0) {
                return currentNode;
            }
            // if value is less than 0, we go left
            else if(value < 0) {
                // we continue to the left
                currentNode = currentNode.getLeft();
            }
            else {
                // we continue to the right
                currentNode = currentNode.getRight();
            }
        }
        // if we reach here without finding the node...
        return null;
    }

    private void insert(Node parent, Node child) {
        // if the child is less thant the parent, it goes on the left
        if(child.getItem().compareTo(parent.getItem()) < 0) {
            // if the left of the parent is null, just insert it there
            if(parent.getLeft() == null) {
                parent.setLeft(child);
                child.setParent(parent);
                this.size++;
            } else {
                // we recursively try to insert to the left
                insert(parent.getLeft(), child);
            }
        }
        else if(child.getItem().compareTo(parent.getItem()) > 0) {
            if(parent.getRight() == null) {
                parent.setRight(child);
                child.setParent(parent);
                this.size++;
            } else {
                insert(parent.getRight(), child);
            }
        }
    }

    private class Node {
        private Node left;
        private Node right;
        private Node parent;
        private X item;

        public Node(X item) {
            this.item = item;
            this.left = null;
            this.right = null;
            this.parent = null;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        public Node getParent() {
            return parent;
        }

        public void setParent(Node parent) {
            this.parent = parent;
        }

        public X getItem() {
            return item;
        }

        public void setItem(X item) {
            this.item = item;
        }
    }
}
