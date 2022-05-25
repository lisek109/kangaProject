package handler;

import model.SpreadItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import parser.JsonParser;
import provider.DataProvider;
import provider.MarketPairsProvider;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletionException;
import java.util.stream.Collectors;

public class DataHandler implements DataProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataHandler.class);

    public List<SpreadItem> getSpreadData() {
        MarketPairsProvider marketPairsProvider = new MarketPairHandler();
        List<URI> marketUrls = marketPairsProvider.getmarketPairsData();
        try {
            LOGGER.info("*********STARTING NEW REPORT**********"); // just to know when next report starts to generate
            HttpClient client = HttpClient.newHttpClient();
            List<SpreadItem> items = marketUrls.stream()
                    .map(marketUrl -> client
                            .sendAsync(
                                    HttpRequest.newBuilder(marketUrl).GET().timeout(Duration.ofSeconds(20L)).build(),
                                    HttpResponse.BodyHandlers.ofString())
                            .thenApply(stringHttpResponse -> stringHttpResponse.body())
                            .thenApply(JsonParser::spreadParser).join())
                    .collect(Collectors.toList());
            LOGGER.info("--------LIST SIZE-------" + items.size());
            return items;

        } catch (CompletionException c) {
            LOGGER.error("Can not load page " + c.getMessage());
            return new ArrayList<>();
        }
    }
}

