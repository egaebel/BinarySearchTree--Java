package ds.BinarySearchTree;

/**
 * BinarySearchTree<T> which follows the operations set forth in the BinarySearchTreeInterface<T>.
 * 
 * @author Ethan Gaebel (egaebel)
 *
 * @param <T>
 */
public class BinarySearchTree<T extends Comparable<T>> implements BinarySearchTreeInterface<T> {

    //~Data Fields--------------------------------------------
    /**
     * The root node of the BinarySearchTree<T>.
     */
    private BinaryNode<T> root;
    /**
     * The size of the BinarySearchTree<T>.
     */
    private int size;

    //~Constructors--------------------------------------------
    /**
     * Default constructor of the BinarySearchTree<T>.
     */
    public BinarySearchTree() {
        
        root = null;
        size = 0;
    }
    
    /**
     * Constructor of the BinarySearchTree<T> which takes an initial data element as an argument.
     * 
     * @param data the first data element in the BinarySearchTree<T>.
     */
    public BinarySearchTree(T data) {
        
        root = new BinaryNode<T>(data);
        size = 1;
    }

    //~Methods-------------------------------------------------
    @Override
    public void insert(T element) {

        if (element != null) {
         
            BinaryNode<T> node = new BinaryNode<T>(element);
            
            if (root == null) {
                
                root = node;
            }
            else {
                
                insert(root, node);
            }
            
            size++;
        }
    }
    
    /**
     * Main recursive method for the insert operation.
     * 
     * @param subRoot the root node of the current sub-tree.
     * @param node the node to be inserted into the BinarySearchTree<T>.
     */
    private void insert(BinaryNode<T> subRoot, BinaryNode<T> node) {
        
        //node < root
        if (node.getData().compareTo(subRoot.getData()) < 0) {
            
            if (subRoot.getLeft() != null) {
                insert(subRoot.getLeft(), node);
            }
            else {
                subRoot.setLeft(node);
            }
        }
        //node > root
        else if (node.getData().compareTo(subRoot.getData()) > 0) {
            
            if (subRoot.getRight() != null) {
                insert(subRoot.getRight(), node);
            }
            else {
                subRoot.setRight(node);
            }
        }
        //node == root
        else {
            
            if (subRoot.getRight() != null) {
                insert(subRoot.getRight(), node);
            }
            else {
                subRoot.setRight(node);
            }
        }
    }

    @Override
    public boolean remove(T element) {

        if (element != null && root != null) {
         
            root = remove(root, element);
            size--;
            
            return true;
        }
        
        return false;
    }

    /**
     * Main recursive method for the remove operation.
     * 
     * @param subRoot the root node of the current sub-tree.
     * @param element the element to be removed from the BinarySearchTree<T>.
     * @return the removed BinaryNode<T>, or null if a node containing element wasn't found.
     */
    private BinaryNode<T> remove(BinaryNode<T> subRoot, T element) {
        
        BinaryNode<T> node = subRoot;
        
        //element < subRoot
        if (subRoot.getLeft() != null && element.compareTo(subRoot.getData()) < 0) {
            
            subRoot.setLeft(remove(subRoot.getLeft(), element));
        }
        //element > subRoot
        else if (subRoot.getRight() != null && element.compareTo(subRoot.getData()) > 0) {
            
            subRoot.setRight(remove(subRoot.getRight(), element));
        }
        //element == subRoot
        else {
            
            //node has two children
            if (subRoot.getLeft() != null && subRoot.getRight() != null) {
                
                BinaryNode<T> minRight = findMin(subRoot.getRight());
                
                subRoot.setRight(remove(subRoot.getRight(), minRight.getData()));
                subRoot.setData(minRight.getData());
            }
            //node has one element on the left
            else if (subRoot.getLeft() != null) {
                
                node = subRoot.getLeft();
            }
            //node has one element on the right, or NO elements
            else {
                
                node = subRoot.getRight();
            }
        }
        
        return node;
    }

