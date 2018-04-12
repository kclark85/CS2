/*
Name: Khamunetri Clark
Assignment: HW4
Description: Program to compare the speeds to load several different types of abstract data types. By interacting with a
basic user interface, users can search through the lists by word or by key values, and can also print the timing
results
Course: CS 2050 Sec. 3
 */
import java.io.*;
import java.text.DecimalFormat;
import java.util.*;

public class P4Program {
    BufferedWriter writer;
    DecimalFormat realFormatter;
    LinkedList<Lelement> stackList, queueList, temp;
    Stack<Lelement> jStack;
    File input;
    Scanner userInput;
    StackNode stack;
    QueueNode queue;
    DoubleNode DLL;
    String query;
    int floatingSize, totalSize;
    private final double MILSEC = 1000;
    private final double NANOSEC = 1000000000;
    long startWall, startCPU, stopWall, stopCPU;
    double nodeStackWall, nodeStackCPU, nodeQueueWall, nodeQueueCPU, listStackWall, listStackCPU,
            listQueueWall, listQueueCPU, doubleListWall, doubleListCPU, jStackWall, jStackCPU;

    class Lelement {
        String word;
        int key = floatingSize;
    }

    class Node {
        Lelement data;
        Node next;
        Node prev;

        public Node() {

        }

        public Node(Lelement element) {
            this.data = element;
            this.next = null;
        }
    }

    class StackNode {
        Node head;

        public StackNode() {

        }

        public StackNode(Node first) {
            head = first;
        }

        void push(Node node) {
            if (this.head == null) {
                this.head = node;
            }
            Node first = node;
            first.next = head;
            this.head = first;
        }

        Node pop() {
            Node temp = new Node(head.data);
            if (head.next == null) {
                System.out.println("Error: Stack underflow");
                return null;
            }
            head = head.next;
            return temp;
        }

        void peek() {
            if (head == null) {
                System.out.println("Error: Stack is empty");
                return;
            }
            System.out.println("Stack(Node) \nDATA: " + head.data.word + "\nKEY: " + head.data.key);
            System.out.println();
        }
    }

    class QueueNode {
        Node first, last;

        public QueueNode() {

        }

        void enqueue(Node node) {

            Node temp = new Node(node.data);
            if (first == null) {
                first = last = temp;
            } else {
                last.next = temp;
                last = temp;
            }
            last.next = first;
        }

        Node dequeue() {
            Node temp = new Node();
            if (first.data == null) {
                throw new NoSuchElementException();
            } else if (first == last) {
                temp.data = first.data;
                first = last = null;
            } else {
                temp = first;
                first = first.next;
                last.next = first;
            }
            return temp;
        }

        Lelement getFirstElement() {
            return this.first.data;
        }

        Lelement getLastElement() {
            return this.last.data;
        }

    }

    class DoubleNode {
        Node post, pre, ref, search; //post and pre "guard" first and last value

        public DoubleNode() {
            pre = new Node();
            post = new Node();
            search = new Node();
            pre.next = post;
            post.prev = pre;

        }

        void add(Node node) {
            if (this.ref == null) {
                ref = node;
                search = ref;
            }
            ref = post.prev;
            Node add = new Node(node.data);
            add.next = post;
            add.prev = ref;
            post.prev = add;
            ref.next = add;
            search = ref.next;
            search.prev = ref;

        }

        void lookNext() {
            if (search.next == post || search.prev == pre) {
                search = ref;
                return;//brings search back to beginning upon reaching end
            }
            search = search.prev;
            return;
        }
    }

