package file;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import parser.TimestampParser;
import provider.TimeProvider;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Paths;

public abstract class AbstractFileSaver  {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractFileSaver.class);

    public void saveFile() {
        FileOutputStream fos = null;
        File file;

        if (!getInputData().equals("")) { //If connection with server not succesfull then file will not be created
            try {
                TimeProvider parser = new TimestampParser();
                file = new File(Paths.get("")
                        .toAbsolutePath() + "\\report_spread_" + parser.parseTimestamp() + ".txt");

                fos = new FileOutputStream(file);

                if (!file.exists()) {
                    file.createNewFile();
                }

                byte[] bytesArray = getInputData().getBytes();

                fos.write(bytesArray);
                fos.flush();
                LOGGER.info("File Written Successfully");
            } catch (IOException ioe) {
                ioe.printStackTrace();
            } finally {
                try {
                    if (fos != null) {
                        fos.close();
                    }
                } catch (IOException ioe) {
                    LOGGER.error("Error in closing the Stream");
                }
            }
        }
    }

    protected abstract String getInputData();
}