    @Override
    public T find(T element) {

        if (element != null && root != null) {
        
            if (root.getData().equals(element)) {
                
                return root.getData();
            }
            else {
                
                BinaryNode<T> node = find(root, element);
                if (node != null) {
                    
                    return node.getData();
                }
            }
        }
        
        return null;
    }
    
    /**
     * Main recursive method for the find operation.
     * 
     * @param subRoot the root of the current sub-tree.
     * @param element the element to find.
     * @return the BinaryNode<T> holding the sought element, or null if it isn't found.
     */
    private BinaryNode<T> find(BinaryNode<T> subRoot, T element) {
        
        //element < subRoot
        if (element.compareTo(subRoot.getData()) < 0) {
         
            if (subRoot.getLeft() != null) {
                return find(subRoot.getLeft(), element);
            }
            else {
                return null;
            }
        }
        //element > subRoot
        else if(element.compareTo(subRoot.getData()) > 0) {
            
            if (subRoot.getRight() != null) {
                return find(subRoot.getRight(), element);
            }
            else {
                return null;
            }
        }
        else {
        
            return subRoot;
        }
    }

    @Override
    public T findMin() {

        if (root != null) {
            return findMin(root).getData();
        }
        return null;
    }
    
    /**
     * Main recursive method for the findMin operation.
     * 
     * @param subRoot the root of the current sub-tree.
     * @return the minimum node in the tree.
     */
    private BinaryNode<T> findMin(BinaryNode<T> subRoot) {
        
        if (subRoot.getLeft() != null) {
            
            return findMin(subRoot.getLeft());
        }
        else {
            
            return subRoot;
        }
    }

    @Override
    public T findMax() {

        if (root != null) {
            return findMax(root).getData();
        }
        return null;
    }
    
    /**
     * Main recursive method for the findMax operation.
     * 
     * @param subRoot the root of the current sub-tree.
     * @return the maximum node of the tree.
     */
    private BinaryNode<T> findMax(BinaryNode<T> subRoot) {
        
        if (subRoot.getRight() != null) {
            
            return findMax(subRoot.getRight());
        }
        else {
            
            return subRoot;
        }
    }

    @Override
    public T findK(int k) {

        if (k > -1 && k < size && root != null) {
            
            int currentIndex = size - countSubTree(root.getLeft()) - 2;

            //the root is k
            if (currentIndex == k) {
                
                return root.getData();
            }
            //k is an index larger than the currentIndex
            else if (currentIndex < k) {
                
                int increment = root.getLeft() != null ? countSubTree(root.getRight().getLeft()) + 1 : 1;
                return findK(root.getRight(), currentIndex + increment, k);
            }
            //k is an index smaller than the currentIndex
            else {
            
                int decrement = root.getRight() != null ? countSubTree(root.getLeft().getRight()) + 1 : 1;
                return findK(root.getLeft(), currentIndex - decrement, k);
            }
        }
        
        return null;
    }
    
    /**
     * Main recursive method for the findK operation.
     * 
     * @param subRoot the root of the current sub-tree.
     * @param rootIndex the index of subRoot.
     * @param k the sought index.
     * @return the element at the kth position in the BinarySearchTree<T>.
     */
    private T findK(BinaryNode<T> subRoot, int rootIndex, int k) {
        
        int currentIndex = rootIndex;
        
        //if this node is k
        if (currentIndex == k) {

            return subRoot.getData();
        }
        //k is an index larger than the currentIndex
        else if (currentIndex < k) {
            
            int increment = subRoot.getLeft() != null ? countSubTree(subRoot.getRight().getLeft()) + 1 : 1;
            return findK(subRoot.getRight(), currentIndex + increment, k);
        }
        //k is an index smaller than the currentIndex
        else {
            
            int decrement = subRoot.getRight() != null ? countSubTree(subRoot.getLeft().getRight()) + 1 : 1;
            return findK(subRoot.getLeft(), currentIndex - decrement, k);
        }
    }
    
