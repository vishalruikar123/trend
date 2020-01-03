package com.example.dataloadservice.model.id;

import java.io.Serializable;
import java.util.Date;

public class LivePriceId implements Serializable {
    private String symbol;
    private Date date;
    private int serialNo;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(int serialNo) {
        this.serialNo = serialNo;
    }
}
