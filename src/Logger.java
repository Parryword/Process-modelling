import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {
    private static Logger instance;
    private File file;
    private Logger() {
        this.file = new File("src/log.txt");
    }
    public static Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    public void log(String cpuID, String text) throws IOException {
        FileWriter writer = new FileWriter(file, true);
        LocalDateTime ldt = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String date = ldt.format(dtf);
        writer.append(date + " CPU: " + cpuID + " " + text+"\n");
        writer.close();
    }
}
