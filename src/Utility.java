import java.util.Arrays;
import java.util.Random;

public class Utility {
    public static Byte[] generateByteData() {
        Byte[] data = new Byte[4];
        byte[] bytes = new byte[4];
        new Random().nextBytes(bytes);
        for (int i = 0; i < 4; i++) {
            data[i] = bytes[i];
        }
        return data;
    }

    public static Byte[] generateByteData(int len) {
        Byte[] data = new Byte[len];
        byte[] bytes = new byte[len];
        new Random().nextBytes(bytes);
        for (int i = 0; i < len; i++) {
            data[i] = bytes[i];
        }
        return data;
    }

    public static int[] generateIntData() {
        int[] data = new int[4];
        for (int i = 0; i < 4; i++) {
            data[i] = new Random().nextInt();
        }
        return data;
    }

    public static int[] generateIntData(int len) {
        int[] data = new int[len];
        for (int i = 0; i < len; i++) {
            data[i] = new Random().nextInt();
        }
        return data;
    }

    public static void printArray(byte[] arr) {
        String str = Arrays.toString(arr);
        System.out.println(str);
    }
}
