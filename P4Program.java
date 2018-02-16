
        import java.io.*;
        import java.text.DecimalFormat;
        import java.util.LinkedList;
        import java.util.NoSuchElementException;
        import java.util.Scanner;
        import java.util.Stack;

//TO-DO: implement doubly linked lists
public class P4Program {
    BufferedWriter writer;
    DecimalFormat realFormatter;
    LinkedList<Lelement> stackList, queueList, temp;
    File input;
    Scanner userInput;
    StackNode stack;
    QueueNode queue;
    DoubleNode DLL;
    String wordQuery;
    int keyQuery = -1;
    int size, floatingSize, totalSize;
    private final int milSec = 1000;
    private final int nanoSec = 1000000000;
    long startWall, startCPU, stopWall, stopCPU;
    double nodeStackWall, nodeStackCPU, nodeQueueWall, nodeQueueCPU, listStackWall, listStackCPU,
            listQueueWall, listQueueCPU, doubleListWall, doubleListCPU;

    class Lelement {
        String word;
        int key = size;
    }

    class Node {
        Lelement data;
        Node next;
        Node prev;

        public Node(){

        }

        public Node(Lelement element) {
            this.data = element;
            this.next = null;
        }
    }
    class StackNode {
        Node head;

        public StackNode(){

        }

        public StackNode(Node first) {
            head = first;
        }

