package handler;

import model.SpreadItem;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;


class DataHandlerTest {

    private static final DataHandler DATA_HANDLER = new DataHandler();

    @Test
    void shouldReturnEmptyArrayIfTimeoutExceptionAreCatched() { //Changing method timeout to mills. line 29 in DataHandler
        //given
        ArrayList<SpreadItem> excpectedResult = new ArrayList<>();
        //when
        List<SpreadItem> result = DATA_HANDLER.getSpreadData();
        //then
        assertArrayEquals(excpectedResult.toArray(), result.toArray());
    }

    @Test
    void shouldReturnListOfSpreadItemWhenCOnnectedToServer() {
        //when
        List<SpreadItem> result = DATA_HANDLER.getSpreadData();
        //then
        assert result.size() != 0;
    }

}