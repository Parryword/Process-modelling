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

// COMPONENT PATTER
// Command - component
// Process - composite
// Task(ReadCard/ReadMemory/WriteCard/WriteMemory) - leaf

// TO DO
// Factory - We can create a factory class to create CPU, RAM etc.
// Singleton - We can make the factory class singleton.

public class Main {
    public static void main(String[] args) {
        CPU cpu = new CPU();
        RAM ram = new RAM();
        Ethernet et = new Ethernet(Utility.generateData(100));
        TokenRing tr = new TokenRing();
        Command c1 = new ReadCard(et, 10);
        Command c2 = new WriteMemory(ram,0);
        Command c3 = new WriteCard(new EthernetToTokenRingAdapter(tr));
        Process p1 = new Process(c1, c2, c3);
        cpu.addTask(p1);
        cpu.start();

    }
}
