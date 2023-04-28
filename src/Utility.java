import java.util.Arrays;
import java.util.Random;

public class Utility {
    public static byte[] generateData() {
        byte[] data = new byte[4];
        new Random().nextBytes(data);
        return data;
    }

    public static byte[] generateData(int len) {
        byte[] data = new byte[len];
        new Random().nextBytes(data);
        return data;
    }

    public static void printArray(byte[] arr) {
        String str = Arrays.toString(arr);
        System.out.println(str);
    }
}
