package handler;

import model.SpreadItem;

import java.util.List;
import java.util.stream.Collectors;

public class FileHandler {

    public  String getSpreadFile(List<SpreadItem> spreadList) {

        if (spreadList.isEmpty()) {
            return "";
        }

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

        String mycontent = header3 + "\n\n" + header2 + "\n\n" + header1;
        return mycontent;

    }
}
