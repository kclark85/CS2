import java.io.*;
import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.Scanner;

public class SeparateChaining {
    DecimalFormat realFormatter;
    int M;
    int totalCollisions;
    int mostCollisions;
    int mostCollisionsIndex;
    private final double MILSEC = 1000;
    private final double NANOSEC = 1000000000;
    long startWall, startCPU, stopWall, stopCPU;
    double wallTime, CPUTime;
    LinkedList<Integer>[] hashTable;
    Scanner userInput;
    File input;
    Scanner reader;
    BufferedWriter writer;

    public SeparateChaining(int M, File input) {
        this.M = M;
        this.input = input;
        this.totalCollisions = 0;
        this.hashTable = new LinkedList[M];
        for (int i = 0; i < M; i++) {
            hashTable[i] = new LinkedList<>();
        }
    }

    public SeparateChaining(int M) {
        this.M = M;
        this.totalCollisions = 0;
        this.hashTable = new LinkedList[M];
        for (int i = 0; i < M; i++) {
            hashTable[i] = new LinkedList<>();
        }
        try {
            writer = new BufferedWriter(new FileWriter("SCOutput.txt"));
            writer.write("Khamunetri Clark");
            writer.newLine();
            writer.write("====================================================================");
            writer.newLine();
            writer.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public void getFile() {
        userInput = new Scanner(System.in);
        System.out.println("Please type the name of your file: ");
        try {
            input = new File(userInput.next());
            if (!input.exists()) {
                throw new FileNotFoundException("Error: File not found");
            } else if (input.length() <= 0) {
                throw new FileNotFoundException("Error: Empty file provided");
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            input = null;
            System.out.println("Invalid input. Try including the file extension, or use a file with the proper formatting if the problem persists.");
            getFile();
        }
        if (input != null) {
            System.out.println("File saved.");
        }
    }

    public File getInput() {
        return this.input;
    }

    private int hash(int k) {
        int hash = k % 1231;
        return hash;
    }

    public void loadTable() {
        try {
            reader = new Scanner(new BufferedReader(new FileReader(input)));
            if (!reader.hasNextInt()) {
                System.out.println("Error: file must consist of integers");
                getFile();
            }
            System.out.println("Loading Table size " + this.hashTable.length + " and managing collisions by separate chaining...");
            this.startWall = System.currentTimeMillis();
            this.startCPU = System.nanoTime();
            while (reader.hasNextInt()) {
                int k = reader.nextInt();
                hashTable[hash(k)].add(k);

            }
            reader.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        this.stopWall = System.currentTimeMillis();
        this.stopCPU = System.nanoTime();
        this.wallTime = (this.stopWall - this.startWall) / this.MILSEC;
        this.CPUTime = (this.stopCPU - this.startCPU) / this.NANOSEC;
    }

    public void countCollisions() {
        int i = this.hashTable.length;
        while (i != 0) {
            if (hashTable[i - 1].size() > 1) {
                totalCollisions++;
            }
            if (hashTable[i - 1].size() > mostCollisions) {
                mostCollisionsIndex = i - 1;
                mostCollisions = hashTable[mostCollisionsIndex].size();
            }
            i--;
        }


        System.out.println("Most collisions: " + mostCollisions + " at index " + mostCollisionsIndex);
        System.out.println("Total collisions: " + totalCollisions);
    }

    void printLoadTimes() {
        realFormatter = new DecimalFormat("0.0000");
        System.out.println();
        System.out.println("ELAPSED TIME:");
        System.out.println("Seperate Chaining Hash Table size: "
                + this.hashTable.length + "\nTime: " + this.realFormatter.format(this.CPUTime) + " seconds CPU time    " + this.realFormatter.format(this.wallTime) + " seconds wall clock ");
        System.out.println();
    }

    void writeOutput() {
        try {
            writer = new BufferedWriter(new FileWriter("SCOutput.txt", true));
            System.out.println("Writing to file SCOutput.txt...");
            writer.write("Hash Table Size: " + this.hashTable.length);
            writer.newLine();
            writer.write("a) Time: [" + this.realFormatter.format(this.CPUTime) + "] seconds CPU time, [" + this.realFormatter.format(this.wallTime) + "] seconds wall clock time");
            writer.newLine();
            writer.write("b) Total collisions: " + totalCollisions);
            writer.newLine();
            writer.write("c) Most collisions: " + mostCollisions + " at index " + mostCollisionsIndex);
            writer.newLine();
            writer.write("====================================================================");
            writer.newLine();
            writer.write("Matrix[" + this.hashTable.length + "]:");
            writer.newLine();
            for(int i = 0; i < hashTable.length; i++){
                writer.write("Index " + i + ": " + hashTable[i]);
                writer.newLine();
            }
            writer.write("====================================================================");
            writer.newLine();
            System.out.println("Writing complete.");
            writer.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public static void main(String[] args) {
        SeparateChaining SC = new SeparateChaining(11000);
        SC.getFile();
        SC.loadTable();
        SC.printLoadTimes();
        SC.countCollisions();
        SC.writeOutput();

        SeparateChaining SC2 = new SeparateChaining(15707, SC.getInput());
        SC2.loadTable();
        SC2.printLoadTimes();
        SC2.countCollisions();
        SC2.writeOutput();

        SeparateChaining SC3 = new SeparateChaining(17111, SC.getInput());
        SC3.loadTable();
        SC3.printLoadTimes();
        SC3.countCollisions();
        SC3.writeOutput();

        SeparateChaining SC4 = new SeparateChaining(25111, SC.getInput());
        SC4.loadTable();
        SC4.printLoadTimes();
        SC4.countCollisions();
        SC4.writeOutput();

        System.out.println("Ending program...");


    }
}