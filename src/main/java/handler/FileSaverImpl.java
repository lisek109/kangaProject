package handler;

import file.AbstractFileSaver;
import model.SpreadItem;

import java.util.List;

public class FileSaverImpl extends AbstractFileSaver {

    DataHandler dataHandler = new DataHandler();
    FileHandler fileHandler = new FileHandler();
    List<SpreadItem> spreadItemList = dataHandler.getSpreadData();
    String input = fileHandler.getSpreadFile(spreadItemList);

    @Override
    protected String getInputData() {
        return input;
    }
}





