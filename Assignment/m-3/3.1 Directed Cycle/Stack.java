import java.util.Iterator;
import java.util.NoSuchElementException;
/**.
 * List of .
 *
 * @param      <Item>  The item
 */
public class Stack<Item> implements Iterable<Item> {
    /**.
     * { var_description }
     */
    private int size;          // size of the stack
    /**.
     * { var_description }
     */
    private Node first;     // top of stack

    // helper linked list class
    /**..
     * Class for node.
     */
    private class Node {
        /**.
         * { var_description }
         */
        private Item item;
        /**.
         * { var_description }
         */
        private Node next;
    }

    /**.
      * Create an empty stack.
      * Complexity: O(1)
      */
    public Stack() {
        first = null;
        size = 0;
    }

    /**.
      * Is the stack empty?
      * Complexity: O(1)
      * @return     True if empty, False otherwise.
      */
    public boolean isEmpty() {
        return first == null;
    }

    /**.
      * Return the number of items in the stack.
      * Complexity: O(1)
      * @return     { description_of_the_return_value }
      */
    public int size() {
        return size;
    }

    /**.
      * Add the item to the stack.
      * Complexity: O(1)
      * @param      item  The item
      */
    public void push(final Item item) {
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        size++;
    }

    /**.
      * Delete and return the item most recently added to the stack. Throw an
      * exception if no such item exists because the stack is empty.
      * Complexity: O(1)
      * @return     { description_of_the_return_value }
      */
    public Item pop() {
        if (isEmpty()) {
            throw new RuntimeException("Stack underflow");
        }
        Item item = first.item;        // save item to return
        first = first.next;            // delete first node
        size--;
        return item;                   // return the saved item
    }


    /**.
      * Return the item most recently added to the stack. Throw an exception if
      * no such item exists because the stack is empty.
      * Complexity: O(1)
      * @return     { description_of_the_return_value }
      */
    public Item peek() {
        if (isEmpty()) {
            throw new RuntimeException("Stack underflow");
        }
        return first.item;
    }

    /**.
      * Return string representation.
      * Complexity: O(size)
      * @return     String representation of the object.
      */
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Item item : this) {
            s.append(item + " ");
        }
        return s.toString();
    }


    /**.
      * Return an iterator to the stack that iterates through the items in
      * LIFO order.
      * Complexity: O(size)
      * @return     { description_of_the_return_value }
      */
    public Iterator<Item> iterator()  {
        return new ListIterator();
    }
    // an iterator, doesn't implement remove() since it's optional

    /**.
     * Class for list iterator.
     */
    private class ListIterator implements Iterator<Item> {
        /**.
         * { var_description }
         */
        private Node current = first;
        /**.
         * Determines if it has next.
         *
         * @return     True if has next, False otherwise.
         */
        public boolean hasNext()  {
            return current != null;
        }
        /**.
         * { function_description }
         */
        public void remove()      {
            throw new UnsupportedOperationException();
        }

        /**.
         * { function_description }
         *
         * @return     { description_of_the_return_value }
         */
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
}
