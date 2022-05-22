package parser;

import com.google.gson.Gson;
import model.Item;
import model.PairItem;
import model.SpreadItem;
import java.net.URI;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class JsonParser {

    public static List<URI> marketPairParser(String jsonString) {
        String url = "https://public.kanga.exchange/api/market/orderbook/";
        PairItem[] data = new Gson().fromJson(jsonString, PairItem[].class);// Not sure if this solution is the best one since I could also use Jackson here...
        List<URI> marketPairs = Arrays.stream(data)
                .map(PairItem::getTicker_id)
                .map(a -> url + a)
                .map(URI::create)
                .collect(Collectors.toList());
        return marketPairs;
    }


    public static SpreadItem spreadParser(String jsonString) {
        Item data = new Gson().fromJson(jsonString, Item.class);
        System.out.println(data.getTicker_id());

        if (data.getBids().size() == 0 || data.getAsks().size() == 0) {
            return new SpreadItem(data.getTicker_id(), null);
        }

        Float bestBid = data.getBids().stream()
                .map(a -> Float.parseFloat(a.get(0)))
                        .max(Comparator.comparing(x -> x)).get();

        Float bestAsk = data.getAsks().stream()
                .map(a -> Float.parseFloat(a.get(0)))
                .min(Comparator.comparing(x -> x)).get();

        return new SpreadItem(data.getTicker_id(), ((bestAsk - bestBid) / ((0.5 * (bestBid + bestAsk)) * 100 )));
    }
}
