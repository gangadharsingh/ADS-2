import java.util.Iterator;
import java.util.NoSuchElementException;
/**.
 * Class for bag.
 *
 * @param      <Item>  The item
 */
public class Bag<Item> implements Iterable<Item> {
	/**.
	 * { var_description }
	 */
    private int N;         // number of elements in bag
    /**.
     * { var_description }
     */
    private Node first;    // beginning of bag
    /**.
     * Class for node.
     */
    private class Node {
        private Item item;
        private Node next;
    }

   /**.
     * Create an empty stack.
     */
    public Bag() {
        first = null;
        N = 0;
    }

   /**.
     * Is the BAG empty?
     */
    public boolean isEmpty() {
        return first == null;
    }

   /**.
     * Return the number of items in the bag.
     */
    public int size() {
        return N;
    }

   /**.
     * Add the item to the bag.
     */
    public void add(final Item item) {
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        N++;
    }


   /**.
     * Return an iterator that iterates over the items in the bag.
     */
    public Iterator<Item> iterator()  {
        return new ListIterator();  
    }

    // an iterator, doesn't implement remove() since it's optional
    /**.
     * Class for list iterator.
     */
    private class ListIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext()  { return current != null;                     }
        public void remove()      { throw new UnsupportedOperationException();  }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next; 
            return item;
        }
    }
}
