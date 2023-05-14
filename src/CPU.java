import java.io.*;
import java.util.Collections;
import java.util.LinkedList;

public abstract class CPU {
    /**Task list contains the list of commands the CPUs will execute. It is declared static so that all CPUs can access the same data. It is assumed there is only one computer created in this scenario. If there are multiple computers, this will cause bugs as CPUs belonging to different computer will have access to the same task list. To circumnavigate this problem, the task list may be replaced inside Computer class instead.*/
    private static LinkedList<Command> taskList;
    private Command currentTask;
    /**CPU ID used to distinguish which process was executed by which CPU in the log data.*/
    protected String cpuID;
    private Logger logger;

    public CPU() {
        this.cpuID = this.getClass().getName();
        this.taskList = new LinkedList<>();
        this.logger = Logger.getInstance();
    }

    public static void addTask(Command... task) {
        Collections.addAll(taskList, task);
    }

    /**Handles the main responsibility of the CPU. This is the place where tasks are executed. If there are no tasks left, it will return false, which will prompt computer to shut down.*/
    public boolean operation() {
        hook();
        fetchTask();
        if (currentTask == null) {
            return false;
        }
        else {
            executeTask();
            recordInLog();
            discardTheTask();
            return true;
        }
    }

    private void fetchTask() {
        currentTask = taskList.poll();
        if (currentTask == null) {
            System.out.println("Fetch task failed.");
            return;
        }
        System.out.println("Task fetched.");
    }

    private void executeTask() {
        System.out.println("Task execute started.");
        currentTask.execute();
        System.out.println("Task execute completed.");
    }

    private void recordInLog() {
        String text = currentTask.toString();
        try {
            logger.log(cpuID, text);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Task recorded.");
    }

    private void discardTheTask() {
        currentTask = null;
        System.out.println("Task discarded.\n");
    }

    protected abstract void hook();
}

class AMD extends CPU {


    @Override
    protected void hook() {
        System.out.println("AMD CPU operates.");
    }
}

class Intel extends CPU {

    @Override
    protected void hook() {
        System.out.println("Intel CPU operates.");
    }
}
