/**This program and its documentation are created by Yiğit Efe İren.
 * Team members are Yiğit Efe İren, Can Selçuk, Halil Can Parlayan, and Bersay Yakıcı.
 */
public class Main {
    public static void main(String[] args) {
        // Create random data
        Byte[] preGeneratedData = Utility.generateByteData(1000);

        // Create hardware
        CPU cpu1 = new AMD();
        CPU cpu2 = new Intel();
        RAM ram = new RAM();
        Ethernet et = new Ethernet(preGeneratedData);
        TokenRing tr = new TokenRing();
        Computer cmp = new Computer(et, tr, ram, cpu1, cpu2);

        // Create tasks
        Command c1 = cmp.readEthernet(8);
        Command c2 = cmp.writeMemory(0);
        Command c3 = cmp.writeTokenRing();
        Command c4 = cmp.readEthernet(4);
        Command c5 = cmp.writeMemory(8);
        Command c6 = cmp.writeTokenRing();
        Process p1 = new Process(c1, c2, c3);
        Process p2 = new Process(c4, c5, c6);

        // Add tasks
        cmp.configureTasks(p1, p2);

        // Run the computer
        cmp.runComputer();
    }
}
