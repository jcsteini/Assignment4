/*
This program recreates the LinkedList data structure and the methods associated 
with it such as get, set, indexOf, lastIndexOf, and contains.
 */
package  assignment4;

/**
 * @author Jonathan Steininger
 * @param <E>
 */
public class MyLinkedList<E> extends MyAbstractList<E> {

    private Node<E> head, tail;

    /**
     * Create a default list
     */
    public MyLinkedList() {
    }

    /**
     * Create a list from an array of objects
     * @param objects
     */
    public MyLinkedList(E[] objects) {
        super(objects);
    }

    /**
     * Return the head element in the list
     * @return the first element
     */
    public E getFirst() {
        if (size == 0) {
            return null;
        } else {
            return head.element;
        }
    }

    /**
     * Return the last element in the list
     * @return the element of the last node
     */
    public E getLast() {
        if (size == 0) {
            return null;
        } else {
            return tail.element;
        }
    }

    /**
     * Add an element to the beginning of the list
     * @param e the element to be placed at the beginning of the list
     */
    public void addFirst(E e) {
        Node<E> newNode = new Node<E>(e); // Create a new node
        newNode.next = head; // link the new node with the head
        head = newNode; // head points to the new node
        size++; // Increase list size

        if (tail == null) // the new node is the only node in list
        {
            tail = head;
        }

    }

    /**
     * Add an element to the end of the list
     * @param e the element to be added to the end of the list
     */
    public void addLast(E e) {
        Node<E> newNode = new Node<E>(e); // Create a new for element e
        if (tail == null) {
            head = tail = newNode; // The new node is the only node in list
        } else {
            tail.next = newNode; // Link the new with the last node
            tail = tail.next; // tail now points to the last node
        }
        size++; // Increase size
    }

    /**
     * Add a new element at the specified index in this list The index of the
     * head element is 0
     * @param index the intended position/index of the element to be added
     * @param e the element to be added
     */
    public void add(int index, E e) {
        if (index == 0) {
            addFirst(e);
        } else if (index >= size) {
            addLast(e);
        } else {
            Node<E> current = head;
            for (int i = 1; i < index; i++) {
                current = current.next;
            }
            Node<E> temp = current.next;
            current.next = new Node<E>(e);
            (current.next).next = temp;
            size++;
        }

    }

    /**
     * Remove the head node and return the object that is contained in the
     * removed node.
     * @return the removed element
     */
    public E removeFirst() {
        if (size == 0) {
            return null;
        } else {
            Node<E> temp = head;
            head = head.next;
            size--;
            if (head == null) {
                tail = null;
            }
            return temp.element;
        }
    }

    /**
     * Remove the last node and return the object that is contained in the
     * removed node.
     * @return removed element
     */
    public E removeLast() {
        if (size == 0) {
            return null;
        } else if (size == 1) {
            Node<E> temp = head;
            head = tail = null;
            size = 0;
            return temp.element;
        } else {
            Node<E> current = head;
            for (int i = 0; i < size - 2; i++) {
                current = current.next;
            }
            Node<E> temp = tail;
            tail = current;
            tail.next = null;
            size--;
            return temp.element;
        }
    }

    /**
     * Remove the element at the specified position in this list. Return the
     * element that was removed from the list.
     * @param index the position of the element to be removed
     * @return the removed element
     */
    @Override
    public E remove(int index) {
        if (index < 0 || index >= size) {
            return null;
        } else if (index == 0) {
            return removeFirst();
        } else if (index == size - 1) {
            return removeLast();
        } else {
            Node<E> previous = head;
            for (int i = 1; i < index; i++) {
                previous = previous.next;
            }
            Node<E> current = previous.next;
            previous.next = current.next;
            size--;
            return current.element;
        }
    }

    /**
     * Override toString() to return elements in the list
     * @return the string representation of the list
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("[");
        Node<E> current = head;
        for (int i = 0; i < size; i++) {
            result.append(current.element);
            current = current.next;
            if (current != null) {
                result.append(", "); // Separate two elements with a comma
            } else {
                result.append("]"); // Insert the closing ] in the string
            }
        }
        return result.toString();
    }

    /**
     * Clear the list
     */
    @Override
    public void clear() {
        head = tail = null;
    }

    /**
     * methods for lab 8 should be implemented here
     */
    private static class Node<E> {

        E element;
        Node<E> next;

        public Node(E element) {
            this.element = element;
        }
    }

    /**
     * Replace the element at the specified position in this list with the
     * specified element and returns the new set.
     *
     * @param index: location of element to be changed in list
     * @param e: desired element at index
     * @return the original element at the index
     */
    @Override
    public E set(int index, E e) {
        if (index < 0 || index >= size) {
            return null;
        } else {
            Node<E> previous = head;
            for (int i = 0; i < index; i++) {
                previous = previous.next;
            }
            E current = previous.element;
            previous.element = e;

            return current;
        }
    }
    /**
     * Determines if an element is within the list.
     * @param e: element to be compared
     * @param count: a variable to store the number of comparisons made to 
     * to determine if e is contained in the list
     * @return true if element e is found, false otherwise
     */
    @Override
    public boolean contains(E e, int[] count) {
        //boolean doesContain = false;
        count[0] = size;
        Node<E> previous = head;
        for (int i = 0; i < size; i++) {
            if (previous.element.equals(e)) {
                count[0] = i + 1;
                return true;
            } 
            previous = previous.next;
        }
        return false;
    }
    
    /**
     * Returns the element at a specific index in the list.
     * @param index: position of element to be returned
     * @return element at the index
     */
    @Override
    public E get(int index) {
        if (index < 0 || index >= size) {
            return null;
        } else {
            Node<E> previous = head;
            for (int i = 1; i < index; i++) {
                previous = previous.next;
            }
            Node<E> temp = previous.next;
            return temp.element;
        }
    }
    /**
     * Determines the index of a an element if it is in the list.
     * @param e: the element to be found
     * @return the value of the position of the element if it is found, -1 if 
     * not found
     */
    @Override
    public int indexOf(E e) {
        Node<E> previous = head;
        for (int i = 0; i < size; i++) {
            if (previous.element.equals(e)) {
                return i;
            }
            previous = previous.next;
        }
        return -1;
    }
    /**
     * Determines the last index of a an element if it is in the list.
     * @param e: the element to be found
     * @return the value of the position of the element if it is found, -1 if 
     * not found
     */
    @Override
    public int lastIndexOf(E e){
        Node<E> previous = head;
        int lastIndex = -1;
        for (int i = 0; i < size; i++) {
            if (previous.element.equals(e)) {
                lastIndex = i;
            }
            previous = previous.next;
        }
        return lastIndex;
    }

}

/*

(1) [America] 
(2) [Canada, America] 
(3) [Canada, America, Russia] 
(4) [Canada, America, Russia, France] 
(5) [Canada, America, Germany, Russia, France] 
(6) [Canada, America, Germany, Russia, France, Norway] 
(7) [Poland, Canada, America, Germany, Russia, France, Norway] 
(8) [Canada, America, Germany, Russia, France, Norway] 
(9) [Canada, America, Russia, France, Norway] 
(10) [Canada, America, Russia, France] 
(11) The list does not contain Germany 
(12) Invalid position 
(13) The list element France is at position 3 
(14) [India, Canada, America, Russia, France] 
(15) [India, Canada, America, Russia, France, America] 
(16) The list element America occurs last at 5 
(17) [India, Canada, America, Russia, France, China]
 */
