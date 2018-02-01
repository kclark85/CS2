import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

//TO-DO: Ensure proper format, output to a file

public class P3Program {
    DecimalFormat realFormatter;
    Random randomGenerator;
    int size = 1000000;
    ArrayList<Integer> intList = new ArrayList<>(size);
    ArrayList<Float> floatList = new ArrayList<>(size);
    int[] intArray = new int[size];
    float[] floatArray = new float[size];

    public P3Program() {
        realFormatter = new DecimalFormat("000.0000");
        randomGenerator = new Random();

    }

    void fill() {
        DecimalFormat formatSize = new DecimalFormat( "0,000"); //for style
        System.out.println("FILL: Time taken to fill each list with " + formatSize.format(size) + " elements:" );
        long startWall, startCPU, stopWall, stopCPU;
        double intArrayTimeWall, intArrayTimeCPU, floatArrayTimeWall, floatArrayTimeCPU,
                    intListTimeWall, intListTimeCPU, floatListTimeWall, floatListTimeCPU;
        for(int j = 0; j < 5; j++) {
            System.out.println("FILL RUN #: " + (j+1) + '\n');
            //fill int array
            startWall = System.currentTimeMillis();
            startCPU = System.nanoTime();
            for (int i = 0; i < size; i++) {
                intArray[i] = randomGenerator.nextInt(10000000);
            }
            stopWall = System.currentTimeMillis();
            stopCPU = System.nanoTime();
            intArrayTimeWall = ((double)stopWall - (double)startWall)/1000;
            intArrayTimeCPU = ((double)stopCPU - (double)startCPU)/1000000000;
            //fill int array list
            startWall = System.currentTimeMillis();
            startCPU = System.nanoTime();
            for (int i = 0; i < size; i++) {
                intList.add(i, randomGenerator.nextInt(10000000));
            }
            stopWall = System.currentTimeMillis();
            stopCPU = System.nanoTime();
            intListTimeWall = ((double)stopWall - (double)startWall)/1000;
            intListTimeCPU = ((double)stopCPU - (double)startCPU)/1000000000;
            //fill float array
            startWall = System.currentTimeMillis();
            startCPU = System.nanoTime();
            for (int i = 0; i < size; i++) {
                floatArray[i] = randomGenerator.nextFloat() * 10000000;
            }
            stopWall = System.currentTimeMillis();
            stopCPU = System.nanoTime();
            floatArrayTimeWall = ((double)stopWall - (double)startWall)/1000;
            floatArrayTimeCPU = ((double)stopCPU - (double)startCPU)/1000000000;
            //fill float array list
            startWall = System.currentTimeMillis();
            startCPU = System.nanoTime();
            for (int i = 0; i < size; i++) {
                floatList.add(i, randomGenerator.nextFloat() * 10000000);
            }
            stopWall = System.currentTimeMillis();
            stopCPU = System.nanoTime();
            floatListTimeWall = ((double)stopWall - (double)startWall)/1000;
            floatListTimeCPU = ((double)stopCPU - (double)startCPU)/1000000000;
            System.out.println("INTEGER ARRAY" + "\n" + "Wall clock: " + realFormatter.format(intArrayTimeWall) + " seconds" + "\n" + "CPU time: " + realFormatter.format(intArrayTimeCPU) + " seconds");
            System.out.println("INTEGER ARRAYLIST" + "\n" + "Wall clock: " + realFormatter.format(intListTimeWall) + " seconds" + "\n" + "CPU time: " + realFormatter.format(intListTimeCPU) + " seconds");
            System.out.println("FLOAT ARRAY" + "\n" + "Wall clock: " + realFormatter.format(floatArrayTimeWall) + " seconds" + "\n" + "CPU time: " + realFormatter.format(floatArrayTimeCPU) + " seconds");
            System.out.println("FLOAT ARRAYLIST" + "\n" + "Wall clock: " + realFormatter.format(floatListTimeWall) + " seconds" + "\n" + "CPU time: " + realFormatter.format(floatListTimeCPU) + " seconds");
            System.out.println();

            //clear out values after each loop
            Arrays.fill(intArray, 0);
            Arrays.fill(floatArray, 0);
            intList.clear();
            floatList.clear();
        }


    }

    void increment() {
        System.out.println("INCREMENT: Time taken to increment each element by 1:");
        long startWall, startCPU, stopWall, stopCPU;
        double intArrayTimeWall, intArrayTimeCPU, floatArrayTimeWall, floatArrayTimeCPU,
                intListTimeWall, intListTimeCPU, floatListTimeWall, floatListTimeCPU;
        for(int j = 0; j < 5; j ++) { //run loops enough times to get an accurate reading

            for (int i = 0; i < size; i++) {    //fill the arrays and arraylists
                intArray[i] = randomGenerator.nextInt(10000000);
                floatArray[i] = randomGenerator.nextFloat()*10000000;
                intList.add(i, randomGenerator.nextInt(10000000));
                floatList.add(i, randomGenerator.nextFloat()*10000000);
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
            intArrayTimeWall = ((double)stopWall - (double)startWall)/1000;
            intArrayTimeCPU = ((double)stopCPU - (double)startCPU)/1000000000;

            //time float array
            startWall = System.currentTimeMillis();
            startCPU = System.nanoTime();
            for (int i = 0; i < size; i++) {
                floatArray[i]++;
            }
            stopWall = System.currentTimeMillis();
            stopCPU = System.nanoTime();
            floatArrayTimeWall = ((double)stopWall - (double)startWall)/1000;
            floatArrayTimeCPU = ((double)stopCPU - (double)startCPU)/1000000000;

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
            intListTimeWall = ((double)stopWall - (double)startWall)/1000;
            intListTimeCPU = ((double)stopCPU - (double)startCPU)/1000000000;

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
            floatListTimeWall = ((double)stopWall - (double)startWall)/1000;
            floatListTimeCPU = ((double)stopCPU - (double)startCPU)/1000000000;
            System.out.println("INTEGER ARRAY: " + "\n" + "Wall clock: " + realFormatter.format(intArrayTimeWall) + " seconds" + "\n" + "CPU time: " + realFormatter.format(intArrayTimeCPU) + " seconds");
            System.out.println("INTEGER ARRAY LIST: " + "\n" + "Wall clock: " + realFormatter.format(intListTimeWall) + " seconds" + "\n" + "CPU time: " + realFormatter.format(intListTimeCPU) + " seconds");
            System.out.println("FLOAT ARRAY: " + "\n" + "Wall clock: " + realFormatter.format(floatArrayTimeWall) + " seconds" + "\n" + "CPU time: " + realFormatter.format(floatArrayTimeCPU) + " seconds");
            System.out.println("FLOAT ARRAY LIST: " + "\n" + "Wall clock: " + realFormatter.format(floatListTimeWall) + " seconds" + "\n" + "CPU time: " + realFormatter.format(floatListTimeCPU) + " seconds");
            //clear out values after each loop
            Arrays.fill(intArray, 0);
            Arrays.fill(floatArray, 0);
            intList.clear();
            floatList.clear();


        }



    }

    public static void main(String[] args) {
        P3Program run = new P3Program();
        run.fill();
        run.increment();
    }
}