        void push(Node node) {
            if(this.head == null) {
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
            size--;
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

        public QueueNode(){

        }

        public QueueNode (Node end){
            this.last = end;

        }

        public QueueNode (Node start, Node end ){

        }

        void enqueue(Node node) {

            Node temp = new Node(node.data);
            if(first == null){
                first = temp;
                last = temp;
                first.next = last.next = first;
            }
            temp.next = first;
            last.next = temp;
            last = temp;
        }

        Node dequeue() {
            if (first.data == null) {
                throw new NoSuchElementException();
            }
            Node temp = last.next;
            if (last.next == last) {
                last = null;
            } else {
                first = first.next;
                last.next = temp.next;
                size--;
            }
            return temp;
        }

        Lelement getFirstElement(){
            return this.first.data;
        }
        Lelement getLastElement(){
            return this.last.data;
        }

    }

    class DoubleNode {
        Node post, pre, ref, search; //post and pre "guard" first and last value

        public DoubleNode() {
            pre  = new Node();
            post = new Node();
            pre.next = post;
            post.prev = pre;

        }

        public DoubleNode(Node start) {
            ref = start;
            ref.next = post;
            ref.prev = pre;
        }

        void add(Node node) {
            if (this.ref == null) {
                ref = node;
                search = ref;
            }
            ref = post.prev;
            search = ref;
            Node add = new Node(node.data);
            add.next = post;
            add.prev = ref;
            post.prev = add;
            ref.next = add;

        }

        void lookNext() {
            search = new Node();
            search.prev = ref.prev;
            if (search.next == post) {
                search = ref; //brings search back to beginning upon reaching end
            }else if(search.next == post.prev){
                search = search.next;
            }
            search = search.prev;

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
        if (!input.exists()) {
            System.out.println("Error: No file input, returning to menu...");
            System.out.println("-------------------------------------");
            this.run();
        }

        fillNodeStack();
        fillLinkedListStack();
        fillNodeQueue();
        fillLinkedListQueue();
        fillDoublyLinkedList();
        this.run();

    }

    public void fillDoublyLinkedList() {
        if (!input.exists()) {
            System.out.println("Error: Input file not found, please select another input file." + "\nReturning to menu...");
            System.out.println("-------------------------------------");
            this.run();
        }
        try {
            Scanner fileScanner = new Scanner(new FileReader(input));
            DLL = new DoubleNode();
            startWall = System.currentTimeMillis();
            startCPU = System.nanoTime();
            while (fileScanner.hasNext()) {
                Lelement newElement = new Lelement();
                newElement.word = fileScanner.next();
                newElement.key = size;
                Node newNode = new Node(newElement);
                DLL.add(newNode);
//                System.out.println(getNext(head).word);
//                System.out.println(getNext(head).key);
                size++;
            }
            fileScanner.close();
            stopWall = System.currentTimeMillis();
            stopCPU = System.nanoTime();
            doubleListWall = ((double) stopWall - (double) startWall) / milSec;
            doubleListCPU = ((double) stopCPU - (double) startCPU) / nanoSec;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }


    public void fillNodeStack() {
        if (!input.exists()) {
            System.out.println("Error: Input file not found, please select another input file." + "\nReturning to menu...");
            System.out.println("-------------------------------------");
            this.run();
        }
        try {
            Scanner fileScanner = new Scanner(new FileReader(input));
            stack = new StackNode();
//            System.out.println("Loading simple linked list (Stack)...");
            startWall = System.currentTimeMillis();
            startCPU = System.nanoTime();
            while (fileScanner.hasNext()) {
                Lelement newElement = new Lelement();
                newElement.word = fileScanner.next();
                newElement.key = size;
                Node newNode = new Node(newElement);
                stack.push(newNode);
//                System.out.println(getNext(head).word);
//                System.out.println(getNext(head).key);
                size++;
            }
            fileScanner.close();
            stopWall = System.currentTimeMillis();
            stopCPU = System.nanoTime();
            nodeStackWall = ((double) stopWall - (double) startWall) / milSec;
            nodeStackCPU = ((double) stopCPU - (double) startCPU) / nanoSec;
            totalSize = size;

            size = 0; //CHECK AFTER SEARCHING METHODS ARE COMPLETE
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }


    public void fillNodeQueue() {
        if (!input.exists()) {
            System.out.println("Error: Input file not found, please select another input file." + "\nReturning to menu...");
            System.out.println("-------------------------------------");
            this.run();
        }
        try {
            Scanner fileScanner = new Scanner(new FileReader(input));
            queue = new QueueNode();
//            System.out.println("Loading simple linked list (Queue)...");
            startWall = System.currentTimeMillis();
            startCPU = System.nanoTime();
            while (fileScanner.hasNext()) {
                Lelement newElement = new Lelement();
                newElement.word = fileScanner.next();
                newElement.key = size;
                Node newNode = new Node(newElement);
                queue.enqueue(newNode);
                size++;
            }
            fileScanner.close();
            stopWall = System.currentTimeMillis();
            stopCPU = System.nanoTime();
            nodeQueueWall = ((double) stopWall - (double) startWall) / milSec;
            nodeQueueCPU = ((double) stopCPU - (double) startCPU) / nanoSec;
            size = 0; //CHECK AFTER SEARCHING METHODS ARE COMPLETE
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }


    public void fillLinkedListStack() {
        stackList = new LinkedList<>();
        if (!input.exists()) {
            System.out.println("Error: Input file not found, please select another input file." + "\nReturning to menu...");
            System.out.println("-------------------------------------");
            this.run();
        }
        try {
            Scanner fileScanner = new Scanner(new FileReader(input));
//            System.out.println("Loading Java LinkedList (Stack)...");
            startWall = System.currentTimeMillis();
            startCPU = System.nanoTime();
            while (fileScanner.hasNext()) {
                Lelement newElement = new Lelement();
                newElement.word = fileScanner.next();
                newElement.key = size;
                stackList.push(newElement);
                size++;
            }
            fileScanner.close();
            stopWall = System.currentTimeMillis();
            stopCPU = System.nanoTime();
            listStackWall = ((double) stopWall - (double) startWall) / milSec;
            listStackCPU = ((double) stopCPU - (double) startCPU) / nanoSec;
            size = 0; //CHECK AFTER SEARCHING METHODS ARE COMPLETE
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }

    public void fillLinkedListQueue() {
        queueList = new LinkedList<>();
        if (!input.exists()) {
            System.out.println("Error: Input file not found, please select another input file." + "\nReturning to menu...");
            System.out.println("-------------------------------------");
            this.run();
        }
        try {
            Scanner fileScanner = new Scanner(new FileReader(input));
//            System.out.println("Loading Java LinkedList (Queue)...");
//             if (this.queueList.getFirst() == null && this.queueList.getLast() == null) {
            startWall = System.currentTimeMillis();
            startCPU = System.nanoTime();
            while (fileScanner.hasNext()) {
                Lelement newElement = new Lelement();
                newElement.word = fileScanner.next();
                newElement.key = size;
                this.queueList.add(newElement);
                size++;
            }
            fileScanner.close();
            stopWall = System.currentTimeMillis();
            stopCPU = System.nanoTime();
//            }
            listQueueWall = ((double) stopWall - (double) startWall) / milSec;
            listQueueCPU = ((double) stopCPU - (double) startCPU) / nanoSec;
            size = 0; //CHECK AFTER SEARCHING METHODS ARE COMPLETE
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }

    //TO-DO: Search by either string or integer entries
    void searchListsByWord() {
        if (!input.exists()) {
            System.out.println("Error: Input file not found, please select another input file." + "\nReturning to menu...");
            System.out.println("-------------------------------------");
            this.run();
        }
        userInput = new Scanner(System.in);
        System.out.println("Please enter a word to be searched");
        try {
            wordQuery = userInput.next();

        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
        System.out.println("Searching for word: " + wordQuery + "...");
        searchNodeStack();
        searchNodeQueue();
        searchLinkedListStack();
        searchLinkedListQueue();
        searchDLL();
        System.out.println("Search complete. \nReturning to menu...");
        System.out.println("---------------------------------------");
        size = 0;
        loadLists();
        run();
    }

    void searchListsByKey() {
        if (!input.exists()) {
            System.out.println("Error: Input file not found, please select another input file." + "\nReturning to menu...");
            System.out.println("-------------------------------------");
            this.run();
        }
        userInput = new Scanner(System.in);
        System.out.println("Please enter a key to be searched");
        try {
            keyQuery = userInput.nextInt();
            if (keyQuery > 20067) {
                System.out.println("Invalid key (must be between 0 and 20067).");
                searchListsByKey();
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
        System.out.println("Searching for key: " + keyQuery + "...");
        keySearchNodeStack();
        keySearchNodeQueue();
        keySearchLinkedListStack();
        keySearchLinkedListQueue();
        keySearchDLL();
        System.out.println("Search complete. \nReturning to menu...");
        System.out.println("---------------------------------------");
        size = 0;
        loadLists();
        run();
    }
    void searchDLL() {
//        DoubleNode temp = new DoubleNode(DLL.ref);
        floatingSize = totalSize;
        while (floatingSize != 0){
            if (!DLL.search.data.word.equalsIgnoreCase(wordQuery)){
               DLL.lookNext();
               floatingSize--;
            } else if (DLL.search.data.word.equalsIgnoreCase(wordQuery)){
                System.out.println("Double List \nDATA: " + DLL.search.data.word + "\nKEY: " + DLL.search.data.key);
            }

        }
        System.out.println("Query not found, returning to menu...");
        run();

    }

    void keySearchDLL() {
//        DoubleNode temp = new DoubleNode(DLL.ref);
        floatingSize = totalSize;
        while (floatingSize != 0){
            if (DLL.search.data.key != keyQuery){
                DLL.lookNext();
                floatingSize--;
            } else if (DLL.search.data.key == keyQuery){
                System.out.println("Double List \nDATA: " + DLL.search.data.word + "\nKEY: " + DLL.search.data.key);
            }

        }
        System.out.println("Query not found, returning to menu...");
        run();

    }

    void keySearchNodeStack() {
        StackNode temp = new StackNode(stack.head);
        floatingSize = totalSize;
        while (floatingSize != 0) {
            if (stack.head.data.key != keyQuery) {
                temp.push(stack.pop());
            } else if (stack.head.data.key == keyQuery) {
                stack.peek();
                stack.push(temp.pop());
                return;
            }
        }
        if (floatingSize == 0) { //first method call checks to see if the word is present
            while (floatingSize != totalSize) {
                stack.push(temp.pop());
            }
            System.out.println("Query not found, returning to menu...");
            run();

        }
    }


    void searchNodeStack() {
        StackNode temp = new StackNode(stack.head);
        floatingSize = totalSize;
        while (floatingSize != 0) {
            if (!stack.head.data.word.equalsIgnoreCase(wordQuery)) {
                temp.push(stack.pop());
                floatingSize--;
            } else if (stack.head.data.word.equalsIgnoreCase(wordQuery)) {
                stack.peek();
                stack.push(temp.pop());
                return;
            }
        }
        if (floatingSize == 0) { //first method call checks to see if the word is present
            while (floatingSize != totalSize) {
                stack.push(temp.pop());
                floatingSize++;
            }
            System.out.println("Query not found, returning to menu...");
            run();
        }
    }

    void keySearchNodeQueue() {
        QueueNode temp = new QueueNode();
        floatingSize = totalSize;
        while (floatingSize != 0) {
            if (queue.first.data.key == keyQuery) {
                System.out.println("Queue(Node) \nDATA: " + queue.first.data.word + "\nKEY: " + queue.first.data.key);
                System.out.println();
                if(queue.getFirstElement().key > 0) {
                    queue.enqueue(temp.dequeue());
                    floatingSize++;
                }
                return;

            } else {
                temp.enqueue(queue.dequeue());
                floatingSize--;
            }
        }

    }
    void searchNodeQueue() {
        QueueNode temp = new QueueNode();
        floatingSize = totalSize;
        while (floatingSize != 0) {
            if (queue.getFirstElement().word.equalsIgnoreCase(wordQuery)) {
                System.out.println("Queue(Node) \nDATA: " + queue.getFirstElement().word + "\nKEY: " + queue.getFirstElement().key);
                System.out.println();
                if(queue.getFirstElement().key > 0) {
                    queue.enqueue(temp.dequeue());
                    floatingSize++;
                }
                return;

            }
            else {
                temp.enqueue(queue.dequeue());
                floatingSize--;
            }
        }
        if (floatingSize == 0) {
            while (floatingSize != totalSize) {
                queue.enqueue(queue.dequeue());
                floatingSize++;
            }
            System.out.println("Query not found, returning to menu...");
            run();
        }

    }

    void keySearchLinkedListStack() {
        temp = stackList;
        floatingSize = totalSize;
        while (stackList.getFirst() != null) {
            if (keyQuery == stackList.getFirst().key) {
                System.out.println("LinkedList(Stack) \nDATA: " + stackList.getFirst().word + "\nKEY: " + stackList.getFirst().key);
                System.out.println();
                return;
            } else if (keyQuery != stackList.getFirst().key) {
                stackList.pop();
            }
        }
        stackList = temp;
    }

    void searchLinkedListStack() {
        temp = stackList;
        floatingSize = totalSize;
        while (stackList.getFirst() != null) {
            if (wordQuery.equalsIgnoreCase(stackList.getFirst().word)) {
                System.out.println("LinkedList(Stack) \nDATA: " + stackList.getFirst().word + "\nKEY: " + stackList.getFirst().key);
                System.out.println();
                return;
            } else if (!wordQuery.equalsIgnoreCase(stackList.getFirst().word)) {
                stackList.pop();
            }
        }
        stackList = temp;
    }

    void keySearchLinkedListQueue() {
        temp = queueList;
        floatingSize = totalSize;
        while (queueList.getFirst() != null) {
            if (keyQuery == queueList.getFirst().key) {
                System.out.println("LinkedList(Queue) \nDATA: " + queueList.getFirst().word + "\nKEY: " + queueList.getFirst().key);
                System.out.println();
                return;
            } else if (keyQuery != queueList.getFirst().key) {
                queueList.addLast(queueList.getFirst());
                queueList.pop();
            }
        }
        queueList = temp;
    }

    void searchLinkedListQueue() {
        temp = queueList;
        floatingSize = totalSize;
        while (queueList.getFirst() != null) {
            if (wordQuery.equalsIgnoreCase(queueList.getFirst().word)) {
                System.out.println("LinkedList(Queue) \nDATA: " + queueList.getFirst().word + "\nKEY: " + queueList.getFirst().key);
                System.out.println();
                return;
            } else if (!wordQuery.equalsIgnoreCase(queueList.getFirst().word)) {
                queueList.addLast(queueList.getFirst());
                queueList.pop();
            }
        }
        queueList = temp;
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
        if (input.length() < 4) {
            System.out.println("Invalid filename (be sure to include type extension)");
            getInput();
        }
        System.out.println("File name saved, returning to menu...");
        System.out.println("-------------------------------------");
        this.run();

    }

    public void showStats() {
        realFormatter = new DecimalFormat("0.0000");
        DecimalFormat sizeFormat = new DecimalFormat("0,000");
        System.out.println("There are " + sizeFormat.format(totalSize) + " words in the list. \nStack - simple linked list:     " + this.realFormatter.format(nodeStackCPU) + " seconds CPU time    " + this.realFormatter.format(nodeStackWall) + " seconds wall clock " +
                "\nStack - LinkedList:             " + this.realFormatter.format(listStackCPU) + " seconds CPU time    " + this.realFormatter.format(listStackWall) + " seconds wall clock " +
                "\n " +
                "\nQueue - simple linked list:     " + this.realFormatter.format(nodeQueueCPU) + " seconds CPU time    " + this.realFormatter.format(nodeQueueWall) + " seconds wall clock " +
                "\nQueue - LinkedList:             " + this.realFormatter.format(listQueueCPU) + " seconds CPU time    " + this.realFormatter.format(listQueueWall) + " seconds wall clock " +
                "\n" +
                "\nDouble List:                    " + this.realFormatter.format(doubleListCPU)+ " seconds CPU time    " + this.realFormatter.format(doubleListWall) +" seconds wall clock ");
        System.out.println("-----------------------------------------------------------------------");
        run();
    }

    public void writeStats() {
        realFormatter = new DecimalFormat("0.0000");
        DecimalFormat sizeFormat = new DecimalFormat("0,000");
        System.out.println("Writing to file...");
        try {
            writer = new BufferedWriter(new FileWriter("P4Output.txt", true));
            writer.newLine();
            writer.write("There are " + sizeFormat.format(totalSize) + " words in the list.");
            writer.newLine();
            writer.write("Stack - simple linked list:     " + this.realFormatter.format(nodeStackCPU) + " seconds CPU time    " + this.realFormatter.format(nodeStackWall) + " seconds wall clock ");
            writer.newLine();
            writer.write("Stack - LinkedList:     " + this.realFormatter.format(listStackCPU) + " seconds CPU time    " + this.realFormatter.format(listStackWall) + " seconds wall clock ");
            writer.newLine();
            writer.newLine();
            writer.write("Queue - simple linked list:     " + this.realFormatter.format(nodeQueueCPU) + " seconds CPU time    " + this.realFormatter.format(nodeQueueWall) + " seconds wall clock ");
            writer.newLine();
            writer.write("Queue - LinkedList:     " + this.realFormatter.format(listQueueCPU) + " seconds CPU time    " + this.realFormatter.format(listQueueWall) + " seconds wall clock ");
            writer.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Writing complete!");
        System.out.println("-----------------");
        run();
    }

    //TO-DO: allow for both numerical and string entries
    public void run() {
        System.out.println("P4 Program: given an input file, perform a variety of operations through interaction with user interface." + "\n(Please type a number 1-7 to access the corresponding menu option)" + "\n1 - Input File" +
                "\n2 - Load Lists" + "\n3 - Word Search" + "\n4 - Key Search" + "\n5 - Show Statistics" + "\n6 - Write Statistics" + "\n7 - Exit");
        userInput = new Scanner(System.in);
        String menuSelection = new String();
        try {
            menuSelection = userInput.next();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
            if (menuSelection.equals("1")) {
                this.getInput();
            }
            if (menuSelection.equals("2")) {
                System.out.println("Loading lists... (Will return to menu when complete)");
                this.loadLists();
            }
            if (menuSelection.equals("3")) {
                searchListsByWord();
            }
            if (menuSelection.equals("4")) {
                searchListsByKey();
            }
            if (menuSelection.equals("5")) {
                showStats();
            }
            if (menuSelection.equals("6")) {
                writeStats();
            }
            if (menuSelection.equals("7")) {
                System.out.println("Terminating program...");
                System.exit(0);
            }
            if (!menuSelection.equals("7") || !menuSelection.equals("6") ||
                    !menuSelection.equals("5") || !menuSelection.equals("4") ||
                    !menuSelection.equals("3") || !menuSelection.equals("2") ||
                    !menuSelection.equals("1")) {
                System.out.println("Invalid selection. \nPlease input an integer [1-7] for the corresponding menu option.");
                System.out.println("-------------------------------------------------------------------------------------");
                run();
            }


    }

    public static void main(String[] args) {
        P4Program program = new P4Program();
        program.run();
    }

}
