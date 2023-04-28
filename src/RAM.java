import java.util.Random;

public class RAM {
    private byte[] memory = new byte[100];

    public RAM() {

    }

    public byte[] get(int address, int size) {
        byte[] data = new byte[size - address];
        for (int i = address; i < size; i++) {
            data[i - address] = memory[i];
        }
        return data;
    }

    public int set(byte[] data, int address) {
        try {
            for (int i = 0; i < data.length; i++) {
                memory[address + i] = data[i];
            }
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
            return 1;
        }
        return 0;
    }
}
