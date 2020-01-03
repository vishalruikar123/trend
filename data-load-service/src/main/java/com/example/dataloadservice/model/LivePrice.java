package com.example.dataloadservice.model;

import com.example.dataloadservice.model.id.LivePriceId;

import javax.persistence.*;
import java.util.Date;

@Entity
@IdClass(LivePriceId.class)
public class LivePrice {
    @Id
    private String symbol;
    @Id
    @Temporal(TemporalType.DATE)
    private Date date;
    @Id
    private int serialNo;
    private Double currentPrice;
    private Double prevClosePrice;
    private Double openPrice;
    private Double highPrice;
    private Double lowPrice;

    @Override
    public String toString() {
        return date + "|" + serialNo + "|" + symbol + "|" + currentPrice;
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

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(Double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public Double getPrevClosePrice() {
        return prevClosePrice;
    }

    public void setPrevClosePrice(Double prevClosePrice) {
        this.prevClosePrice = prevClosePrice;
    }

    public Double getOpenPrice() {
        return openPrice;
    }

    public void setOpenPrice(Double openPrice) {
        this.openPrice = openPrice;
    }

    public Double getHighPrice() {
        return highPrice;
    }

    public void setHighPrice(Double highPrice) {
        this.highPrice = highPrice;
    }

    public Double getLowPrice() {
        return lowPrice;
    }

    public void setLowPrice(Double lowPrice) {
        this.lowPrice = lowPrice;
    }
}
