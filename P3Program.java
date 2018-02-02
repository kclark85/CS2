
        import java.io.BufferedWriter;
        import java.io.FileWriter;
        import java.text.DecimalFormat;
        import java.util.ArrayList;
        import java.util.Arrays;
        import java.util.Random;

//TO-DO: Ensure proper format, output to a file

public class P3Program {
    DecimalFormat realFormatter;
    Random randomGenerator;
    int size = 1000000;
    int bound = 10000000;
    private int milSec = 1000;
    private int nanoSec = 1000000000;
    long startRun, stopRun;
    double totalTime;
    ArrayList<Integer> intList = new ArrayList<>(size);
    ArrayList<Float> floatList = new ArrayList<>(size);
    int[] intArray = new int[size];
    float[] floatArray = new float[size];
    BufferedWriter writer;

    public P3Program() {
        realFormatter = new DecimalFormat("000.0000");
        randomGenerator = new Random();
        try {
            writer = new BufferedWriter(new FileWriter("P3Output.txt"));
            writer.write("Khamunetri Clark");
            writer.newLine();
            writer.newLine();
            writer.close();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        startRun = System.nanoTime(); //used to calculate total time later

    }

    void fill() {
        DecimalFormat formatSize = new DecimalFormat( "0,000"); //for style
        try {
            writer = new BufferedWriter(new FileWriter("P3Output.txt",true));
            writer.write("-----------------------------------------------------");
            writer.newLine();
            writer.write("FILL: Time taken to fill each list with " + formatSize.format(size) + " elements:" );
            writer.newLine();
            writer.newLine();
            writer.close();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        System.out.println("FILL: Time taken to fill each list with " + formatSize.format(size) + " elements:" );
        long startWall, startCPU, stopWall, stopCPU;
        double intArrayTimeWall, intArrayTimeCPU, floatArrayTimeWall, floatArrayTimeCPU,
                intListTimeWall, intListTimeCPU, floatListTimeWall, floatListTimeCPU;
        for(int j = 0; j < 5; j++) {
            try{
                writer = new BufferedWriter(new FileWriter("P3Output.txt", true));
                writer.write("FILL RUN #: " + (j + 1) + '\n');
                writer.newLine();
                writer.close();
            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }
            System.out.println("FILL RUN #: " + (j + 1) + '\n');
            //fill int array
            startWall = System.currentTimeMillis();
            startCPU = System.nanoTime();
            for (int i = 0; i < size; i++) {
                intArray[i] = randomGenerator.nextInt(bound);
            }
            stopWall = System.currentTimeMillis();
            stopCPU = System.nanoTime();
            intArrayTimeWall = ((double) stopWall - (double) startWall) / milSec;
            intArrayTimeCPU = ((double) stopCPU - (double) startCPU) / nanoSec;
            //fill int array list
            startWall = System.currentTimeMillis();
            startCPU = System.nanoTime();
            for (int i = 0; i < size; i++) {
                intList.add(i, randomGenerator.nextInt(bound));
            }
            stopWall = System.currentTimeMillis();
            stopCPU = System.nanoTime();
            intListTimeWall = ((double) stopWall - (double) startWall) / milSec;
            intListTimeCPU = ((double) stopCPU - (double) startCPU) / nanoSec;
            //fill float array
            startWall = System.currentTimeMillis();
            startCPU = System.nanoTime();
            for (int i = 0; i < size; i++) {
                floatArray[i] = randomGenerator.nextFloat() * bound;
            }
            stopWall = System.currentTimeMillis();
            stopCPU = System.nanoTime();
            floatArrayTimeWall = ((double) stopWall - (double) startWall) / milSec;
            floatArrayTimeCPU = ((double) stopCPU - (double) startCPU) / nanoSec;
            //fill float array list
            startWall = System.currentTimeMillis();
            startCPU = System.nanoTime();
            for (int i = 0; i < size; i++) {
                floatList.add(i, randomGenerator.nextFloat() * bound);
            }
            stopWall = System.currentTimeMillis();
            stopCPU = System.nanoTime();
            floatListTimeWall = ((double) stopWall - (double) startWall) / milSec;
            floatListTimeCPU = ((double) stopCPU - (double) startCPU) / nanoSec;
            System.out.println("INTEGER ARRAY" + "\n" + "Wall clock: " + realFormatter.format(intArrayTimeWall) + " seconds" + "\n" + "CPU time: " + realFormatter.format(intArrayTimeCPU) + " seconds");
            System.out.println("INTEGER ARRAYLIST" + "\n" + "Wall clock: " + realFormatter.format(intListTimeWall) + " seconds" + "\n" + "CPU time: " + realFormatter.format(intListTimeCPU) + " seconds");
            System.out.println("FLOAT ARRAY" + "\n" + "Wall clock: " + realFormatter.format(floatArrayTimeWall) + " seconds" + "\n" + "CPU time: " + realFormatter.format(floatArrayTimeCPU) + " seconds");
            System.out.println("FLOAT ARRAYLIST" + "\n" + "Wall clock: " + realFormatter.format(floatListTimeWall) + " seconds" + "\n" + "CPU time: " + realFormatter.format(floatListTimeCPU) + " seconds");
            System.out.println();
            try {
                writer = new BufferedWriter(new FileWriter("P3Output.txt", true));
                writer.write("INTEGER ARRAY: " + "\n" + "Wall clock: " + realFormatter.format(intArrayTimeWall) + " seconds " + "\n" + "CPU time: " + realFormatter.format(intArrayTimeCPU) + " seconds");
                writer.newLine();
                writer.write("INTEGER ARRAYLIST: " + "\n" + "Wall clock: " + realFormatter.format(intListTimeWall) + " seconds " + "\n" + "CPU time: " + realFormatter.format(intListTimeCPU) + " seconds");
                writer.newLine();
                writer.write("FLOAT ARRAY: " + "\n" + "Wall clock: " + realFormatter.format(floatArrayTimeWall) + " seconds " + "\n" + "CPU time: " + realFormatter.format(floatArrayTimeCPU) + " seconds");
                writer.newLine();
                writer.write("FLOAT ARRAYLIST: " + "\n" + "Wall clock: " + realFormatter.format(floatListTimeWall) + " seconds " + "\n" + "CPU time: " + realFormatter.format(floatListTimeCPU) + " seconds");
                writer.newLine();
                writer.newLine();
                writer.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            //clear out values after each loop
            Arrays.fill(intArray, 0);
            Arrays.fill(floatArray, 0);
            intList.clear();
            floatList.clear();

        }


    }

    void increment() {
        try {
            writer = new BufferedWriter(new FileWriter("P3Output.txt", true));
            writer.write("-----------------------------------------------------");
            writer.newLine();
            writer.write("INCREMENT: Time taken to increment each element by 1: ");
            writer.newLine();
            writer.newLine();
            writer.close();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        System.out.println("INCREMENT: Time taken to increment each element by 1:");
        long startWall, startCPU, stopWall, stopCPU;
        double intArrayTimeWall, intArrayTimeCPU, floatArrayTimeWall, floatArrayTimeCPU,
                intListTimeWall, intListTimeCPU, floatListTimeWall, floatListTimeCPU;
        for(int j = 0; j < 5; j ++) { //run loops enough times to get an accurate reading

            for (int i = 0; i < size; i++) {    //fill the arrays and arraylists
                intArray[i] = randomGenerator.nextInt(bound);
                floatArray[i] = randomGenerator.nextFloat()*bound;
                intList.add(i, randomGenerator.nextInt(bound));
                floatList.add(i, randomGenerator.nextFloat()*bound);
            }
            try{
                writer = new BufferedWriter(new FileWriter("P3Output.txt", true));
                writer.write("INCREMENT RUN # " + (j+1) + '\n');
                writer.newLine();
                writer.close();
            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }

            System.out.println("INCREMENT RUN # " + (j+1) + '\n');
            //time int array
            startWall = System.currentTimeMillis();
            startCPU = System.nanoTime();
            for (int i = 0; i < size; i++) {
                intArray[i]++;
            }
            stopWall = System.currentTimeMillis();
            stopCPU = System.nanoTime();
            intArrayTimeWall = ((double)stopWall - (double)startWall)/milSec;
            intArrayTimeCPU = ((double)stopCPU - (double)startCPU)/nanoSec;

            //time float array
            startWall = System.currentTimeMillis();
            startCPU = System.nanoTime();
            for (int i = 0; i < size; i++) {
                floatArray[i]++;
            }
            stopWall = System.currentTimeMillis();
            stopCPU = System.nanoTime();
            floatArrayTimeWall = ((double)stopWall - (double)startWall)/milSec;
            floatArrayTimeCPU = ((double)stopCPU - (double)startCPU)/nanoSec;

            //time int array list
            startWall = System.currentTimeMillis();
            startCPU = System.nanoTime();
            for (int i = 0; i < size; i++) {
                int temp = intList.get(i);
                temp++;
                intList.set(i, temp);
            }
            stopWall = System.currentTimeMillis();
            stopCPU = System.nanoTime();
            intListTimeWall = ((double)stopWall - (double)startWall)/milSec;
            intListTimeCPU = ((double)stopCPU - (double)startCPU)/nanoSec;

            //time float array list
            startWall = System.currentTimeMillis();
            startCPU = System.nanoTime();
            for (int i = 0; i < size; i++) {
                float temp = floatList.get(i);
                temp++;
                floatList.set(i, temp);
            }
            stopWall = System.currentTimeMillis();
            stopCPU = System.nanoTime();
            floatListTimeWall = ((double)stopWall - (double)startWall)/milSec;
            floatListTimeCPU = ((double)stopCPU - (double)startCPU)/nanoSec;

            System.out.println("INTEGER ARRAY: " + "\n" + "Wall clock: " + realFormatter.format(intArrayTimeWall) + " seconds" + "\n" + "CPU time: " + realFormatter.format(intArrayTimeCPU) + " seconds");
            System.out.println("INTEGER ARRAY LIST: " + "\n" + "Wall clock: " + realFormatter.format(intListTimeWall) + " seconds" + "\n" + "CPU time: " + realFormatter.format(intListTimeCPU) + " seconds");
            System.out.println("FLOAT ARRAY: " + "\n" + "Wall clock: " + realFormatter.format(floatArrayTimeWall) + " seconds" + "\n" + "CPU time: " + realFormatter.format(floatArrayTimeCPU) + " seconds");
            System.out.println("FLOAT ARRAY LIST: " + "\n" + "Wall clock: " + realFormatter.format(floatListTimeWall) + " seconds" + "\n" + "CPU time: " + realFormatter.format(floatListTimeCPU) + " seconds");
            try {
                writer = new BufferedWriter(new FileWriter("P3Output.txt", true));
                writer.write("INTEGER ARRAY: " + "\n" + "Wall clock: " + realFormatter.format(intArrayTimeWall) + " seconds " + "\n" + "CPU time: " + realFormatter.format(intArrayTimeCPU) + " seconds");
                writer.newLine();
                writer.write("INTEGER ARRAYLIST: " + "\n" + "Wall clock: " + realFormatter.format(intListTimeWall) + " seconds " + "\n" + "CPU time: " + realFormatter.format(intListTimeCPU) + " seconds");
                writer.newLine();
                writer.write("FLOAT ARRAY: " + "\n" + "Wall clock: " + realFormatter.format(floatArrayTimeWall) + " seconds " + "\n" + "CPU time: " + realFormatter.format(floatArrayTimeCPU) + " seconds");
                writer.newLine();
                writer.write("FLOAT ARRAYLIST: " + "\n" + "Wall clock: " + realFormatter.format(floatListTimeWall) + " seconds " + "\n" + "CPU time: " + realFormatter.format(floatListTimeCPU) + " seconds");
                writer.newLine();
                writer.newLine();
                writer.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            //clear out values after each loop
            Arrays.fill(intArray, 0);
            Arrays.fill(floatArray, 0);
            intList.clear();
            floatList.clear();


        }



    }

    void runtime(){
        stopRun = System.nanoTime();
        totalTime = ((double)stopRun - (double)this.startRun)/nanoSec;
        try{
            writer = new BufferedWriter(new FileWriter("P3Output.txt",true));
            writer.write("TOTAL RUN TIME: " + realFormatter.format(totalTime) + " seconds ");
            writer.close();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        System.out.println("TOTAL TIME: " + realFormatter.format(totalTime) + " seconds ");
    }

    public static void main(String[] args) {
        P3Program run = new P3Program();
        run.fill();
        run.increment();
        run.runtime();

    }
}
