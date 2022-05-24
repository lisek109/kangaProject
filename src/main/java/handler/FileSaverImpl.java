package handler;

import file.AbstractFileSaver;
import model.SpreadItem;

import java.io.Serializable;
import java.util.List;

public class FileSaverImpl extends AbstractFileSaver  {

    FileHandler fileHandler = new FileHandler();
    DataHandler dataHandler = new DataHandler();
    List<SpreadItem> spreadItemList = dataHandler.getSpreadData();
    String input = fileHandler.getSpreadFile(spreadItemList);

    @Override
    protected String getMessage() {
        return "File Written Successfully";
    }

    @Override
    protected String getInputData() {
        return input;
    }
}





