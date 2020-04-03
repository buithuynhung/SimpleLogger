import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class LoggerTest {

    @Test
    public void logTest() throws IOException {

        Logger logger = new Logger();
        logger.log("message");
        assertTrue(getLastLine().startsWith("INFO"));

        logger.log("ERROR", "error message");
        assertTrue(getLastLine().startsWith("ERROR"));
        assertFalse(getLastLine().startsWith("TRACE"));

    }

    public String getLastLine() throws IOException {
        String logfileName = "logger.log";
        String sCurrentLine;
        String lastLine = "";
        BufferedReader br = new BufferedReader(new FileReader(logfileName));

        while ((sCurrentLine = br.readLine()) != null)
        {
            System.out.println(sCurrentLine);
            lastLine = sCurrentLine;
        }
        return lastLine;
    }

}