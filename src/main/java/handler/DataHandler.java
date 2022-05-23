package handler;

import model.SpreadItem;
import parser.JsonParser;
import provider.DataProvider;
import provider.MarketPairsProvider;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CompletionException;
import java.util.stream.Collectors;

public class DataHandler implements DataProvider {

    public List<SpreadItem> getSpreadData() {
        MarketPairsProvider marketPairsProvider = new MarketPairHandler();
        List<URI> marketUrls = marketPairsProvider.getmarketPairsData();
        try {
            System.out.println("*********STARTING NEW LOOP**********");
            HttpClient client = HttpClient.newHttpClient();
            List<SpreadItem> items = marketUrls.stream()
                    .map(marketUrl -> client
                            .sendAsync(
                                    HttpRequest.newBuilder(marketUrl).GET().timeout(Duration.ofSeconds(20L)).build(),
                                    HttpResponse.BodyHandlers.ofString())
                            .thenApply(stringHttpResponse -> stringHttpResponse.body())
                            .thenApply(JsonParser::spreadParser).join())
                    .collect(Collectors.toList());
            System.out.println("--------LIST SIZE-------" + items.size());
      //      items.stream()
      //              .filter(a -> a.getSpread() > 2F)
      //              .sorted(Comparator.comparing(SpreadItem::getTicker_id))
      //              .forEach(System.out::println);
            return items;
        } catch (CompletionException c) {
            System.out.println("Can not load page " + c.getMessage());
            return new ArrayList<>();
        }
    }
}

