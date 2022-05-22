package model;

import java.util.List;

public class Item {

    private long timestamp;
    private List<List<String>> bids;
    private List<List<String>> asks;
    private String ticker_id;

    public long getTimestamp() {
        return timestamp;
    }

    public List<List<String>> getBids() {
        return bids;
    }

    public List<List<String>> getAsks() {
        return asks;
    }

    public String getTicker_id() {
        return ticker_id;
    }
}
