import file.AbstractFileSaver;
import handler.FileSaverImpl;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {

        AbstractFileSaver fileSaver = new FileSaverImpl();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                fileSaver.saveFile();
            }
        };
        ScheduledExecutorService service = Executors
                .newSingleThreadScheduledExecutor();
        service.scheduleAtFixedRate(runnable, 0, 60, TimeUnit.SECONDS);

    }

}
