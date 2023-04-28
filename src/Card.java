import java.util.Random;

interface IEthernet {
    Byte[] read (Integer size);
    int write (Byte[] data);
}

class Ethernet implements IEthernet {
    private Byte[] memory = new Byte[100];

    public Ethernet() {

    }

    public Ethernet(byte[] data) {
        for (int i = 0; i < data.length; i++) {
            memory[i]=data[i];
        }
    }

    public Byte[] read(Integer size) {
        Byte [] data = new Byte[size];
        for (int i = 0; i < size; i++) {
            data[i] = memory[i];
        }
        return data;
    }

    public int write(Byte[] data) {
        return -1;
    }
}

interface ITokenRing {
    int[] receive(int size);
    int send(int[] data, int size);
}

class TokenRing implements ITokenRing {
    private int[] memory = new int[100];

    public int[] receive(int size) {
        int [] data = new int[size];
        for (int i = 0; i < size; i++) {
            data[i] = memory[i];
        }
        return data;
    }

    public int send(int[] data, int size) {
        return -1;
    }
}

class EthernetToTokenRingAdapter implements IEthernet {
    private TokenRing tokenRing;

    public EthernetToTokenRingAdapter(TokenRing tokenRing) {
        this.tokenRing = tokenRing;
    }

    @Override
    public Byte[] read(Integer size) {
        int[] data = tokenRing.receive(size);
        Byte[] convertedData = new Byte[size];
        for (int i = 0; i < size; i++) {
            convertedData[i] = (byte) data[i];
        }
        return convertedData;
    }

    @Override
    public int write(Byte[] data) {
        int size = data.length;
        int[] convertedData = new int[size];
        for (int i = 0; i < size; i++) {
            convertedData[i] = data[i];
        }
        tokenRing.send(convertedData, size);
        return -1;
    }
}

public class Card {
    private IEthernet ethernet;

    public Card(IEthernet card) {
        this.ethernet = card;
    }

    public byte[] getCom(int size) {
        byte[] convertedData = new byte[size];
        Byte[] data = ethernet.read(size);
        for (int i = 0; i < size; i++) {
            convertedData[i] = data[i];
        }
        return convertedData;
    }

    public int setCom(byte[] data) {
        return -1;
    }
}
