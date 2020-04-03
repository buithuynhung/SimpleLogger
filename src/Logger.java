import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

enum Level {
    ERROR,
    WARNING,
    INFO,
    DEBUG,
    TRACE
}

public class Logger {

    private final int MAX_LINES = 50;
    Level level;

    public Logger() {
        this.level = Level.INFO;
    }

    public Logger(String level) {
        this.level = Level.valueOf(level);
    }

    public void log(String msg) {
        log("" + level, msg);
    }

    public void log(String level, String msg) {
        String logFileName = "logger.log";

        if (!levelCheck(level)) return;
        OutputStream os = null;
        try {
            if (!new File(logFileName).exists() ||
                    Files.readAllLines(Paths.get(logFileName)).size() >= 50)
                os = new FileOutputStream(new File(logFileName));
            else os = new FileOutputStream(new File(logFileName), true);
            System.setOut(new PrintStream(os));
            System.out.println("" + level + ": <" + getCurrentTime() + "> " + msg);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (os != null) {
                    os.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void writeToLog(String level, String msg) {

    }

    public boolean levelCheck(String level) {
        for (Level l : Level.values()) {
            if (l.name().equals(level)) {
                return true;
            }
        }
        return false;
    }

    private String getCurrentTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        return formatter.format(date);
    }

    public void setLevel(String level) {
        this.level = Level.valueOf(level);
    }

}
