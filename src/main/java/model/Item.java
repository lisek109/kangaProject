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

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public List<List<String>> getBids() {
        return bids;
    }

    public void setBids(List<List<String>> bids) {
        this.bids = bids;
    }

    public List<List<String>> getAsks() {
        return asks;
    }

    public void setAsks(List<List<String>> asks) {
        this.asks = asks;
    }

    public String getTicker_id() {
        return ticker_id;
    }

    public void setTicker_id(String ticker_id) {
        this.ticker_id = ticker_id;
    }
}
