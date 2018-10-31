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
    private int N;          // size of the stack
    /**.
     * { var_description }
     */
    private Node first;     // top of stack

    // helper linked list class
    /**..
     * Class for node.
     */
    private class Node {
        private Item item;
        private Node next;
    }

    /**.
      * Create an empty stack.
      */
    public Stack() {
        first = null;
        N = 0;
    }

    /**.
      * Is the stack empty?
      */
    public boolean isEmpty() {
        return first == null;
    }

    /**.
      * Return the number of items in the stack.
      */
    public int size() {
        return N;
    }

    /**.
      * Add the item to the stack.
      */
    public void push(final Item item) {
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        N++;
    }

    /**.
      * Delete and return the item most recently added to the stack.
      * Throw an exception if no such item exists because the stack is empty.
      */
    public Item pop() {
        if (isEmpty()) throw new RuntimeException("Stack underflow");
        Item item = first.item;        // save item to return
        first = first.next;            // delete first node
        N--;
        return item;                   // return the saved item
    }


    /**.
      * Return the item most recently added to the stack.
      * Throw an exception if no such item exists because the stack is empty.
      */
    public Item peek() {
        if (isEmpty()) throw new RuntimeException("Stack underflow");
        return first.item;
    }

    /**.
      * Return string representation.
      */
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Item item : this)
            s.append(item + " ");
        return s.toString();
    }


    /**.
      * Return an iterator to the stack that iterates through the items in LIFO order.
      */
    public Iterator<Item> iterator()  { return new ListIterator();  }
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
