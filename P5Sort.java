import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;


public class P5Sort{
    private final double MILSEC = 1000;
    private final double NANOSEC = 1000000000;
    long startWall, startCPU, stopWall, stopCPU;
    Scanner userInput;
    File inputFile;
    ArrayList<String> stringList;
    ArrayList<Integer> intList;

    public P5Sort(){
        stringList = new ArrayList<>();
        intList = new ArrayList<>();
    }

    public void displayMenu(){ //allow user to chose between inputting file, choose different list sorts, and print data on sorting times

    }

    public void getFile(){
        userInput = new Scanner(System.in);
        System.out.println("Please enter the name of your file.");
        String response = userInput.next();
        if(inputFile != null){
            System.out.println("WARNING: changing input file " + inputFile.getName() + " will clear all list elements." +
                    " Do you want to continue?(Yes/No?)");
            //check user responses
            try {
                if (response.equalsIgnoreCase("No")||response.equalsIgnoreCase("N")){
                    return; // TODO: 2/28/2018 have this bring users back to the menu
                }
                if(!(response.equalsIgnoreCase("No")||response.equalsIgnoreCase("N")
                        ||response.equalsIgnoreCase("Yes")||response.equalsIgnoreCase("Y"))){
                    System.out.println("Invalid userInput, returning to menu...");
                    displayMenu();
                }

            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }
            //end check user userInput
        }
        try {
            inputFile = new File(response);
        }
        catch (NullPointerException e){
            System.out.println(e.getMessage());
        }
        System.out.println("Input file: " + inputFile.getName() +"\n");
    }

    public void loadLists(File in) {
        try {
            Scanner fileScanner = new Scanner(new BufferedReader( new FileReader(in)));
            while(fileScanner.hasNext()) {
                String sInput = fileScanner.next();
                appendStringList(sInput);
            }
            clearNumbers();
            fileScanner.close();
            fileScanner = new Scanner(new BufferedReader( new FileReader(in)));
            while(fileScanner.hasNextInt()){
                appendIntList(fileScanner.nextInt());
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Lists loaded, returning to menu...");
        displayMenu();
    }
    //ensures string list won't load with ints
    void clearNumbers(){
        if(stringList != null) {
            for (int i = 0; i < stringList.size(); ) {
                if (stringList.get(i).contains("0") || stringList.get(i).contains("1") ||
                        stringList.get(i).contains("2") || stringList.get(i).contains("3") ||
                        stringList.get(i).contains("4") || stringList.get(i).contains("5") ||
                        stringList.get(i).contains("6") || stringList.get(i).contains("7") ||
                        stringList.get(i).contains("8") || stringList.get(i).contains("9")) {
                    stringList.remove(i);
                }
                return;
            }
        }
    }

    public void appendStringList(String s) {
        stringList.add(s);
    }

    public void appendIntList(int n) {
        intList.add(n);

    }

    void swap(ArrayList<String> list, int m, int n){
        String temp = list.get(m); //temp = index m of list
        list.set(m, list.get(n)); //list(m) = list.get(n)
        list.set(n, temp);
    }

    void bubbleSort(ArrayList<String> list){
        System.out.println("sorting...");
        for(int i = 1; i < list.size() -1; i++){
            for(int j = 0; j < list.size() -1; j++){
                if(list.get(j).compareTo(list.get(j+1)) > 0){
                    swap(list,j, j+1);

                }
            }
        }
    }


    public static void main(String[] args) {
        P5Sort run = new P5Sort();
        run.getFile();
        run.loadLists(run.inputFile);
        System.out.println(run.intList.size() + " " + run.stringList.size());
        run.bubbleSort(run.stringList);
        for(int i = 0; i < run.stringList.size(); i++) {
            System.out.println(run.stringList.get(i));
        }

    }
}

