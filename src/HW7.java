import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

class Node {
    int value, depth;
    Node left, right;

    Node(int d) {
        value = d;
        depth = 1;
    }
}

class AVLTree {
    Scanner userInput, reader;
    File input, search;
    ArrayList<Integer> loader;
    private final double MILSEC = 1000;
    private final double NANOSEC = 1000000000;
    long startWall, startCPU, stopWall, stopCPU;
    int numElements;
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
    Node root;

    // A utility function to get depth of the tree
    int depth(Node N) {
        if (N == null)
            return 0;

        return N.depth;
    }

    int max(int a, int b) {
        return (a > b) ? a : b; //"tertiary if/else statement", where ? = if and : = else
    }
    Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        // Perform rotation
        x.right = y;
        y.left = T2;

        // Update depths
        y.depth = max(depth(y.left), depth(y.right)) + 1;
        x.depth = max(depth(x.left), depth(x.right)) + 1;

        // Return new root
        return x;
    }
    Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        // Perform rotation
        y.left = x;
        x.right = T2;

        //  Update depths
        x.depth = max(depth(x.left), depth(x.right)) + 1;
        y.depth = max(depth(y.left), depth(y.right)) + 1;

        // Return new root
        return y;
    }

    // Get Balance factor of node N
    int getBalance(Node N) {
        if (N == null)
            return 0;

        return depth(N.left) - depth(N.right);
    }

    Node insert(Node node, int value) {
 
        /* 1.  Perform the normal BST insertion */
        if (node == null)
            return (new Node(value));

        if (value < node.value)
            node.left = insert(node.left, value);
        else if (value > node.value)
            node.right = insert(node.right, value);
        else // Duplicate values not allowed
            return node;
 
        /* 2. Update depth of this ancestor node */
        node.depth = 1 + max(depth(node.left),
                depth(node.right));
 
        /* 3. Get the balance factor of this ancestor
              node to check whether this node became
              unbalanced */
        int balance = getBalance(node);

        // If this node becomes unbalanced
        if (balance > 1 && value < node.left.value) //rotate with left child
            return rightRotate(node);


        if (balance < -1 && value > node.right.value)//rotate with right child
            return leftRotate(node);


        if (balance > 1 && value > node.left.value) { //double rotate with left child
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        if (balance < -1 && value < node.right.value) { //double rotate with right child
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
 
        // return the (unchanged) node pointer
        return node;
    }

    public Node search(Node root, int value)
    {
        // Base Cases: root is null or value is present at root
        if (root==null) {
            System.out.println("Value: " + value + "\nSearch miss");
            return root;
        }
        else if(root.value==value) {
            System.out.println("Value: " + root.value + "\nSearch hit at height " + root.depth );
            return root;
        }

        // val is greater than root's value
        if (root.value > value) {
            return search(root.left, value);
        }

        // val is less than root's value
        return search(root.right, value);
    }

    void preOrder(Node node) {

        if (node != null) {
            System.out.print(node.value + " ");
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    int getLeftMostValue() {
        if (this.root.left == null) return this.root.value;
        return getLeftMostValue();
    }

    public void getFile() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            while (this.input == null) {
                System.out.println("Please enter the name of your INPUT input file.");
                input = new File(br.readLine());
                checkFile(input);
            }
        }catch (Exception e) {
            }
    }

    public void getSearchFile() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            try {
                while (this.search == null) {
                    System.out.println("Please enter the name of your SEARCH input file.");
                    search = new File(br.readLine());
                    checkSearchFile(search);
                }
                br.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
    }

    public void searchByFile(){
        try{
            Scanner searchReader = new Scanner(new BufferedReader(new FileReader(search)));
            while(searchReader.hasNextInt()){
                search(this.root, searchReader.nextInt());
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
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
            stringPatrol.close();
        } catch (Exception e) {
            System.out.println("Invalid entry");
            input = null;
            getFile();
        }
    }

    void checkSearchFile(File in) {
        try {
            Scanner stringPatrol = new Scanner(in);
            System.out.println("File \"" + in + "\" entered.");
            if (!in.exists()) {
                System.out.println("Error: File not found");
                this.search = null;
                getSearchFile();
                userInput.close();
            }
            if (!stringPatrol.hasNextInt() && in.length() <= 0) {
                System.out.println("Error: Empty file provided");
                this.search = null;
                getSearchFile();
                userInput.close();
            } else if (!stringPatrol.hasNextInt()) {
                System.out.println("Error: File must contain integers");
                this.search = null;
                getSearchFile();
                userInput.close();
            }
            stringPatrol.close();
        } catch (Exception e) {
            System.out.println("Invalid entry");
            search = null;
            getSearchFile();
        }
    }

    void loadFileUnsorted() {
        try {
            reader = new Scanner(new BufferedReader(new FileReader(input)));
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
            reader = new Scanner(new BufferedReader(new FileReader(input)));
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
        System.out.println("Loading tree...");
        numElements = 0;
        startWall = System.currentTimeMillis();
        startCPU = System.nanoTime();
        for(int i = 0; i < loader.size(); i++){
            this.root = this.insert(this.root,loader.get(i));
            numElements++;

        }
        stopWall = System.currentTimeMillis();
        stopCPU = System.nanoTime();
    }

    public static void main(String[] args) {
        AVLTree tree = new AVLTree();
        tree.getFile();
        tree.getSearchFile();
        tree.loadFileUnsorted();
        tree.loadTree();
//        tree.preOrder(tree.root);
//        for(int i = 0; i < tree.loader.size(); i++){
//            System.out.println(tree.loader.get(i));
//        }
        tree.searchByFile();
        AVLTree tree2 = new AVLTree();
        tree2.getFile();
        tree2.getSearchFile();
        tree2.loadFileSorted();
        tree2.loadTree();
//        tree.preOrder(tree.root);
//        for(int i = 0; i < tree.loader.size(); i++){
//            System.out.println(tree.loader.get(i));
//        }
        tree2.searchByFile();
    }
}