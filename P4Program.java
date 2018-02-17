
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
    int floatingSize, totalSize;
    private final int MILSEC = 1000;
    private final int NANOSEC = 1000000000;
    long startWall, startCPU, stopWall, stopCPU;
    double nodeStackWall, nodeStackCPU, nodeQueueWall, nodeQueueCPU, listStackWall, listStackCPU,
            listQueueWall, listQueueCPU, doubleListWall, doubleListCPU;

    class Lelement {
        String word;
        int key = floatingSize;
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
//            floatingSize--;
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
                first = last = temp;
            }
            else {
                last.next = temp;
                last = temp;
            }
             last.next = first;
        }

        Node dequeue() {
            Node temp = new Node();
            if (first.data == null) {
                throw new NoSuchElementException();
            }
            else if (first == last) {
                temp.data = first.data;
                first = last = null;
            } else {
                temp = first;
                first = first.next;
                last.next = first;
//                floatingSize--;
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
            search = new Node();
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

        fillNodeStack();
        fillNodeQueue();
        fillLinkedListStack();
        fillLinkedListQueue();
        fillDoublyLinkedList();
        System.out.println("Lists loaded, returning to menu...");
        System.out.println("----------------------------------");
        this.run();

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
            nodeStackWall = ((double) stopWall - (double) startWall) / MILSEC;
            nodeStackCPU = ((double) stopCPU - (double) startCPU) / NANOSEC;
            totalSize = floatingSize;
            floatingSize = 0;
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
            nodeQueueWall = ((double) stopWall - (double) startWall) / MILSEC;
            nodeQueueCPU = ((double) stopCPU - (double) startCPU) / NANOSEC;
            floatingSize = 0;
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
            listStackWall = ((double) stopWall - (double) startWall) / MILSEC;
            listStackCPU = ((double) stopCPU - (double) startCPU) / NANOSEC;
            floatingSize = 0;
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
            listQueueWall = ((double) stopWall - (double) startWall) / MILSEC;
            listQueueCPU = ((double) stopCPU - (double) startCPU) / NANOSEC;
            floatingSize = 0;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
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
                newElement.key = floatingSize;
                Node newNode = new Node(newElement);
                DLL.add(newNode);
                floatingSize++;
            }
            fileScanner.close();
            stopWall = System.currentTimeMillis();
            stopCPU = System.nanoTime();
            doubleListWall = ((double) stopWall - (double) startWall) / MILSEC;
            doubleListCPU = ((double) stopCPU - (double) startCPU) / NANOSEC;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    void searchListsByKey() {
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
        run();
    }


    void searchListsByWord() {
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
        run();
    }

    void keySearchNodeStack() {
        StackNode temp = new StackNode(stack.head);
        floatingSize = totalSize;
        while (floatingSize != 0) {
            if (stack.head.data.key != keyQuery) {
                temp.push(stack.pop());
                floatingSize--;
            } else if (stack.head.data.key == keyQuery) {
                stack.peek();
                while(stack.head.data.key < totalSize-1) {
                    stack.push(temp.pop());
                }
                return;
            }
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
                while(stack.head.data.key != totalSize-1) {
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
            if(queue.first.data.key != keyQuery){
                queue.enqueue(queue.dequeue());
                floatingSize--;
            } else if (queue.first.data.key == keyQuery) {
                System.out.println("Queue(Node) \nDATA: " + queue.getFirstElement().word + "\nKEY: " + queue.first.data.key);
                System.out.println();
                while(floatingSize < totalSize-1) {
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
            if(!queue.first.data.word.equalsIgnoreCase(wordQuery)){
                queue.enqueue(queue.dequeue());
                floatingSize--;
            } else if (queue.first.data.word.equalsIgnoreCase(wordQuery)) {
                System.out.println("Queue(Node) \nDATA: " + queue.getFirstElement().word + "\nKEY: " + queue.first.data.key);
                System.out.println();
                while(floatingSize < totalSize-1) {
                    queue.enqueue(queue.dequeue());
                    floatingSize++;
                }
                return;
            }

        }

    }

    void keySearchLinkedListStack() {
        floatingSize = totalSize;
        while (floatingSize != 0) {
            if (keyQuery != stackList.peek().key) {
                temp.push(stackList.pop());
                floatingSize--;
            } else if (keyQuery == stackList.peek().key) {
                System.out.println("LinkedList(Stack) \nDATA: " + stackList.getFirst().word + "\nKEY: " + stackList.getFirst().key);
                System.out.println();
                while(stackList.peek().key < totalSize-1) {
                    stackList.push(temp.pop());
                }
                return;
            }
        }
    }

    void searchLinkedListStack() {
        temp = new LinkedList<>();
        floatingSize = totalSize;
        while (floatingSize != 0) {
            if (!wordQuery.equalsIgnoreCase(stackList.peek().word)) {
                temp.push(stackList.pop());
                floatingSize--;
            } else if (wordQuery.equalsIgnoreCase(stackList.peek().word)) {
                System.out.println("LinkedList(Stack) \nDATA: " + stackList.getFirst().word + "\nKEY: " + stackList.getFirst().key);
                System.out.println();
                while(stackList.peek().key < totalSize-1) {
                    stackList.push(temp.pop());
                }
                return;
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
            if (!wordQuery.equalsIgnoreCase(queueList.getFirst().word)) {
                queueList.addLast(queueList.removeFirst());
            } else if (wordQuery.equalsIgnoreCase(queueList.getFirst().word)) {
                System.out.println("LinkedList(Queue) \nDATA: " + queueList.getFirst().word + "\nKEY: " + queueList.getFirst().key);
                System.out.println();
                while(floatingSize < totalSize-1) {
                    queueList.addLast(queueList.removeFirst());
                    floatingSize++;
                }
                return;
            }
        }
    }

    void keySearchDLL() {
        floatingSize = totalSize;
        while (floatingSize != 0){
            if (DLL.search.data.key != keyQuery){
                DLL.lookNext();
                floatingSize--;
            } else if (DLL.search.data.key == keyQuery){
                System.out.println("Double List \nDATA: " + DLL.search.data.word + "\nKEY: " + DLL.search.data.key);
                floatingSize = 0;
                return;
            }

        }

    }

    void searchDLL() {
        floatingSize = totalSize;
        while (floatingSize != 0){
            if (!DLL.search.data.word.equalsIgnoreCase(wordQuery)){
                DLL.lookNext();
                floatingSize--;
            } else if (DLL.search.data.word.equalsIgnoreCase(wordQuery)){
                System.out.println("Double List \nDATA: " + DLL.search.data.word + "\nKEY: " + DLL.search.data.key);
                floatingSize = 0;
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
        if (menuSelection.equals("2")) {
            if (input == null) {
                System.out.println("Error: Input file not found, please select an input file.");
                System.out.println("-------------------------------------");
                this.getInput();
                return;
            }
            System.out.println("Loading lists... (Will return to menu when complete)");
            this.loadLists();
        }
        if (menuSelection.equals("3")) {
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
        if (menuSelection.equals("4")) {
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
        if (menuSelection.equals("5")) {
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
        if (menuSelection.equals("6")) {
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
            userInput.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        P4Program program = new P4Program();
        program.run();
    }

}
