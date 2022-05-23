package model;

import java.util.Objects;

public class SpreadItem {

    public SpreadItem(String ticker_id, Double spread) {
        this.ticker_id = ticker_id;
        this.spread = spread;
    }

    private String ticker_id;
    private Double spread;

    public String getTicker_id() {
        return ticker_id;
    }

    public double getSpread() {
        return this.spread;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SpreadItem item = (SpreadItem) o;
        return Objects.equals(ticker_id, item.ticker_id) && Objects.equals(spread, item.spread);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ticker_id, spread);
    }

    @Override
    public String toString() {
        return this.ticker_id + "   " + this.spread + "\n";
    }
}
