package handler;

import model.SpreadItem;
import parser.TimestampParser;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class FileHandler implements Serializable {

    public void getSpreadFile(List<SpreadItem> spreadList) throws IOException {
        String header1 = "Powyzej 2% \nMarket spread\n";
        String header2 = "Ponizej 2% \nMarket spread\n";
        String header3 = "Null \nMarket spread\n";

        List<String> listOfEmptyPairs = spreadList.stream()
                .filter(a -> a.getSpread() == -1.0)
                .map(SpreadItem::toString)
                .collect(Collectors.toList());

        List<String> listOfPairsBelow2Percent = spreadList.stream()
                .filter(a -> a.getSpread() >=0 && a.getSpread() <= 2)
                .map(SpreadItem::toString)
                .collect(Collectors.toList());

        List<String> listOfPairsAbove2Percent = spreadList.stream()
                .filter(a -> a.getSpread() > 2)
                .map(SpreadItem::toString)
                .collect(Collectors.toList());

        for (String item : listOfEmptyPairs) {
            header3 = header3 + item;
        }

        for (String item : listOfPairsBelow2Percent) {
            header2 = header2 + item;
        }

        for (String item : listOfPairsAbove2Percent) {
            header1 = header1 + item;
        }

        FileOutputStream fos = null;
        File file;
        String mycontent = header1 + "\n\n" +header2 + "\n\n" +header3;

        try {
            TimestampParser parser = new TimestampParser();
            file = new File(Paths.get("").toAbsolutePath() + "\\report_spread_"+parser.parseTimestamp()+".txt");
            fos = new FileOutputStream(file);

            if (!file.exists()) {
                file.createNewFile();
            }

            byte[] bytesArray = mycontent.getBytes();

            fos.write(bytesArray);
            fos.flush();
            System.out.println("File Written Successfully");
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


  //  public void getSpreadFile(ArrayList<SpreadItem> spreadList) {
  //      // Split list into 3 lists - =< 2%, > 2%, null
  //      // List<String> stringValues = spreadList.stream()
  //      //        .map(a -> a.ticker_id+"   "+a.spread.toString()+"%")
  //      //        .collect(Collectors.toList());
  //      // Create file stream writer
  //      // add 2 first headers
//
  //  }

    //fix file error - file name?
    //fix spread -1 in parser and stream method
    //add sort()
}
