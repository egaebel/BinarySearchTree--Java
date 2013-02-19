package ds.BinarySearchTree;


public class BinaryNode<T extends Comparable<T>> {

    //~Constants----------------------------------------------


    //~Data Fields--------------------------------------------
    private BinaryNode<T> left;
    private BinaryNode<T> right;
    private T data;

    //~Constructors--------------------------------------------
    public BinaryNode() {
     
        data = null;
        left = null;
        right = null;
    }
    
    public BinaryNode(T data) {
        
        this.data = data;
        left = null;
        right = null;
    }

    //~Methods-------------------------------------------------
    /**
     * @return the left
     */
    public BinaryNode<T> getLeft() {

        return left;
    }

    /**
     * @param left the left to set
     */
    public void setLeft(BinaryNode<T> left) {
        
        this.left = left;
    }

    /**
     * @return the right
     */
    public BinaryNode<T> getRight() {

        return right;
    }

    /**
     * @param right the right to set
     */
    public void setRight(BinaryNode<T> right) {
        
        this.right = right;
    }

    /**
     * @return the data
     */
    public T getData() {

        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(T data) {
       
        this.data = data;
    }
    
    @Override
    public String toString() {
        
        return data.toString();
    }
}