package com.example.dataloadservice.model;

import com.example.dataloadservice.model.id.StockCategoryId;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
@IdClass(StockCategoryId.class)
public class StockCategory {

    @Id
    private String symbol;
    @Id
    private String category;

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
