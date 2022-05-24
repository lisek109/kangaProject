package handler;

import file.AbstractFileSaver;
import model.SpreadItem;
import parser.TimestampParser;
import provider.TimeProvider;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class FileHandler  {



    public String  getSpreadFile(List<SpreadItem> spreadList) {
        String header1 = "Above 2% market spread\n";
        String header2 = "Below 2% market spread\n";
        String header3 = "Null market spread\n";

        List<String> listOfEmptyPairs = spreadList.stream()
                .filter(a -> a.getSpread() == -1.0)
                .map(SpreadItem::toString)
                .collect(Collectors.toList());

        List<String> listOfPairsBelow2Percent = spreadList.stream()
                .filter(a -> a.getSpread() >= 0 && a.getSpread() <= 2)
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

        //  FileOutputStream fos = null;
        //  File file;
        String mycontent = header3 + "\n\n" + header2 + "\n\n" + header1;
        return mycontent;

    }

  //  @Override
  //  public void saveFile(String content, String message) {
  //      super.saveFile(content, message);
  //  }


     //   try {
     //       TimeProvider parser = new TimestampParser();
     //       file = new File(Paths.get("").toAbsolutePath() + "\\report_spread_" + parser.parseTimestamp() + ".txt");
     //       fos = new FileOutputStream(file);
//
     //       if (!file.exists()) {
     //           file.createNewFile();
     //       }
//
     //       byte[] bytesArray = mycontent.getBytes();
//
     //       fos.write(bytesArray);
     //       fos.flush();
     //       System.out.println("File Written Successfully");
     //   }
     //   catch (IOException ioe) {
     //       ioe.printStackTrace();
     //   }
     //   finally {
     //       try {
     //           if (fos != null) {
     //               fos.close();
     //           }
     //       } catch (IOException ioe) {
     //           System.out.println("Error in closing the Stream");
     //       }
     //   }

}
