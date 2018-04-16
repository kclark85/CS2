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
        private int key; // key
        private Node left, right; // links to subtrees
        private int N; // # nodes in subtree rooted here

        public Node(int key, int N) {
            this.key = key;
            this.N = N;
        }
    }

    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null) return 0;
        else return x.N;
    }

    public int get(int searchKey) {
        return get(root, searchKey);
    }

    private int get(Node x, int searchKey) { // Return value associated with key in the subtree rooted at x;
// return null if key not present in subtree rooted at x.
        if (x == null) {
            System.out.println("Following key could not be found: ");
            return searchKey;
        }
        if (searchKey < x.key) return get(x.left, searchKey);
        else if (searchKey > x.key) return get(x.right, searchKey);
        else return x.key;
    }

    public void put(int key) { // Search for key. Update value if found; grow table if new.
        root = put(root, key);
    }

    private Node put(Node x, int insertKey) {
// Change key’s value to val if key in subtree rooted at x.
// Otherwise, add new node to subtree associating key with val.
        if (x == null) return new Node(insertKey, 1);
        if (insertKey < x.key) x.left = put(x.left, insertKey);
        else if (insertKey > x.key) x.right = put(x.right, insertKey);
        else x.key = insertKey;
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

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

    }

    public static void main(String[] args) {

    }
}