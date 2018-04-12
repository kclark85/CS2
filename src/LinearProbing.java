import java.io.*;
import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.Scanner;

public class LinearProbing {

    DecimalFormat realFormatter;
    int M;
    int totalCollisions;
    int collisionDist;
    int furthestCollision;
    int furthestCollisionIndex;
    int furthestCollisionValue;
    int newIndex;
    private final double MILSEC = 1000;
    private final double NANOSEC = 1000000000;
    long startWall, startCPU, stopWall, stopCPU;
    double wallTime, CPUTime;
    int[] hashTable;
    Scanner userInput;
    File input;
    Scanner reader;
    BufferedWriter writer;

    public LinearProbing(int M, File input){
        this.M = M;
        this.input = input;
        this.totalCollisions = 0;
        furthestCollision = 0;
        furthestCollisionIndex = 0;
        furthestCollisionValue = 0;
        this.hashTable = new int[M];
    }

    public LinearProbing(int M){
        this.M = M;
        this.totalCollisions = 0;
        furthestCollision = 0;
        furthestCollisionIndex = 0;
        furthestCollisionValue = 0;
        this.hashTable = new int[M];
        try{
            writer = new BufferedWriter(new FileWriter("LPOutput.txt"));
            writer.write("Khamunetri Clark");
            writer.newLine();
            writer.write("====================================================================");
            writer.newLine();
            writer.close();
        }
        catch(Exception e){
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

    public File getInput(){
        return this.input;
    }

    private int hash(int k){
        int hash = k % 1231;
        return hash;
    }

    public void loadTable(){
        try {
            reader = new Scanner(new BufferedReader(new FileReader(input)));
            if(!reader.hasNextInt()){
                System.out.println("Error: file must consist of integers");
                getFile();
            }
            System.out.println("Loading Table size " +  this.hashTable.length + " and managing collisions by linear probing...");
            this.startWall = System.currentTimeMillis();
            this.startCPU = System.nanoTime();
            while(reader.hasNextInt()){
                int k = reader.nextInt();
                newIndex = 0;
                if(hashTable[hash(k)] != 0){ //gather data on collisions
                    getCollisionData(k);
                    hashTable[newIndex] = k; //send value to next open index
                }
                else {
                    hashTable[hash(k)] = k; //if no collision
                }
            }
            reader.close();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        this.stopWall = System.currentTimeMillis();
        this.stopCPU = System.nanoTime();
        this.wallTime = (this.stopWall - this.startWall) / this.MILSEC;
        this.CPUTime = (this.stopCPU - this.startCPU) / this.NANOSEC;
    }
    void getCollisionData(int value){
        int k = value;
        this.totalCollisions++;
        for(int i = hash(k); i < this.hashTable.length; i++){
            collisionDist++;
            if(this.hashTable[i] == 0) {
                System.out.println(collisionDist);
                checkFurthestCollision(k);
                newIndex = i;
                collisionDist = 0;
                break;
            }
        }
    }

    void checkFurthestCollision(int value){
        if(collisionDist > furthestCollision){
            furthestCollision = collisionDist;
            furthestCollisionIndex = hash(value);
            furthestCollisionValue = value;
        }
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
            writer = new BufferedWriter(new FileWriter("LPOutput.txt", true));
            System.out.println("Writing to file SCOutput.txt...");
            writer.write("Hash Table Size: " + this.hashTable.length);
            writer.newLine();
            writer.write("a) Time: [" + this.realFormatter.format(this.CPUTime) + "] seconds CPU time, [" + this.realFormatter.format(this.wallTime) + "] seconds wall clock time");
            writer.newLine();
            writer.write("b) Total collisions: " + totalCollisions);
            writer.newLine();
            writer.write("c) \"Furthest\" collision at index " + furthestCollisionIndex + " with value " + furthestCollisionValue + " of size " + furthestCollision );
            writer.newLine();
            writer.write("====================================================================");
            writer.newLine();
            writer.write("Matrix[" + this.hashTable.length + "]:");
            writer.newLine();
            for(int i = 0; i < hashTable.length; i++){
                writer.write("Index " + i + ": [" + hashTable[i] + "]");
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
        LinearProbing LP = new LinearProbing(11000);
        LP.getFile();
        LP.loadTable();
        LP.printLoadTimes();
        LP.writeOutput();
    }
}
