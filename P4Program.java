import java.io.*;
import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.Scanner;

//TO-DO: implement doubly linked lists
public class P4Program {
    BufferedWriter writer;
    DecimalFormat realFormatter;
    LinkedList<Lelement> stackList, queueList, temp;
    File input;
    Scanner userInput;
    StackNode head;
    QueueNode first, last;
    String wordQuery;
    int keyQuery = -1;
    int size, totalSize;
    private final int milSec = 1000;
    private final int nanoSec = 1000000000;
    long startWall, startCPU, stopWall, stopCPU;
    double nodeStackWall, nodeStackCPU, nodeQueueWall, nodeQueueCPU, listStackWall, listStackCPU,
            listQueueWall, listQueueCPU;

    class Lelement {
        String word;
        int key;
    }

    class StackNode {
        String data;
        StackNode next;
        int key;
    }

    class QueueNode {
        String data;
        QueueNode next;
        int key;

    }

    class DoubleNode {
        String data;
        DoubleNode next, prev;
        int key = size;
    }

    public P4Program(){
        try {
            writer = new BufferedWriter(new FileWriter("P4Output.txt"));
            writer.write("Khamunetri Clark");
            writer.newLine();
            writer.newLine();
            writer.close();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    boolean isEmpty(){
        return first == null;
    }

    void enqueue(String element) {

        QueueNode secondToLast = last;
        last = new QueueNode();
        last.data = element;
        last.key = size;
        last.next = null;
        if (isEmpty()) {
            first = last;
        } else {
            secondToLast.next = last;
            last.data = element;
            last.key = size;
        }
        size++;
    }

    void dequeue() {
        if (isEmpty()) {
//            System.out.println("Error: Queue is empty");
            last = null;
        }
        if (first == last) {
            first = last = null;
        }
        else {
            first = first.next;
            size--;
        }
    }

    void push(String element) {
        StackNode pushDown = this.head;
        head = new StackNode();
        head.data = element;
        head.next = pushDown;
        head.key = size;
        size++;
    }

    void pop() {
        if (head == null) {
            System.out.println("Error: Stack underflow");
            return;
        }
        head = head.next;
        size--;

    }

    void peek() {
        if (head == null) {
            System.out.println("Error: Stack is empty");
            return;
        }
        System.out.println("Stack(Node) \nDATA: " + head.data + "\nKEY: " + head.key);
        System.out.println();
    }

    public void loadLists() {
        if(!input.exists()){
            System.out.println("Error: No file input, returning to menu...");
            System.out.println("-------------------------------------");
            this.run();
        }

        fillNodeStack();
        fillLinkedListStack();
        fillNodeQueue();
        fillLinkedListQueue();
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
//            System.out.println("Loading simple linked list (Stack)...");
            startWall = System.currentTimeMillis();
            startCPU = System.nanoTime();
            while (fileScanner.hasNext()) {
                this.push(fileScanner.next());
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
//            System.out.println("Loading simple linked list (Queue)...");
            startWall = System.currentTimeMillis();
            startCPU = System.nanoTime();
            while (fileScanner.hasNext()) {
                this.enqueue(fileScanner.next());
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
            if(keyQuery > 20067){
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
        System.out.println("Search complete. \nReturning to menu...");
        System.out.println("---------------------------------------");
        size = 0;
        loadLists();
        run();
    }
    void keySearchNodeStack() {
        while (head != null) {
            if (head.key == keyQuery) {
                this.peek();
                return;
            } else if (head.key != keyQuery) {
                this.pop();
            }
        }
        if(head == null) { //first method call checks to see if the word is present
//            System.out.println("Query not found, returning to menu...");
            fillNodeStack(); //reset lists
            loadLists();
        }
    }


    void searchNodeStack() {
        while (head != null) {
            if (head.data.equalsIgnoreCase(wordQuery)) {
                this.peek();
                return;
            } else if (!head.data.equalsIgnoreCase(wordQuery)) {
                this.pop();
            }
        }
        if(head == null) { //first method call checks to see if the word is present
            System.out.println("Query not found, returning to menu...");
            System.out.println("-------------------------------------");
            fillNodeStack(); //reset lists
            loadLists();
            run();
        }
    }

    void keySearchNodeQueue() {
        while (first != null) {
            if (first.key == keyQuery) {
                System.out.println("Queue(Node) \nDATA: " + first.data + "\nKEY: " + first.key);
                System.out.println();
                return;

            } else {
                this.dequeue();
            }
        }

    }

    void searchNodeQueue() {
        while (first != null) {
            if (first.data.equalsIgnoreCase(wordQuery)) {
                System.out.println("Queue(Node) \nDATA: " + first.data + "\nKEY: " + first.key);
                System.out.println();
                return;

            } else {
                this.dequeue();
            }
        }

    }

    void keySearchLinkedListStack() {
        temp = stackList;
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
        if(input.length() < 4){
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
                "\nStack - LinkedList:     " + this.realFormatter.format(listStackCPU) + " seconds CPU time    " + this.realFormatter.format(listStackWall) + " seconds wall clock " +
                "\n " +
                "\nQueue - simple linked list:     " + this.realFormatter.format(nodeQueueCPU) + " seconds CPU time    " + this.realFormatter.format(nodeQueueWall) + " seconds wall clock " +
                "\nQueue - LinkedList:     " + this.realFormatter.format(listQueueCPU) + " seconds CPU time    " + this.realFormatter.format(listQueueWall) + " seconds wall clock ");
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
        }
        catch(Exception e){
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
        try {
            String menuSelection = userInput.next();
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
            if (menuSelection.equals("7")){
                System.out.println("Terminating program...");
                System.exit(0);
            }
            if (!menuSelection.equals("7") || !menuSelection.equals("6")||
                    !menuSelection.equals("5") || !menuSelection.equals("4") ||
                    !menuSelection.equals("3") || !menuSelection.equals("2")||
                    !menuSelection.equals("1")){
                System.out.println("Invalid selection. \nPlease input an integer [1-7] for the corresponding menu option.");
                System.out.println("-------------------------------------------------------------------------------------");
                run();
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    //    void getFileName(){
//        System.out.println(this.input);
//
    public void loadDLL() {

    }

    public void searchDLL() {

    }

    public static void main(String[] args) {
        P4Program program = new P4Program();
        program.run();
    }

}
