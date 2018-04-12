import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;


public class P5Sort {
    BufferedWriter writer;
    DecimalFormat realFormatter;
    private final double MILSEC = 1000;
    private final double NANOSEC = 1000000000;
    long startWall, startCPU, stopWall, stopCPU;
    double intBubbleWall, intBubbleCPU, stringBubbleWall, stringBubbleCPU, stringSelectionWall,
            stringSelectionCPU, intSelectionWall, intSelectionCPU, stringJWall, stringJCPU, intJWall, intJCPU;
    Scanner userInput;
    File stringInputFile, intInputFile;
    ArrayList<String> stringList;
    ArrayList<Integer> intList;
    Comparator<String> c = (o1, o2) -> {
        if (o1.compareTo(o2) == 0) {
            return 0;
        }
        if (o1.toUpperCase().compareTo(o2.toUpperCase()) > 0) {
            return 1;
        }

        if (o1.toUpperCase().compareTo(o2.toUpperCase()) < 0) {
            return -1;
        }

        return o1.toUpperCase().compareTo(o2.toUpperCase());
    };

    Comparator<Integer> n = (o1, o2) -> {
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

    public P5Sort() {
        stringList = new ArrayList<>();
        intList = new ArrayList<>();
        try {
            writer = new BufferedWriter(new FileWriter("P5Output.txt"));
            writer.write("Khamunetri Clark\n");
            writer.newLine();
            writer.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void getFile() {
        stringList.clear();
        intList.clear();

        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Please enter the name of your STRING input file.");
            stringInputFile = new File(br.readLine());
            System.out.println("Please enter the name of your INT input file.");
            intInputFile = new File(br.readLine());
            if(stringInputFile.exists() && intInputFile.exists()){
                System.out.println("String input file: " + stringInputFile.getName() + "\n"
                        + "Integer input file: " + intInputFile.getName() + "\n");
                userInput.close();
            }else if(!stringInputFile.exists()){
                System.out.println("Input file not found, please try again.");
                getFile();
            }else if(!intInputFile.exists()){
                System.out.println("Input file not found, please try again. ");
                getFile();
            }
            userInput.close();
        }catch (Exception e){

        }
    }

    public void loadLists() {
        System.out.println("Loading lists...");
        if (!intInputFile.exists()||!stringInputFile.exists()) {
            System.out.println("Error reading input, please choose another file...");
            getFile();
        }
        intList.clear();
        stringList.clear();
        try {
            Scanner fileScanner = new Scanner(new BufferedReader(new FileReader(intInputFile)));
            while (fileScanner.hasNextInt()) {
                appendIntList(fileScanner.nextInt());
            }
            fileScanner.close();
            fileScanner = new Scanner(new BufferedReader(new FileReader(stringInputFile)));
            while (fileScanner.hasNext()) {
                String sInput = fileScanner.next();
                appendStringList(sInput);
            }
            fileScanner.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        if(intList.size() > 0 && stringList.size() > 0) {
            System.out.println("Lists loaded!");
        }
    }

    public void appendStringList(String s) {
        stringList.add(s);
    }

    public void appendIntList(int n) {
        intList.add(n);

    }

    void stringSwap(ArrayList<String> list, int m, int n) {
        String temp = list.get(m);
        list.set(m, list.get(n));
        list.set(n, temp);
    }

    void intSwap(ArrayList<Integer> list, int m, int n) {
        int temp = list.get(m);
        list.set(m, list.get(n));
        list.set(n, temp);
    }

    void intJavaSort(ArrayList<Integer> list){
        System.out.println("Java sorting...");
        startWall = System.currentTimeMillis();
        startCPU = System.nanoTime();
        list.sort(n);
        stopWall = System.currentTimeMillis();
        stopCPU = System.nanoTime();
        intJWall = (stopWall - startWall) / MILSEC;
        intJCPU = (stopCPU - startCPU) / NANOSEC;
    }

    void stringJavaSort(ArrayList<String> list) {
        System.out.println("Java sorting...");
        startWall = System.currentTimeMillis();
        startCPU = System.nanoTime();
        list.sort(c);
        stopWall = System.currentTimeMillis();
        stopCPU = System.nanoTime();
        stringJWall = (stopWall - startWall) / MILSEC;
        stringJCPU = (stopCPU - startCPU) / NANOSEC;

    }

    void stringSelectionSort(ArrayList<String> list) {
        System.out.println("Selection sorting...");
        startWall = System.currentTimeMillis();
        startCPU = System.nanoTime();
        for (int i = 0; i < list.size(); i++) {
            int min = i;
            for (int j = i + 1; j < list.size(); j++) {
                if (list.get(j).toUpperCase().compareTo(list.get(min).toUpperCase()) < 0) {
                    min = j;
                }
            }
            stringSwap(list, i, min);
        }
        stopWall = System.currentTimeMillis();
        stopCPU = System.nanoTime();
        stringSelectionWall = (stopWall - startWall) / MILSEC;
        stringSelectionCPU = (stopCPU - startCPU) / NANOSEC;
    }

    void intSelectionSort(ArrayList<Integer> list) {
        System.out.println("Selection sorting...");
        startWall = System.currentTimeMillis();
        startCPU = System.nanoTime();
        for (int i = 0; i < list.size(); i++) {
            int min = i;
            for (int j = i + 1; j < list.size(); j++) {
                if (list.get(j) < list.get(min)) {
                    min = j;
                }
            }
            intSwap(list, i, min);
        }
        stopWall = System.currentTimeMillis();
        stopCPU = System.nanoTime();
        intSelectionWall = (stopWall - startWall) / MILSEC;
        intSelectionCPU = (stopCPU - startCPU) / NANOSEC;
    }

    void stringBubbleSort(ArrayList<String> list) {
        System.out.println("Bubble sorting...");
        startWall = System.currentTimeMillis();
        startCPU = System.nanoTime();
        for (int i = 1; i < list.size() - 1; i++) {
            for (int j = 0; j < list.size() - 1; j++) {
                if (list.get(j).toUpperCase().compareTo(list.get(j + 1).toUpperCase()) > 0) {
                    stringSwap(list, j, j + 1);

                }
            }
        }
        stopWall = System.currentTimeMillis();
        stopCPU = System.nanoTime();
        stringBubbleWall = (stopWall - startWall) / MILSEC;
        stringBubbleCPU = (stopCPU - startCPU) / NANOSEC;
    }

    void intBubbleSort(ArrayList<Integer> list) {
        System.out.println("Bubble sorting...");
        startWall = System.currentTimeMillis();
        startCPU = System.nanoTime();
        for (int i = 1; i < list.size() - 1; i++) {
            for (int j = 0; j < list.size() - 1; j++) {
                if (list.get(j) > list.get(j + 1)) {
                    intSwap(list, j, j + 1);

                }
            }
        }
        stopWall = System.currentTimeMillis();
        stopCPU = System.nanoTime();
        intBubbleWall = (stopWall - startWall) / MILSEC;
        intBubbleCPU = (stopCPU - startCPU) / NANOSEC;
    }

    void runSorts() {
        if (intList.size() > 0) {
            intBubbleSort(intList);
            System.out.println("Bubble sort complete!");
            //uncomment to see sorted lists
            for (int i = 0; i < intList.size(); i++) {
                System.out.println(intList.get(i));
            }
            loadLists(); //ensures each new load is random
            intSelectionSort(intList);
            System.out.println("Selection sort complete!");
            //uncomment to see sorted lists
            for (int i = 0; i < intList.size(); i++) {
                System.out.println(intList.get(i));
            }
            loadLists();
            intJavaSort(intList);
            System.out.println("Java sort complete!");
            //uncomment to see sorted lists
            for (int i = 0; i < intList.size(); i++) {
                System.out.println(intList.get(i));

            }
        }else if(intList.size() == 0){
            System.out.println("Lists are empty. Check input file");
            getFile();
        }
//        }   else if (stringList.size() > 0 && intList.size() == 0) {
//            stringBubbleSort(stringList);
//            System.out.println("Bubble sort complete!");
//            //uncomment to see sorted lists
////            for (int i = 0; i < stringList.size(); i++) {
////                System.out.println(intList.get(i));
////            }
//            loadLists(inputFile);
//            stringSelectionSort(stringList);
//            System.out.println("Selection sort complete!");
//            //uncomment to see sorted lists
////            for (int i = 0; i < stringList.size(); i++) {
////                System.out.println(stringList.get(i));
////            }
//            loadLists(inputFile);
//            stringJavaSort(stringList);
//            System.out.println("Java Sort complete!");
//            //uncomment to see sorted lists
////            for (int i = 0; i < stringList.size(); i++) {
////                System.out.println(stringList.get(i));
////            }
//
//        }

    }

    void writeSorts() {
        if (stringInputFile == null || !stringInputFile.exists()) {
            System.out.println("Invalid file, please choose another");
            getFile();
        }
        if (stringList.size() == 0) {
            System.out.println("Lists are empty. Check input file");
            getFile(); // if lists are empty, it is because they could not be read in from file
        }
//            writeIntSorts();
        writeStringSorts();
    }

    void writeIntSorts(){
        realFormatter = new DecimalFormat("0.0000");
        DecimalFormat sizeFormat = new DecimalFormat("0,000");
        System.out.println("Writing to file...");
        try {
            writer = new BufferedWriter(new FileWriter("P5Output.txt", true));
            writer.write("INTEGER SORT");
            writer.newLine();
            writer.write("There are " + sizeFormat.format(intList.size()) + " numbers in the list.");
            writer.newLine();
            writer.write("Bubble Sort:      " + this.realFormatter.format(intBubbleCPU) +    " seconds CPU time    " + this.realFormatter.format(intBubbleWall) + " seconds wall clock ");
            writer.newLine();
            writer.write("Selection Sort:   " + this.realFormatter.format(intSelectionCPU) + " seconds CPU time    " + this.realFormatter.format(intSelectionWall) + " seconds wall clock ");
            writer.newLine();
            writer.write("Java sort:        " + this.realFormatter.format(intJCPU) +         " seconds CPU time    " + this.realFormatter.format(intJWall) + " seconds wall clock ");
            writer.newLine();
            writer.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Writing complete!");
    }

    void writeStringSorts(){
        realFormatter = new DecimalFormat("0.0000");
        DecimalFormat sizeFormat = new DecimalFormat("0,000");
        System.out.println("Sorting string inputs and writing to file...");
        stringBubbleSort(stringList);
        System.out.println("Bubble sort complete!");
        loadLists();
        stringSelectionSort(stringList);
        System.out.println("Selection sort complete!");
        loadLists();
        stringJavaSort(stringList);
        System.out.println("Java sort complete!");
        try {
            writer = new BufferedWriter(new FileWriter("P5Output.txt", true));
            writer.write("STRING SORT");
            writer.newLine();
            writer.write("There are " + sizeFormat.format(stringList.size()) + " words in the list.");
            writer.newLine();
            writer.write("Bubble Sort:      " + this.realFormatter.format(stringBubbleCPU) +    " seconds CPU time    " + this.realFormatter.format(stringBubbleWall) + " seconds wall clock ");
            writer.newLine();
            writer.write("Selection Sort:   " + this.realFormatter.format(stringSelectionCPU) + " seconds CPU time    " + this.realFormatter.format(stringSelectionWall) + " seconds wall clock ");
            writer.newLine();
            writer.write("Java sort:        " + this.realFormatter.format(stringJCPU) +         " seconds CPU time    " + this.realFormatter.format(stringJWall) + " seconds wall clock ");
            writer.newLine();
            writer.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Writing complete!");
    }

    void printIntSorts(){
        realFormatter = new DecimalFormat("0.0000");
        System.out.println();
        System.out.println("ELAPSED TIME:");
        System.out.println("Integer Bubble Sort:      " + this.realFormatter.format(intBubbleCPU) +    " seconds CPU time    " + this.realFormatter.format(intBubbleWall) + " seconds wall clock ");
        System.out.println();
        System.out.println("Integer Selection Sort:   " + this.realFormatter.format(intSelectionCPU) + " seconds CPU time    " + this.realFormatter.format(intSelectionWall) + " seconds wall clock ");
        System.out.println();
        System.out.println("Integer Java Sort:        " + this.realFormatter.format(intJCPU) +         " seconds CPU time    " + this.realFormatter.format(intJWall) + " seconds wall clock ");
        System.out.println();
    }
    public static void main(String[] args) {
        P5Sort run = new P5Sort();
        System.out.println("P5 Sorts: given an input file, test the performance of a number of sorts and save the output.");
        try {

            run.getFile();
            run.loadLists();
            if(run.intList.size() == 0 || run.stringList.size() == 0){
                while(run.intList.size() == 0 || run.stringList.size() == 0) {
                    System.out.println("Loading failed, check your input file.");
                    run.intInputFile = null;
                    run.stringInputFile = null;
                    run.getFile();
                    run.loadLists();
                }
            }
            run.runSorts();
            run.printIntSorts();
            run.writeSorts();
            System.out.println("Exiting program. Check File P5Output.txt for results.");


        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}

