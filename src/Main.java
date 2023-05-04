// COMPLETED
// COMMAND PATTERN
// Program - client
// CPU - invoker
// Task/Process - command
// Ram - receiver

// ADAPTER PATTERN
// TokenRing - adaptee
// EthernetToTokenRingAdapter - adapter
// Ethernet - target

// COMPOSITE PATTERN
// Command - component
// Process - composite
// Task(ReadCard/ReadMemory/WriteCard/WriteMemory) - leaf

// FACADE PATTERN
// Computer

// SINGLETON PATTERN
// Logger
public class Main {
    public static void main(String[] args) {
        // Create random data
        Byte[] preGeneratedData = Utility.generateByteData(1000);

        // Create hardware
        CPU cpu1 = new CPU("1");
        CPU cpu2 = new CPU("2");
        RAM ram = new RAM();
        Ethernet et = new Ethernet(preGeneratedData);
        TokenRing tr = new TokenRing();
        Computer cmp = new Computer(et, tr, ram, cpu1, cpu2);

        // Create tasks
        Command c1 = new ReadCard(cmp.getEthernet(), 4);
        Command c2 = new WriteMemory(cmp.getRam(),0);
        Command c3 = new WriteCard(cmp.getEthernet());
        Command c4 = new ReadCard(cmp.getEthernet(), 4);
        Command c5 = new WriteMemory(cmp.getRam(),0);
        Command c6 = new WriteCard(cmp.getTokenRing());
        Process p1 = new Process(c1, c2, c3);
        Process p2 = new Process(c4, c5, c6);

        // Add tasks
        cmp.configureTasks(p1);
        cmp.configureTasks(p2);

        // Run the computer
        cmp.runComputer();
    }
}