    /**
     * Counts the elements in the tree. Useless, because size is kept track of.
     * Used to test countSubTree without making it public.
     * 
     * @return the size of the tree.
     */
    public int countTree() {
        
        return countSubTree(root);
    }
    
    /**
     * Counts the number of elements in the subtree that has subRoot as a root
     * INCLUDING SUBROOT.
     * 
     * @param subRoot the root of the sub-tree.
     * @return the number of elements in the sub-tree.
     */
    private int countSubTree(BinaryNode<T> subRoot) {
        
        int count = 0;
        
        if (subRoot != null) {
         
            if (subRoot.getRight() != null) {
                
                count += countSubTree(subRoot.getRight());
            }
            
            count++;
            
            if (subRoot.getLeft() != null) {
                
                count += countSubTree(subRoot.getLeft());
            }
        }
        
        return count;
    }

    @Override
    public boolean isEmpty() {

        return root == null;
    }

    @Override
    public int size() {

        return size;
    }

    @Override
    public void makeEmpty() {

        root = null;
        size = 0;
    }

    @Override
    public String postOrderTraversal() {

        StringBuilder build = new StringBuilder();
        if (root != null) {
            postOrderTraversal(root, build, "");
        }
        else {
            build.append("|-");
        }
        
        return build.toString();
    }
    
    /**
     * Main recursive method for the post-order traversal operation.
     * 
     * @param subRoot the root of the current sub-tree.
     * @param build the StringBuilder object used to hold the ultimate return string.
     * @param branch the branch String which denotes the depth that an element is at in denominations of "****".
     */
    private void postOrderTraversal(BinaryNode<T> subRoot, StringBuilder build, String branch) {
        
        if (subRoot.getRight() != null) {
            
            inOrderTraversal(subRoot.getRight(), build, branch + "****");
        }
        
        if (subRoot.getLeft() != null) {
            
            inOrderTraversal(subRoot.getLeft(), build, branch + "****");
        }
        
        build.append("\n").append(branch).append(subRoot.getData().toString());
    }

    @Override
    public String inOrderTraversal() {

        StringBuilder build = new StringBuilder();
        if (root != null) {
            inOrderTraversal(root, build, "");
        }
        else {
            build.append("|-");
        }
        
        return build.toString();
    }
    
    /**
     * Main recursive method for the in-order traversal operation.
     * 
     * @param subRoot the root of the current sub-tree.
     * @param build the StringBuilder object used to hold the ultimate return string.
     * @param branch the branch String which denotes the depth that an element is at in denominations of "****".
     */
    private void inOrderTraversal(BinaryNode<T> subRoot, StringBuilder build, String branch) {
        
        if (subRoot.getRight() != null) {
            
            inOrderTraversal(subRoot.getRight(), build, branch + "****");
        }
        
        build.append("\n").append(branch).append(subRoot.getData().toString());
        
        if (subRoot.getLeft() != null) {
            
            inOrderTraversal(subRoot.getLeft(), build, branch + "****");
        }
    }

    @Override
    public String preOrderTraversal() {

        StringBuilder build = new StringBuilder();
        if (root != null) {
            preOrderTraversal(root, build, "");
        }
        else {
            build.append("|-");
        }
        
        return build.toString();
    }
    
    /**
     * Main recursive method for the pre-order traversal operation.
     * 
     * @param subRoot the root of the current sub-tree.
     * @param build the StringBuilder object used to hold the ultimate return string.
     * @param branch the branch String which denotes the depth that an element is at in denominations of "****".
     */
    private void preOrderTraversal(BinaryNode<T> subRoot, StringBuilder build, String branch) {
        
        build.append("\n").append(branch).append(subRoot.getData().toString());
        
        if (subRoot.getRight() != null) {
            
            inOrderTraversal(subRoot.getRight(), build, branch + "****");
        }
        
        if (subRoot.getLeft() != null) {
            
            inOrderTraversal(subRoot.getLeft(), build, branch + "****");
        }
    }
}