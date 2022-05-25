package handler;

import model.SpreadItem;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FileHandlerTest {

    private static final FileHandler FILE_HANDLER = new FileHandler();

    private static final SpreadItem SPREAD_ITEM1 = new SpreadItem("Ticker1", 1.0);
    private static final SpreadItem SPREAD_ITEM2 = new SpreadItem("Ticker2", 3.0);
    private static final SpreadItem SPREAD_ITEM3 = new SpreadItem("Ticker3", -1.0);
    private static final SpreadItem SPREAD_ITEM4 = null;


    List<SpreadItem> spreadItems = List.of(new SpreadItem("Ticker1", 1.0),
            new SpreadItem("Ticker2", 3.0), new SpreadItem("Ticker3", -1.0));

    @Test
    void shouldReturnStringDataReportFromList() {
        //given
        String expectedResult = "Null market spread\n" +
                "Ticker3   -1.0\n" + "\n" + "\n" +
                "Below 2% market spread\n" +
                "Ticker1   1.0\n" + "\n" + "\n" +
                "Above 2% market spread\n" +
                "Ticker2   3.0" + "\n";
        //when
        String result = FILE_HANDLER.getSpreadFile(spreadItems);
        //then
        assertEquals(expectedResult, result);

    }

    @Test
    void shouldReturnEmptyStringIfListIsEmpty() {
        //given
        String excpectedResult = "";
        //when
        String result = FILE_HANDLER.getSpreadFile(new ArrayList<>());
        //then
        assertEquals(excpectedResult, result);
    }

    @Test
    void shouldThrowNullPointerExceptionIfListIsNull() {
        //when & then
        assertThrows(NullPointerException.class,
                () -> FILE_HANDLER.getSpreadFile(null));
    }

}