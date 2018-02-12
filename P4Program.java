import java.io.File;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.Scanner;

//TO-DO: implement doubly linked lists
public class P4Program {

    LinkedList<Lelement> stackList, queueList, temp;
    File input;
    Scanner userInput;
    StackNode head;
    QueueNode first, last;
    String query;
    int size;
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
        int key = size;
    }

    class QueueNode {
        String data;
        QueueNode next;
        int key = size;

    }

    class DoubleNode {
        String data;
        DoubleNode next, prev;
        int key = size;
    }

    void enqueue(String element) {
        if (first == null) {
            first = last = new QueueNode();
            first.data = element;
            first.key = size;
            size++;
        } else {
            last = new QueueNode();
            last.data = element;
            last.key = size;
            size++;
            last.next = first;

        }
    }

    void dequeue() {
        if (first == null) {
            System.out.println("Error: Queue is empty");
        }
        if (first == last) {
            first = last = null;
        }
        else {
            QueueNode temp = first.next;
            first = first.next;
            last = last.next;
        }
    }

    void push(String element) {
        StackNode pushDown = this.head;
        head = new StackNode();
        head.data = element;
        head.next = pushDown;
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
            query = userInput.next();

        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
        System.out.println("Searching for word: " + query + "...");
        searchNodeStack();
        searchNodeQueue();
        searchLinkedListStack();
        searchLinkedListQueue();
        System.out.println("Search complete. \nReturning to menu...");
        loadLists();
        run();
    }

    void searchNodeStack() {
        StackNode temp = new StackNode();
        if (head == null) {
            System.out.println("Error: Word not found");
        }
        while (head != null) {
            if (head.data.equalsIgnoreCase(query)) {
                this.peek();
                return;
            } else if (!head.data.equalsIgnoreCase(query)) {
                temp = head;
                temp.next = head.next;
                this.pop();
            }
        }
//        while (temp != null) {
//            try {
//                Scanner fileScanner = new Scanner(new FileReader(input));
//                while (fileScanner.hasNext()) {
//                    this.push(fileScanner.next());
//                }
//                fileScanner.close();
//            } catch (Exception e) {
//                System.out.println(e.getMessage());
//            }
//        }
    }

    //not working properly--SEEK HELP
    void searchNodeQueue() {
        if (first == null) {
            System.out.println("Error: Word not found");
        }
        while (first != null) {
            if (first.data.equalsIgnoreCase(query)) {
                System.out.println("Queue(Node) \nDATA: " + first.data + "\nKEY: " + first.key);
                System.out.println();
                return;

            } else if (!first.data.equalsIgnoreCase(query)) {
                this.dequeue();
            }
        }

    }

    void searchLinkedListStack() {
        temp = stackList;
        while (stackList.getFirst() != null) {
            if (query.equalsIgnoreCase(stackList.getFirst().word)) {
                System.out.println("LinkedList(Stack) \nDATA: " + stackList.getFirst().word + "\nKEY: " + stackList.getFirst().key);
                System.out.println();
                return;
            } else if (!query.equalsIgnoreCase(stackList.getFirst().word)) {
                stackList.pop();
            }
        }
        if (head == null) {
            System.out.println("Error: Word not found");
        }
        stackList = temp;
    }

    void searchLinkedListQueue() {
        temp = queueList;
        while (queueList.getFirst() != null) {
            if (query.equalsIgnoreCase(queueList.getFirst().word)) {
                System.out.println("LinkedList(Queue) \nDATA: " + queueList.getFirst().word + "\nKEY: " + queueList.getFirst().key);
                System.out.println();
                return;
            } else if (!query.equalsIgnoreCase(queueList.getFirst().word)) {
                queueList.addLast(queueList.getFirst());
                queueList.pop();
        }
        }
        if (head == null) {
            System.out.println("Error: Word not found");
        }
        queueList = temp;
    }
//TO-DO: allow for both numerical and string entries
    public void run() {
        System.out.println("P4 Program: given an input file words.txt" + "\n(Please type a number 1-7 to access the corresponding menu option)" + "\n1 - Input File" + "\n2 - Load Lists" + "\n3 - Word Search");
        userInput = new Scanner(System.in);
        int menuSelection = userInput.nextInt();
        try {
            if (menuSelection == 1) {
                this.getInput();
            }
            if (menuSelection == 2) {
                System.out.println("Loading lists... (Will return to menu when complete)");
                this.loadLists();
            }
            if (menuSelection == 3) {
                this.searchListsByWord();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
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
        System.out.println("File name saved, returning to menu...");
        System.out.println("-------------------------------------");
        this.run();

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
