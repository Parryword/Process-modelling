import java.util.ArrayList;

/**Computer acts as a facade to run the system. It is possible to manually execute tasks without a computer object by creating CPU and other components separately.*/
public class Computer {
    public Computer(Ethernet ethernetCard, TokenRing tokenRingCard, RAM ram, CPU cpu1, CPU cpu2) {
        this.cpu1 = cpu1;
        this.cpu2 = cpu2;
        this.ram = ram;
        ethernet = new EthernetAdapter(ethernetCard);
        tokenRing = new TokenRingAdapter(tokenRingCard);
    }

    private CPU cpu1, cpu2;
    private RAM ram;
    private Card ethernet, tokenRing;

    // Adds to the task pool
    public void configureTasks(Command... task) {
        CPU.addTask(task);
    }

    // Both CPUs start to operate
    public void runComputer() {
        boolean cp1Run = true, cp2Run = true;
        while (cp1Run && cp2Run) {
            cp1Run = cpu1.operation();
            cp2Run = cpu2.operation();
        }
        System.out.println("System is shutting down...");
    }

    // Task creation methods
    public ReadMemory readMemory(int address, int size) {
        return new ReadMemory(ram, address, size);
    }

    public WriteMemory writeMemory(int address) {
        return new WriteMemory(ram, address);
    }

    public ReadCard readEthernet(int size) {
        return new ReadCard(ethernet, size);
    }

    public WriteCard writeEthernet() {
        return new WriteCard(ethernet);
    }

    public ReadCard readTokenRing(int size) {
        return new ReadCard(tokenRing, size);
    }

    public WriteCard writeTokenRing() {
        return new WriteCard(tokenRing);
    }

    // Getters and Setters
    public CPU getCpu1() {
        return cpu1;
    }

    public void setCpu1(CPU cpu1) {
        this.cpu1 = cpu1;
    }

    public CPU getCpu2() {
        return cpu2;
    }

    public void setCpu2(CPU cpu2) {
        this.cpu2 = cpu2;
    }

    public RAM getRam() {
        return ram;
    }

    public void setRam(RAM ram) {
        this.ram = ram;
    }

    public Card getEthernet() {
        return ethernet;
    }

    public void setEthernet(Card ethernet) {
        this.ethernet = ethernet;
    }

    public Card getTokenRing() {
        return tokenRing;
    }

    public void setTokenRing(Card tokenRing) {
        this.tokenRing = tokenRing;
    }

    // There are formally 4 task commands but this is a simplification for the user.
    enum TaskType {
        READ_RAM, WRITE_RAM, READ_ETHERNET, WRITE_ETHERNET, READ_TOKEN_RING, WRITE_TOKEN_RING
    }
}

