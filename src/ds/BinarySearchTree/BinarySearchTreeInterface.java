package ds.BinarySearchTree;

/**
 * Interface for a BinarySearchTree with standard insert, find, removal operations.
 *  
 * @author Ethan Gaebel (egaebel)
 *
 */
public interface BinarySearchTreeInterface<T extends Comparable<T>> {

    //~Methods-------------------------------------------------
    /**
     * Inserts the passed element into the BinarySearchTree<T>.
     * 
     * @param element the element to insert.
     */
    public void insert(T element);
    
    /**
     * Remove the passed in element from the BinarySearchTree<T>.
     * 
     * @param element the element to remove.
     * @return true if successful, false otherwise 
     *          (if the element was invalid, or is not in the BinarySearchTree<T>.).
     */
    public boolean remove(T element);
    
    /**
     * Find the passed in element in the BinarySearchTree<T>.
     * Return null if it isn't present.
     * 
     * @param element the element to find.
     * @return the element found, or null if it was not found.
     */
    public T find(T element);
    
    /**
     * Get the minimum item in the BinarySearchTree<T>.
     * 
     * @return the minimum item.
     */
    public T findMin();
    
    /**
     * Get the maximum item in the BinarySearchTree<T>.
     * 
     * @return the maximum item.
     */
    public T findMax();
    
    /**
     * Get the kth indexed item from the BinarySearchTree<T>.
     * 
     * @param k the index to get the element at.
     * @return the element at position k, or null if the key is invalid.
     */
    public T findK(int k);
    
    /**
     * Indicates whether the BinarySearchTree<T> is empty.
     * 
     * @return true if empty, false if it isn't.
     */
    public boolean isEmpty();
    
    /**
     * Gets the size of the BinarySearchTree<T>.
     * 
     * @return the size of the BinarySearchTree<T>.
     */
    public int size();
    
    /**
     * Removes all of the elements from the BinarySearchTree<T>.
     */
    public void makeEmpty();
    
    /**
     * Traverses the BinarySearchTree<T> in post order, prints the string.
     * 
     * @return the String representation of the tree in post order.
     */
    public String postOrderTraversal();
    
    /**
     * Traverses the BinarySearchTree<T> in in order, prints the string.
     * 
     * @return the String representation of the tree in in order.
     */
    public String inOrderTraversal();
    
    /**
     * Traverses the BinarySearchTree<T> in pre order, prints the string.
     * 
     * @return the String representation of the tree in pre order.
     */
    public String preOrderTraversal();
}
