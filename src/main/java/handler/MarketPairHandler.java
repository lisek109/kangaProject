package handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import parser.JsonParser;
import provider.MarketPairsProvider;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletionException;

public class MarketPairHandler implements MarketPairsProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(MarketPairHandler.class);

    public List<URI> getmarketPairsData() {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://public.kanga.exchange/api/market/pairs")).timeout(Duration.ofSeconds(20)).build();
        try {
            List<URI> list = client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                    .thenApply(stringHttpResponse -> stringHttpResponse.body())
                    .thenApply(JsonParser::marketPairParser)
                    .join();
            if (list.isEmpty()) {
                LOGGER.error("Can not fetch data from server");
                return new ArrayList<>();
            }
            return list;
        } catch (CompletionException c) {
            LOGGER.error("Can not load page " + c.getMessage());
            return new ArrayList<>();
        }
    }
}
