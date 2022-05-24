import file.AbstractFileSaver;
import handler.DataHandler;
import handler.FileHandler;
import handler.FileSaverImpl;
import model.SpreadItem;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {
        DataHandler dataHandler = new DataHandler();

        AbstractFileSaver fileSaver = new FileSaverImpl();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {

                fileSaver.saveFile();
          //      List<SpreadItem> spreadList = dataHandler.getSpreadData();
          //      FileHandler fileHandler = new FileHandler();
          //      try {
          //          fileHandler.getSpreadFile(spreadList);
          //      } catch (IOException e) {
          //          e.printStackTrace();
          //      }
            }
        };
        ScheduledExecutorService service = Executors
                .newSingleThreadScheduledExecutor();
        service.scheduleAtFixedRate(runnable, 0, 60, TimeUnit.SECONDS);


    }

}
