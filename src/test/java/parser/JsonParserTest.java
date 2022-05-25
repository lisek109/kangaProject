package parser;

import model.SpreadItem;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JsonParserTest {

    @ParameterizedTest
    @MethodSource("providerBidsAsksData")
    void shouldReturnSpreadItems(String bidsAsksObject, SpreadItem expectedResult) {
        //when
        SpreadItem result = JsonParser.spreadParser(bidsAsksObject);
        //then
        assertEquals(expectedResult, result);
    }


    static Stream<Arguments> providerBidsAsksData() {
        return Stream.of(
                Arguments.of("{\"timestamp\":1653427976454,\"bids\":[[\"16.65\",\"5.26981424\"],[\"16.64\",\"13\"]]" +
                        ",\"asks\":[[\"35\",\"102.91157790\"],[\"34\",\"99.4517555\"]],\"ticker_id\":\"KNG_PLN\"}"
                        , new SpreadItem("KNG_PLN", 68.51)),
                Arguments.of("{\"timestamp\":1653430135022,\"bids\":[[\"29393.09\",\"0.18575\"],[\"29391.5\",\"0.017\"]]" +
                        ",\"asks\":[[\"29698.86\",\"0.00075\"],[\"29697.75\",\"0.67345169\"]],\"ticker_id\":\"BTC_USD\"}"
                ,new SpreadItem("BTC_USD", 1.03))
        );
    }

    @ParameterizedTest
    @MethodSource("providerBidsAsksEmptyData")
    void shouldReturnSpreadItemWithSpreadMinusOneWhenBidsOrAsksObjetIsEmpty(String bidsAsksObject) {
        //given
        SpreadItem expectedResult = new SpreadItem("CEBULA_ETH", -1.0);
        //when
        SpreadItem result = JsonParser.spreadParser(bidsAsksObject);
        //then
        assertEquals(expectedResult, result);
    }

    static Stream<Arguments> providerBidsAsksEmptyData() {
        return Stream.of(
                Arguments.of("{\"timestamp\":1653434521172,\"bids\":[]" +
                        ",\"asks\":[[\"0.00045\",\"26980.5714\"],[\"0.00000999\",\"10000\"]],\"ticker_id\":\"CEBULA_ETH\"}"),
                Arguments.of("{\"timestamp\":1653434521172,\"bids\":[[\"0.00045\",\"26980.5714\"],[\"0.00000999\",\"10000\"]]" +
                        ",\"asks\":[],\"ticker_id\":\"CEBULA_ETH\"}"),
                Arguments.of("{\"timestamp\":1653434521172,\"bids\":[]" +
                        ",\"asks\":[],\"ticker_id\":\"CEBULA_ETH\"}")
        );
    }



   @Test
    void shouldReturnPAirItems() throws URISyntaxException {
        //given
        List<URI> expectedResult = List.of(new URI("https://public.kanga.exchange/api/market/orderbook/MORTAL_ETH")
                , new URI("https://public.kanga.exchange/api/market/orderbook/APIRWT_ETH"));
        //when
        List<URI> result = JsonParser.marketPairParser("[{\"ticker_id\":\"MORTAL_ETH\",\"base\":\"MORTAL\",\"target\":\"ETH\"}" +
                ", {\"ticker_id\":\"APIRWT_ETH\",\"base\":\"APIRWT\",\"target\":\"ETH\"}]");
        //then
        assertEquals(expectedResult, result);
    }

    @Test
    void shouldReturnEmptyListIfNullAsArgument() {
        //given
        List<URI> expectedResult = new ArrayList<>();
        //when
        List<URI> result = JsonParser.marketPairParser(null);
        //then
        assertEquals(expectedResult, result);
    }

}