    public P4Program() {
        try {
            writer = new BufferedWriter(new FileWriter("P4Output.txt"));
            writer.write("Khamunetri Clark");
            writer.newLine();
            writer.newLine();
            writer.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void loadLists() {
        totalSize = 0;
        if (!input.exists()) {
            System.out.println("Error: No file input, returning to menu...");
            System.out.println("-------------------------------------");
            this.run();
        }

        fillJavaStack();
        fillNodeStack();
        fillNodeQueue();
        fillLinkedListStack();
        fillLinkedListQueue();
        fillDoublyLinkedList();
        System.out.println("Lists loaded, returning to menu...");
        System.out.println("----------------------------------");
        this.run();

    }

    public void fillJavaStack() {
        jStack = new Stack<>();
        if (!input.exists()) {
            System.out.println("Error: Input file not found, please select another input file." + "\nReturning to menu...");
            System.out.println("-------------------------------------");
            this.run();
        }
        try {
            Scanner fileScanner = new Scanner(new FileReader(input));
            startWall = System.currentTimeMillis();
            startCPU = System.nanoTime();
            while (fileScanner.hasNext()) {
                Lelement newElement = new Lelement();
                newElement.word = fileScanner.next();
                newElement.key = floatingSize;
                jStack.push(newElement);
                floatingSize++;
            }
            fileScanner.close();
            stopWall = System.currentTimeMillis();
            stopCPU = System.nanoTime();
            jStackWall = (stopWall - startWall) / MILSEC;
            jStackCPU = (stopCPU - startCPU) / NANOSEC;
            floatingSize = 0;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void fillNodeStack() {
        try {
            Scanner fileScanner = new Scanner(new FileReader(input));
            stack = new StackNode();
            startWall = System.currentTimeMillis();
            startCPU = System.nanoTime();
            while (fileScanner.hasNext()) {
                Lelement newElement = new Lelement();
                newElement.word = fileScanner.next();
                newElement.key = floatingSize;
                Node newNode = new Node(newElement);
                stack.push(newNode);
                floatingSize++;
            }
            fileScanner.close();
            stopWall = System.currentTimeMillis();
            stopCPU = System.nanoTime();
            nodeStackWall = ( stopWall - startWall) / MILSEC;
            nodeStackCPU = ( stopCPU - startCPU) / NANOSEC;
            totalSize = floatingSize;
            floatingSize = 0; //reset the size for the next fill operation
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }


    public void fillNodeQueue() {
        try {
            Scanner fileScanner = new Scanner(new FileReader(input));
            queue = new QueueNode();
            startWall = System.currentTimeMillis();
            startCPU = System.nanoTime();
            while (fileScanner.hasNext()) {
                Lelement newElement = new Lelement();
                newElement.word = fileScanner.next();
                newElement.key = floatingSize;
                Node newNode = new Node(newElement);
                queue.enqueue(newNode);
                floatingSize++;
            }
            fileScanner.close();
            stopWall = System.currentTimeMillis();
            stopCPU = System.nanoTime();
            nodeQueueWall = (stopWall - startWall) / MILSEC;
            nodeQueueCPU = (stopCPU - startCPU) / NANOSEC;
            floatingSize = 0;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }


    public void fillLinkedListStack() {
        stackList = new LinkedList<>();
        try {
            Scanner fileScanner = new Scanner(new FileReader(input));
            startWall = System.currentTimeMillis();
            startCPU = System.nanoTime();
            while (fileScanner.hasNext()) {
                Lelement newElement = new Lelement();
                newElement.word = fileScanner.next();
                newElement.key = floatingSize;
                stackList.push(newElement);
                floatingSize++;
            }
            fileScanner.close();
            stopWall = System.currentTimeMillis();
            stopCPU = System.nanoTime();
            listStackWall = (stopWall - startWall) / MILSEC;
            listStackCPU = (stopCPU - startCPU) / NANOSEC;
            floatingSize = 0;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public void fillLinkedListQueue() {
        queueList = new LinkedList<>();
        try {
            Scanner fileScanner = new Scanner(new FileReader(input));
            startWall = System.currentTimeMillis();
            startCPU = System.nanoTime();
            while (fileScanner.hasNext()) {
                Lelement newElement = new Lelement();
                newElement.word = fileScanner.next();
                newElement.key = floatingSize;
                this.queueList.add(newElement);
                floatingSize++;
            }
            fileScanner.close();
            stopWall = System.currentTimeMillis();
            stopCPU = System.nanoTime();
//            }
            listQueueWall = (stopWall - startWall) / MILSEC;
            listQueueCPU = (stopCPU - startCPU) / NANOSEC;
            floatingSize = 0;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void fillDoublyLinkedList() {
        try {
            Scanner fileScanner = new Scanner(new FileReader(input));
            DLL = new DoubleNode();
            startWall = System.currentTimeMillis();
            startCPU = System.nanoTime();
            while (fileScanner.hasNext()) {
                Lelement newElement = new Lelement();
                newElement.word = fileScanner.next();
                newElement.key = floatingSize;
                Node newNode = new Node(newElement);
                DLL.add(newNode);
                floatingSize++;
            }
            fileScanner.close();
            stopWall = System.currentTimeMillis();
            stopCPU = System.nanoTime();
            doubleListWall = (stopWall - startWall) / MILSEC;
            doubleListCPU = (stopCPU - startCPU) / NANOSEC;
            floatingSize = 0;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    void searchListsByKey() {
        userInput = new Scanner(System.in);
        System.out.println("Please enter a key to be searched");
        try {
            query = userInput.next();
            if (Integer.parseInt(query) > totalSize - 1) {
                System.out.println("Invalid key (must be between 0 and " + (totalSize - 1) + ").");
                searchListsByKey();
            }

        } catch (Exception e) {
            System.out.println("Query not found, returning to menu...");
            run();


        }
        System.out.println("Searching for key: " + query + "...");
        keySearchNodeStack();
        keySearchNodeQueue();
        keySearchLinkedListStack();
        keySearchLinkedListQueue();
        keySearchDLL();
        keySearchJStack();
        System.out.println("Search complete. \nReturning to menu...");
        System.out.println("---------------------------------------");
        run();
    }


    void searchListsByWord() {
        userInput = new Scanner(System.in);
        System.out.println("Please enter a word to be searched");
        try {
            query = userInput.next();

        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
        System.out.println("Searching for word: " + query + "...");

        searchNodeStack();
        searchNodeQueue();
        searchLinkedListStack();
        searchLinkedListQueue();
        searchDLL();
        searchJStack();
        System.out.println("Search complete. \nReturning to menu...");
        System.out.println("---------------------------------------");
        run();
    }

    void keySearchNodeStack() {
        StackNode temp = new StackNode(stack.head);
        floatingSize = totalSize;
        while (floatingSize != 0) {
            if (stack.head.data.key != Integer.parseInt(query)) {
                temp.push(stack.pop());
                floatingSize--;
            } else if (stack.head.data.key == Integer.parseInt(query)) {
                stack.peek();
                while (stack.head.data.key < totalSize - 1) {
                    stack.push(temp.pop());
                }
                return;
            }
        }
        if (floatingSize == 0) { //first method call checks to see if the word is present
            while (floatingSize < totalSize) {
                stack.push(temp.pop());
                floatingSize++;
            }
            System.out.println("Query not found, returning to menu...");
            run();
        }
    }


    void searchNodeStack() {
        StackNode temp = new StackNode(stack.head);
        floatingSize = totalSize;
        while (floatingSize != 0) {
            if (!stack.head.data.word.equalsIgnoreCase(query)) {
                temp.push(stack.pop());
                floatingSize--;
            } else if (stack.head.data.word.equalsIgnoreCase(query)) {
                stack.peek();
                while (stack.head.data.key != totalSize - 1) {
                    stack.push(temp.pop());
                }
                return;
            }
        }
        if (floatingSize == 0) { //first method call checks to see if the word is present
            while (floatingSize < totalSize) {
                stack.push(temp.pop());
                floatingSize++;
            }
            System.out.println("Query not found, returning to menu...");
            run();
        }
    }

    void keySearchNodeQueue() {
        floatingSize = totalSize;
        while (floatingSize != 0) {
            if (queue.first.data.key != Integer.parseInt(query)) {
                queue.enqueue(queue.dequeue());
                floatingSize--;
            } else if (queue.first.data.key == Integer.parseInt(query)) {
                System.out.println("Queue(Node) \nDATA: " + queue.getFirstElement().word + "\nKEY: " + queue.first.data.key);
                System.out.println();
                while (floatingSize < totalSize - 1) {
                    queue.enqueue(queue.dequeue());
                    floatingSize++;
                }
                return;
            }

        }
    }

    void searchNodeQueue() {
        floatingSize = totalSize;
        while (floatingSize != 0) {
            if (!queue.first.data.word.equalsIgnoreCase(query)) {
                queue.enqueue(queue.dequeue());
                floatingSize--;
            } else if (queue.first.data.word.equalsIgnoreCase(query)) {
                System.out.println("Queue(Node) \nDATA: " + queue.getFirstElement().word + "\nKEY: " + queue.first.data.key);
                System.out.println();
                while (floatingSize < totalSize - 1) {
                    queue.enqueue(queue.dequeue());
                    floatingSize++;
                }
                return;
            }

        }

    }

    void keySearchLinkedListStack() {
        temp = new LinkedList<>();
        floatingSize = totalSize;
        while (!(floatingSize < 0)) {
            if (Integer.parseInt(query) != stackList.peek().key) {
                temp.push(stackList.pop());
                floatingSize--;
            } else if (Integer.parseInt(query) == stackList.peek().key) {
                System.out.println("LinkedList(Stack) \nDATA: " + stackList.getFirst().word + "\nKEY: " + stackList.getFirst().key);
                System.out.println();
                while (stackList.peek().key < totalSize - 1) {
                    stackList.push(temp.pop());
                }
                return;
            }
        }
    }

    void searchLinkedListStack() {
        temp = new LinkedList<>();
        floatingSize = totalSize;
        while (!(floatingSize < 0)) {
            if (!query.equalsIgnoreCase(stackList.peek().word)) {
                temp.push(stackList.pop());
                floatingSize--;
            } else if (query.equalsIgnoreCase(stackList.peek().word)) {
                System.out.println("LinkedList(Stack) \nDATA: " + stackList.getFirst().word + "\nKEY: " + stackList.getFirst().key);
                System.out.println();
                while (stackList.peek().key < totalSize - 1) {
                    stackList.push(temp.pop());
                }
                return;
            }
        }

    }

    void keySearchLinkedListQueue() {
        temp = queueList;
        floatingSize = totalSize;
        while (queueList.getFirst() != null) {
            if (Integer.parseInt(query) == queueList.getFirst().key) {
                System.out.println("LinkedList(Queue) \nDATA: " + queueList.getFirst().word + "\nKEY: " + queueList.getFirst().key);
                System.out.println();
                return;
            } else if (Integer.parseInt(query) != queueList.getFirst().key) {
                queueList.addLast(queueList.getFirst());
                queueList.pop();
            }
        }
    }

    void searchLinkedListQueue() {
        temp = queueList;
        floatingSize = totalSize;
        while (queueList.getFirst() != null) {
            if (!query.equalsIgnoreCase(queueList.getFirst().word)) {
                queueList.addLast(queueList.removeFirst());
            } else if (query.equalsIgnoreCase(queueList.getFirst().word)) {
                System.out.println("LinkedList(Queue) \nDATA: " + queueList.getFirst().word + "\nKEY: " + queueList.getFirst().key);
                System.out.println();
                while (floatingSize < totalSize - 1) {
                    queueList.addLast(queueList.removeFirst());
                    floatingSize++;
                }
                return;
            }
        }
    }

    void keySearchDLL() {
        floatingSize = totalSize;
        while (floatingSize != 0) {
            if (DLL.search.data.key != Integer.parseInt(query)) {
                DLL.lookNext();
                floatingSize--;
            } else if (DLL.search.data.key == Integer.parseInt(query)) {
                System.out.println("Double List \nDATA: " + DLL.search.data.word + "\nKEY: " + DLL.search.data.key);
                System.out.println();
                floatingSize = 0;
                return;
            }

        }

    }

    void searchDLL() {
        floatingSize = totalSize;
        while (floatingSize != 0) {
            if (!DLL.search.data.word.equalsIgnoreCase(query)) {
                DLL.lookNext();
                floatingSize--;
            } else if (DLL.search.data.word.equalsIgnoreCase(query)) {
                System.out.println("Double List \nDATA: " + DLL.search.data.word + "\nKEY: " + DLL.search.data.key);
                System.out.println();
                floatingSize = 0;
                return;
            }

        }

    }

    void keySearchJStack() {
        Stack<Lelement> tempStack = new Stack<>();
        floatingSize = totalSize;
        while (!(floatingSize < 0)) {
            if (Integer.parseInt(query) != jStack.peek().key) {
                tempStack.push(jStack.pop());
                floatingSize--;
            } else if (Integer.parseInt(query) == jStack.peek().key) {
                System.out.println("Java Stack Class \nDATA: " + jStack.peek().word + "\nKEY: " + jStack.peek().key);
                while (jStack.peek().key < totalSize - 1) {
                    jStack.push(tempStack.pop());
                }
                return;
            }
        }
    }

    void searchJStack() {
        Stack<Lelement> tempStack = new Stack<>();
        floatingSize = totalSize;
        while (!(floatingSize < 0)) {
            if (!query.equalsIgnoreCase(jStack.peek().word)) {
                tempStack.push(jStack.pop());
                floatingSize--;
            } else if (query.equalsIgnoreCase(jStack.peek().word)) {
                System.out.println("Java Stack Class \nDATA: " + jStack.peek().word + "\nKEY: " + jStack.peek().key);
                while (jStack.peek().key < totalSize - 1) {
                    jStack.push(tempStack.pop());
                }
                return;
            }
        }
    }

    //get input file
    public void getInput() {
        userInput = new Scanner(System.in);
        System.out.println("Please type the name of your file: ");
        try {
            input = new File(userInput.next());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        if (input.length() < 3) {
            System.out.println("Invalid file. Try including the file extension, or use a file with the proper formatting.");
            getInput();
        }
        System.out.println("File name saved, returning to menu...");
        System.out.println("-------------------------------------");
        this.run();

    }

    public void showStats() {
        realFormatter = new DecimalFormat("0.0000");
        DecimalFormat sizeFormat = new DecimalFormat("0,000");
        System.out.println("There are " + sizeFormat.format(totalSize) + " words in the list. " +
                "\nStack - simple linked list:     " + this.realFormatter.format(nodeStackCPU) + " seconds CPU time    " + this.realFormatter.format(nodeStackWall) + " seconds wall clock " +
                "\nStack - LinkedList:             " + this.realFormatter.format(listStackCPU) + " seconds CPU time    " + this.realFormatter.format(listStackWall) + " seconds wall clock " +
                "\nStack - Stack<> class:          " + this.realFormatter.format(jStackCPU) + " seconds CPU time    " + this.realFormatter.format(jStackWall) + " seconds wall clock " +
                "\n " +
                "\nQueue - simple linked list:     " + this.realFormatter.format(nodeQueueCPU) + " seconds CPU time    " + this.realFormatter.format(nodeQueueWall) + " seconds wall clock " +
                "\nQueue - LinkedList:             " + this.realFormatter.format(listQueueCPU) + " seconds CPU time    " + this.realFormatter.format(listQueueWall) + " seconds wall clock " +
                "\n" +
                "\nDouble List:                    " + this.realFormatter.format(doubleListCPU) + " seconds CPU time    " + this.realFormatter.format(doubleListWall) + " seconds wall clock ");
        System.out.println("-----------------------------------------------------------------------");
        run();
    }
    /*When printing to a .txt file using "\n" instead of the BufferedWriter .newLine() function, I noticed the format was not being properly translated to the output file. I understand that "/n" takes less time to execute, which is why I made sure that all
    timing operations were performed before the printing and writing operations*/
    public void writeStats() {
        realFormatter = new DecimalFormat("0.0000");
        DecimalFormat sizeFormat = new DecimalFormat("0,000");
        System.out.println("Writing to file...");
        try {
            writer = new BufferedWriter(new FileWriter("P4Output.txt", true));
            writer.newLine();
            writer.write("There are " + sizeFormat.format(totalSize) + " words in the list.");
            writer.newLine();
            writer.write("Stack - simple linked list:   " + this.realFormatter.format(nodeStackCPU) + " seconds CPU time    " + this.realFormatter.format(nodeStackWall) + " seconds wall clock ");
            writer.newLine();
            writer.write("Stack - LinkedList:           " + this.realFormatter.format(listStackCPU) + " seconds CPU time    " + this.realFormatter.format(listStackWall) + " seconds wall clock ");
            writer.newLine();
            writer.write("Stack - Stack<> class:        " + this.realFormatter.format(jStackCPU) + " seconds CPU time    " + this.realFormatter.format(jStackWall) + " seconds wall clock ");
            writer.newLine();
            writer.newLine();
            writer.write("Queue - simple linked list:   " + this.realFormatter.format(nodeQueueCPU) + " seconds CPU time    " + this.realFormatter.format(nodeQueueWall) + " seconds wall clock ");
            writer.newLine();
            writer.write("Queue - LinkedList:           " + this.realFormatter.format(listQueueCPU) + " seconds CPU time    " + this.realFormatter.format(listQueueWall) + " seconds wall clock ");
            writer.newLine();
            writer.newLine();
            writer.write("Double List:                  " + this.realFormatter.format(doubleListCPU) + " seconds CPU time    " + this.realFormatter.format(doubleListWall) + " seconds wall clock ");
            writer.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Writing complete!");
        System.out.println("-----------------");
        run();
    }

    public void run() {
        System.out.println("P4 Program: given an input file, perform a variety of operations through interaction with user interface." + "\n(Please type a number 1-7 to access the corresponding menu option)" + "\n1 - Input File" +
                "\n2 - Load Lists" + "\n3 - Word Search" + "\n4 - Key Search" + "\n5 - Show Statistics" + "\n6 - Write Statistics" + "\n7 - End Program");
        userInput = new Scanner(System.in);
        String menuSelection;
        try {
            menuSelection = userInput.next();

            if (menuSelection.equals("1")) {
                this.getInput();
            }
            else if (menuSelection.equals("2")) {
                if (input == null) {
                    System.out.println("Error: Input file not found, please select an input file.");
                    System.out.println("-------------------------------------");
                    this.getInput();
                    return;
                }
                System.out.println("Loading lists... (Will return to menu when complete)");
                this.loadLists();
            }
            else if (menuSelection.equals("3")) {
                if (input == null) {
                    System.out.println("Error: Input file not found, please select an input file.");
                    System.out.println("-------------------------------------");
                    this.getInput();
                }
                if (totalSize == 0) {
                    System.out.println("Error: Please load the lists before attempting a search...");
                    System.out.println("-------------------------------------");
                    this.run();
                }
                searchListsByWord();
            }
            else if (menuSelection.equals("4")) {
                if (input == null) {
                    System.out.println("Error: Input file not found, please select an input file.");
                    System.out.println("-------------------------------------");
                    this.getInput();
                }
                if (totalSize == 0) {
                    System.out.println("Error: Please load the lists before attempting a search...");
                    System.out.println("-------------------------------------");
                    this.run();
                }

                searchListsByKey();

            }
            else if (menuSelection.equals("5")) {
                if (input == null) {
                    System.out.println("Error: Input file not found, please select an input file.");
                    System.out.println("-------------------------------------");
                    this.getInput();
                }
                if (totalSize == 0) {
                    System.out.println("Error: Please load the lists before attempting to display statistics...");
                    System.out.println("-------------------------------------");
                    this.run();
                }
                showStats();
            }
            else if (menuSelection.equals("6")) {
                if (input == null) {
                    System.out.println("Error: Input file not found, please select an input file.");
                    System.out.println("-------------------------------------");
                    this.getInput();
                }
                if (totalSize == 0) {
                    System.out.println("Error: Please load the lists before attempting to write statistics...");
                    System.out.println("-------------------------------------");
                    this.run();
                }
                writeStats();
            }
            else if (menuSelection.equals("7")) {
                System.out.println("Terminating program...");
                System.exit(0);
            }
            else if (!menuSelection.equals("7") || !menuSelection.equals("6") ||
                    !menuSelection.equals("5") || !menuSelection.equals("4") ||
                    !menuSelection.equals("3") || !menuSelection.equals("2") ||
                    !menuSelection.equals("1")) {
                System.out.println("Invalid selection. \nPlease input an integer [1-7] for the corresponding menu option.");
                System.out.println("-------------------------------------------------------------------------------------");
                run();
            }
            userInput.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        P4Program program = new P4Program();
        program.run(); //next time try to make more modular
    }

}
