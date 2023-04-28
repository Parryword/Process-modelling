import java.util.Random;

public class RAM {
    // 100 bytes of memory (between -128 and 127)
    // Index of the array is the memory location
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
        for (int i = 0; i < data.length; i++) {
            memory[address + i] = data[i];
        }
        return -1;
    }
}
