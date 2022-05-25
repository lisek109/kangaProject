package handler;

import org.junit.jupiter.api.Test;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class MarketPairHandlerTest {

    private static final MarketPairHandler MARKET_PAIR_HANDLER = new MarketPairHandler();

    @Test
    void shouldReturnEmptyArrayIfTimeoutExceptionAreCatched() { //Changing method timeout to mills. line 23 in MarketPairHandler
        //given
        List<URI> excpectedResult = new ArrayList<>();
        //when
        List<URI> result = MARKET_PAIR_HANDLER.getmarketPairsData();
        //then
        assertArrayEquals(excpectedResult.toArray(), result.toArray());
    }

    @Test
    void shouldReturnListOfUriWhenCOnnectedToServer() {
        //when
        List<URI> result = MARKET_PAIR_HANDLER.getmarketPairsData();
        //then
        assert result.size() != 0;
    }
}