import java.util.ArrayList;
import java.util.Collections;

public interface Card {
    byte[] getCom(int size);
    int setCom(byte[] data);
}

class Ethernet {
    private ArrayList<Byte> memory = new ArrayList<>();

    public Ethernet() {

    }

    public Ethernet(Byte[] data) {
        Collections.addAll(memory, data);
    }

    public Byte[] read(Integer size) {
        Byte [] data = new Byte[size];
        for (int i = 0; i < size; i++) {
            data[i] = memory.get(i);
        }
        return data;
    }

    public int write(Byte[] data) {
        int size = data.length;
        for (int i = 0; i < size; i++) {
            memory.add(data[i]);
        }
        return 0;
    }
}


class TokenRing {
    private int[] memory = new int[100];

    public int[] receive(int size) {
        int [] data = new int[size];
        for (int i = 0; i < size; i++) {
            data[i] = memory[i];
        }
        return data;
    }

    public int send(int[] data, int size) {
        for (int i = 0; i < size; i++) {
            memory[i] = data[i];
        }
        return 0;
    }
}

/**Adapter is used to enable a TokenRing given as a receiver to a task. It is not possible to give a TokenRing as a parameter to a task.*/
class TokenRingAdapter implements Card {
    private TokenRing tokenRing;

    public TokenRingAdapter(TokenRing tokenRing) {
        this.tokenRing = tokenRing;
    }

    @Override
    public byte[] getCom(int size) {
        byte[] convertedData = new byte[size];
        int[] data = tokenRing.receive(size);
        for (int i = 0; i < size; i++) {
            convertedData[i] = (byte) data[i];
        }
        return convertedData;
    }

    @Override
    public int setCom(byte[] data) {
        int size = data.length;
        int[] convertedData = new int[size];
        for (int i = 0; i < size; i++) {
            convertedData[i] = data[i];
        }
        tokenRing.send(convertedData, size);
        return 0;
    }
}

class EthernetAdapter implements Card {
    private Ethernet ethernet;

    public EthernetAdapter (Ethernet ethernet) {
        this.ethernet = ethernet;
    }

    @Override
    public byte[] getCom(int size) {
        byte[] convertedData = new byte[size];
        Byte[] data = ethernet.read(size);
        for (int i = 0; i < size; i++) {
            convertedData[i] = data[i];
        }
        return convertedData;
    }

    @Override
    public int setCom(byte[] data) {
        Integer size = data.length;
        Byte[] convertedData = new Byte[size];
        for (int i = 0; i < size; i++) {
            convertedData[i] = data[i];
        }
        ethernet.write(convertedData);
        return 0;
    }
}
