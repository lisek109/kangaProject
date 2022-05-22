package model;

public class PairItem {

    public PairItem(String ticker_id, String base, String target) {
        this.ticker_id = ticker_id;
        this.base = base;
        this.target = target;
    }

    private String ticker_id;
    private String base;
    private String target;

    public String getTicker_id() {
        return ticker_id;
    }
}
