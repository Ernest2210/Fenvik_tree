import java.io.*;

public class Main {
    public static void main(String[] args) {
        int[] array = new int[100_000_000];
        try {
            BufferedWriter creatingInfoFile = new BufferedWriter(new FileWriter("creating.txt"));
            BufferedWriter sumInfoFile = new BufferedWriter(new FileWriter("sum.txt"));
            BufferedWriter deleteInfoFile = new BufferedWriter(new FileWriter("delete.txt"));
            BufferedWriter changeInfoFile = new BufferedWriter(new FileWriter("change.txt"));
            BufferedReader reader = new BufferedReader(new FileReader("data.txt"));
            int i = 0;
            while (reader.ready()){
                array[i] = Integer.parseInt(reader.readLine());
                i++;
            }
            long start = System.currentTimeMillis();
            FenvicTree fenvicTree = new FenvicTree(array);
            long time = System.currentTimeMillis() - start;
            writeInfoToFile(creatingInfoFile, time, fenvicTree.COUNT_OF_OPERATIONS);
            fenvicTree.COUNT_OF_OPERATIONS = 0;
            creatingInfoFile.close();

            long sumAverageTime = 0;
            long sumAverageOperations = 0;
            for (int j = 0; j < 100; j++) {
                int l = (int)(Math.random()*(fenvicTree.length() - 1)+1);
                int r = (int)(Math.random()*(fenvicTree.length() - 1)+1);
                start = System.currentTimeMillis();
                fenvicTree.getSum(l, r);
                time = System.currentTimeMillis() - start;
                sumAverageTime += time;
                sumAverageOperations += fenvicTree.COUNT_OF_OPERATIONS;
                fenvicTree.COUNT_OF_OPERATIONS = 0;
            }
            writeInfoToFile(sumInfoFile, sumAverageTime/100, sumAverageOperations/100);
            sumInfoFile.close();

            long setAverageTime = 0;
            long setAverageOperations = 0;
            for (int j = 0; j < 100; j++) {
                int indx = (int)(Math.random()*(fenvicTree.length() - 1)+1);
                start = System.currentTimeMillis();
                fenvicTree.set(indx, (int)(Math.random()*10000));
                time = System.currentTimeMillis() - start;
                setAverageTime += time;
                setAverageOperations += fenvicTree.COUNT_OF_OPERATIONS;
                fenvicTree.COUNT_OF_OPERATIONS = 0;
            }
            writeInfoToFile(changeInfoFile, setAverageTime/100, setAverageOperations/100);
            changeInfoFile.close();

            long deleteAverageTime = 0;
            long deleteAverageOperations = 0;
            for (int j = 0; j < 100; j++) {
                int indx = (int)(1 + Math.random()*(fenvicTree.length()));
                start = System.currentTimeMillis();
                fenvicTree.deleteElement(indx);
                time = System.currentTimeMillis() - start;
                deleteAverageTime += time;
                deleteAverageOperations += fenvicTree.COUNT_OF_OPERATIONS;
                fenvicTree.COUNT_OF_OPERATIONS = 0;
            }
            writeInfoToFile(deleteInfoFile, deleteAverageTime/100, deleteAverageOperations/100);
            deleteInfoFile.close();

            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeInfoToFile(BufferedWriter writer, long time, long operations){
        try {
            writer.write(Long.toString(time));
            writer.write(":");
            writer.write(Long.toString(operations));
            writer.write("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


