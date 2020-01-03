package com.example.dataloadservice.model.id;

import java.io.Serializable;

public class StockCategoryId implements Serializable {
    private String symbol;
    private String category;

    public StockCategoryId(){

    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
