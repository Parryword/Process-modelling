import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**Command class is used to reference a process or task.*/
public interface Command {
    void execute();
}

/**Process class stores a group of tasks, which it runs in order.*/
class Process implements Command {
    private ArrayList<Command> commands;

    public Process(Command... command) {
        commands = new ArrayList<>();
        Collections.addAll(commands, command);
    }

    @Override
    public void execute() {
        for (Command c:commands
             ) {
            c.execute();
            System.out.println("Sub-command executed.");
        }
    }

    @Override
    public String toString() {
        return "Process{" +
                "commands=" + commands +
                '}';
    }
}

/**Task class is a general abstraction of all tasks that is implemented. Its main function is to store last read data in a static format so that every task can access it.*/
abstract class Task implements Command {
    /**Contains the last data transferred. Only read commands update it.*/
    static protected byte[] data;
    /**Cache is not strictly necessary; it is only used to log what data has been transferred by a task*/
    protected byte[] cache;

    /**Updates the common data of all tasks.*/
    protected void setData(byte[] data) {

        this.data = data;
    }

    /**Returns the common data of all tasks.*/
    protected byte[] getData() {

        return data;
    }

    /**Sets data of the tasks to null. It is not used in the project.*/
    @Deprecated
    protected void flushData() {
        data = null;
    }
}

class ReadMemory extends Task {
    private RAM ram;
    private int address;
    private int size;


    public ReadMemory(RAM ram, int address, int size) {
        this.ram = ram;
        this.address = address;
        this.size = size;
    }

    @Override
    public void execute() {
        cache = ram.get(address, size);
        setData(cache);
    }

    @Override
    public String toString() {
        return "ReadMemory{" +
                "cache=" + Arrays.toString(cache) +
                ", ram=" + ram +
                ", address=" + address +
                ", size=" + size +
                '}';
    }
}

class WriteMemory extends Task {
    private RAM ram;
    private int address;
    private int errCode;

    public WriteMemory(RAM ram, int address) {
        this.ram = ram;
        this.address = address;
    }

    @Override
    public void execute() {
        cache = getData();
        errCode = ram.set(cache, address);
    }

    @Override
    public String toString() {
        return "WriteMemory{" +
                "cache=" + Arrays.toString(cache) +
                ", ram=" + ram +
                ", address=" + address +
                ", errCode=" + errCode +
                '}';
    }
}

class ReadCard extends Task {
    private Card card;
    private int size;

    public ReadCard (Card card, int size) {
        this.card = card;
        this.size = size;
    }

    @Override
    public void execute() {
        cache = card.getCom(size);
        setData(cache);
    }

    @Override
    public String toString() {
        return "ReadCard{" +
                "cache=" + Arrays.toString(cache) +
                ", card=" + card +
                ", size=" + size +
                '}';
    }
}

class WriteCard extends Task {
    private Card card;
    private int errCode;

    public WriteCard(Card card) {
        this.card = card;
    }

    @Override
    public void execute() {
        cache = getData();
        errCode = card.setCom(cache);
    }

    @Override
    public String toString() {
        return "WriteCard{" +
                "cache=" + Arrays.toString(cache) +
                ", card=" + card +
                ", errCode=" + errCode +
                '}';
    }
}
