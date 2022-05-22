package handler;

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

    public List<URI> getmarketPairsData() {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://public.kanga.exchange/api/market/pairs")).timeout(Duration.ofSeconds(20)).build();
        // HttpClient handle timeout error
        try {
            List<URI> list = client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                    .thenApply(stringHttpResponse -> stringHttpResponse.body())
                    .thenApply(JsonParser::marketPairParser)
                    .join();
            return list;
        } catch (CompletionException c) {
            System.out.println("Can not load page " + c.getMessage());
            return new ArrayList<>();
        }
    }
}
