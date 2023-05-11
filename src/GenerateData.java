import java.io.*;

public class GenerateData {
    public static void main(String[] args) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("data.txt"));
            for (int j = 0; j < 1; j++) {
            for (long i = 0; i < 100_000_000; i++) {
                int num = (int) (Math.random()*10000);
                writer.write(Integer.toString(num));
                writer.write("\n");
            }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
