import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
/*Name: Khamunetri Clark
Assignment: HW7
Description: Program to experiment with the speed of loading sorted and unsorted data into a binary search tree. Uses algorithm from S&W "Algorithms".
Course: CS 2050 Sec. 3*/
public class BST {
    Scanner userInput, reader;
    File input;
    long startWall, startCPU, stopWall, stopCPU;
    double wallTime, CPUTime;
    int smallestVal;
    ArrayList<Integer> loader;
    Comparator<Integer> cmp = (o1, o2) -> {
        if (o1 == o2) {
            return 0;
        }
        if (o1 > o2) {
            return 1;
        }

        if (o1 < o2) {
            return -1;
        }

        return 0;
    };

    private Node root; // root of BST

    private class Node {
        int depth, value; // value
        Node left, right; // links to subtrees
        int N; // # nodes in subtree rooted here

        public Node(int value) {
            this.value = value;
            this.depth = 0;
        }

        public Node (int value, Node left, Node right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }

        public Node insert (int x, Node t){
            if(t == null){
                t = new Node (x);
                return t;
            }
            else if(x < t.value) {
                t = new Node(x);
                t.left = insert(x, t.left);
                if (depth(t.left) - depth(t.right) == 2) {
                    if (x < t.left.value) t = RWLC(t);
                    else t = DRWLC(t);
                }
            }
            else if (x > t.value ){
                t = new Node(x);
                t.right = insert(x, t.right);
                    if (depth(t.right) - depth(t.left) == 2) {
                        if (x < t.left.value) t = RWRC(t);
                        else t = DRWRC(t);
                    }
            }
            else{
                System.out.println("Duplicate value.");
            }
                    t.depth = max(depth(t.left), depth(t.right)) + 1;

                return t;
            }
        public Node getLeft(){
            return this.left;
        }

        public Node getRight(){
            return this.right;
        }

        int getLeftMostValue() {
            if (left == null) return value;
            return getLeftMostValue();
        }

        void setValue(int x){
            this.value = x;
        }

        public boolean isLeaf() {
            return (left == null )&&(right == null);
        }

        public int depth(Node node) {
            if(node == null){
                return root.depth;
            }
            return node.depth;
        }

        public int max(int depth1, int depth2){
            if(depth1 > depth2) return  depth1;
            else return depth2;
        }

        public Node RWLC(Node y){
            Node x = y.left;
            y.left = x.right;
            x.right = y;

            y.depth = max(y.left.depth, y.right.depth) + 1;
            x.depth = max(x.left.depth, y.depth) + 1;

            return x;
        }

        public Node RWRC(Node y){
            Node x = y.right;
            y.right = x.left;
            x.left = y;

            y.depth = max(y.left.depth,y.right.depth) + 1;
            x.depth = max(x.right.depth, y.depth) +1;

            return x;
        }

        public Node DRWRC(Node y){
            y.right = RWLC(y.right);
            return RWRC(y);
        }

        public Node DRWLC(Node y){
            y.left = RWRC(y.left);
            return RWLC(y);
        }

    }





    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null) return 0;
        else return x.N;
    }

//    public int get(int searchValue) {
//        return get(root, searchValue);
//    }
//
//    private int get(Node x, int searchValue) { // Return value associated with value in the subtree rooted at x;
//// return null if value not present in subtree rooted at x.
//        if (x == null) {
//            System.out.println("Following value could not be found: ");
//            return searchValue;
//        }
//        if (searchValue < x.value) return get(x.left, searchValue);
//        else if (searchValue > x.value) return get(x.right, searchValue);
//        else return x.value;
//    }
//
//    public void put(int value) { // Search for value. Update value if found; grow table if new.
//        root = put(root, value);
//    }
//
//    private Node put(Node x, int insertValue) {
//// Change value’s value to val if value in subtree rooted at x.
//// Otherwise, add new node to subtree associating value with val.
//        if (x == null) {
//            Node y = new Node(insertValue);
//            y.depth = x.depth +1;
//            return y;
//        }
//        if (insertValue < x.value) x.left = put(x.left, insertValue);
//        else if (insertValue > x.value) x.right = put(x.right, insertValue);
//        else x.value = insertValue;
//        x.N = size(x.left) + size(x.right) + 1;
//        return x;
//    }

    public void getFile() {
        userInput = new Scanner(System.in);
        System.out.println("Please type the name of your file: ");
        while (this.input == null) {
            try {
                input = new File(userInput.next());
                checkFile(input);

            } catch (Exception e) {
            }
        }
    }

    void checkFile(File in) {
        try {
            Scanner stringPatrol = new Scanner(in);
            System.out.println("File \"" + in + "\" entered.");
            if (!in.exists()) {
                System.out.println("Error: File not found");
                this.input = null;
                getFile();
                userInput.close();
            }
            if (!stringPatrol.hasNextInt() && in.length() <= 0) {
                System.out.println("Error: Empty file provided");
                this.input = null;
                getFile();
                userInput.close();
            } else if (!stringPatrol.hasNextInt()) {
                System.out.println("Error: File must contain integers");
                this.input = null;
                getFile();
                userInput.close();
            }
        } catch (Exception e) {
            System.out.println("Invalid entry");
            input = null;
            getFile();
        }
    }

    void loadFileUnsorted() {
        try {
            reader = new Scanner(input);
            loader = new ArrayList<>();
            while(reader.hasNextInt()){
                loader.add(reader.nextInt());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    void loadFileSorted() {
        try {
            reader = new Scanner(input);
            loader = new ArrayList<>();
            while(reader.hasNextInt()){
                loader.add(reader.nextInt());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        loader.sort(cmp);
    }

    void loadTree() {
        root = new Node(loader.get(0));
        System.out.println("Loading tree...");
        for(int i = 1; i < loader.size(); i++){
            root.insert(loader.get(i), root);
            System.out.println("root value: " + root.value);
            System.out.println("depth " + root.depth);

                System.out.println("Leftmost: " +root.getLeftMostValue());

        }
    }

    public static void main(String[] args) {
        BST tree = new BST();
        tree.getFile();
        tree.loadFileUnsorted();
        tree.loadTree();
    }
}