package parser;

import com.google.gson.Gson;
import model.Item;
import model.PairItem;
import model.SpreadItem;

import java.net.URI;
import java.text.DecimalFormat;
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
        //System.out.println(data.getTicker_id());

        if (data.getBids().size() == 0 || data.getAsks().size() == 0) {
            return new SpreadItem(data.getTicker_id(), -1.0);
        }

        Double bestBid = data.getBids().stream()
                .map(a -> Double.parseDouble(a.get(0)))
                .max(Comparator.comparing(x -> x)).get();

        Double bestAsk = data.getAsks().stream()
                .map(a -> Double.parseDouble(a.get(0)))
                .min(Comparator.comparing(x -> x)).get();

        Double result = ((bestAsk - bestBid) / ((0.5F * (bestBid + bestAsk))) * 100);
        DecimalFormat df = new DecimalFormat("#.00");
        Double formatedResult = Double.valueOf(df.format(result));

        return new SpreadItem(data.getTicker_id(), formatedResult);
    }
}
