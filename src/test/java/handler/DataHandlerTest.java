package handler;

import model.SpreadItem;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import parser.JsonParser;

import java.lang.annotation.Target;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class DataHandlerTest {

    private static final SpreadItem SPREAD_ITEM1 = new SpreadItem("Ticker1", 1.0);
    private static final SpreadItem SPREAD_ITEM2 = new SpreadItem("Ticker2", 3.0);
    private static final SpreadItem SPREAD_ITEM3 = new SpreadItem("Ticker3", -1.0);

    @Mock
    private JsonParser jsonParser;

    @Mock
    private MarketPairHandler marketPairHandler;


   // @InjectMocks()

}