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
        boolean cp1Stop, cp2Stop;
        while (true) {
            cp1Stop = cpu1.run();
            cp2Stop = cpu2.run();
            if (cp1Stop == false && cp2Stop == false) {
                break;
            }
        }
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
}
