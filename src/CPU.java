import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.LinkedList;

public class CPU implements Runnable {
    private Thread thread;
    private LinkedList<Command> taskList;
    private Command currentTask;
    private static File logFile;
    private boolean isRunning;

    public CPU() {
        this.logFile = new File("src/log.txt");
        this.thread = new Thread(this);
        this.taskList = new LinkedList<>();
    }

    public void start() {
        thread.start();
        isRunning = true;
    }

    public void addTask(Command... task) {
        Collections.addAll(taskList, task);
        isRunning = true;
    }

    @Override
    public void run() {
        while (isRunning) {
            fetchTask();
            if (currentTask != null) {
                executeTask();
                recordInLog();
                discardTheTask();
            } else {
                isRunning = false;
                break;
            }
            try {
                thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void fetchTask() {
        currentTask = taskList.peek();
        if (currentTask == null) {
            return;
        }
        System.out.println("Task fetched.");
    }

    private void executeTask() {
        currentTask.execute();
        System.out.println("Task executed");
    }

    private void recordInLog() {
        String text = currentTask.toString();
        synchronized (logFile) {
            try {
                FileWriter writer = new FileWriter(logFile, true);
                LocalDateTime ldt = LocalDateTime.now();
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                String date = ldt.format(dtf);
                writer.append(date + " " + text+"\n");
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Task recorded.");
    }

    private void discardTheTask() {
        taskList.poll();
        currentTask = null;
        System.out.println("Task discarded.");
    }
}
