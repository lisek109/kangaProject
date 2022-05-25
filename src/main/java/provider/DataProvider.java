package provider;

import model.SpreadItem;

import java.util.List;

public interface DataProvider {
    List<SpreadItem> getSpreadData();
}
