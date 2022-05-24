package file;

import parser.TimestampParser;
import provider.FileSaver;
import provider.TimeProvider;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Paths;

public abstract class AbstractFileSaver implements FileSaver, Serializable {


    @Override
    public void saveFile() {


        FileOutputStream fos = null;
        File file;

        try {
            TimeProvider parser = new TimestampParser();
            file = new File(Paths.get("").toAbsolutePath() + "\\report_spread_" + parser.parseTimestamp() + ".txt");
            fos = new FileOutputStream(file);

            if (!file.exists()) {
                file.createNewFile();
            }

            byte[] bytesArray = getInputData().getBytes();

            fos.write(bytesArray);
            fos.flush();
            System.out.println(getMessage());
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }
        finally {
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException ioe) {
                System.out.println("Error in closing the Stream");
            }
        }
    }

    protected abstract String getMessage();
    protected abstract String getInputData();
}
