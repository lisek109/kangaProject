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
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.stream.Collectors;

public class DataHandler implements DataProvider {

    public ArrayList<SpreadItem> getSpreadData() {
        ArrayList<SpreadItem> spreadItems = new ArrayList<>();
        MarketPairsProvider marketPairsProvider = new MarketPairHandler(); // here can I handle exception
        List<URI> marketUrls = marketPairsProvider.getmarketPairsData();
        // for (String marketPair : marketPairsProvider.getmarketPairsData()) {
        try {
            // System.out.println("Values for pair: " + marketPair);
            // HttpClient client = HttpClient.newHttpClient();
            // HttpRequest request = HttpRequest.newBuilder()
            //         .uri(URI.create("https://public.kanga.exchange/api/market/orderbook/" + marketPair))
            //         .timeout(Duration.ofSeconds(20L))
            //         .build();
            // SpreadItem item = client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
            //                 .thenApply(stringHttpResponse -> stringHttpResponse.body())
            //                 .thenApply(JsonParser::spreadParser)
            //                 .join();
            // spreadItems.add(item);

            HttpClient client = HttpClient.newHttpClient();
            List<SpreadItem> items = marketUrls.stream()
                    .map(marketUrl -> client
                            .sendAsync(
                                    HttpRequest.newBuilder(marketUrl).GET().build(),
                                    HttpResponse.BodyHandlers.ofString())
                            .thenApply(stringHttpResponse -> stringHttpResponse.body())
                            .thenApply(JsonParser::spreadParser).join())
                    .collect(Collectors.toList());
                    //spreadItems.add(item);

        } catch (CompletionException c) {
            System.out.println("Can not load page " + c.getMessage());
            return new ArrayList<>();
        }
        return spreadItems;
    }



    public void getSpreadFile(ArrayList<SpreadItem> spreadList) {
        // Split list into 3 lists - =< 2%, > 2%, null
        // List<String> stringValues = spreadList.stream()
        //        .map(a -> a.ticker_id+"   "+a.spread.toString()+"%")
        //        .collect(Collectors.toList());
        // Create file stream writer
        // add 2 first headers

    }

    public static void main(String[] args) {
        DataHandler dataHandler = new DataHandler();

        ArrayList<SpreadItem> spreadList = dataHandler.getSpreadData();


    }
}
