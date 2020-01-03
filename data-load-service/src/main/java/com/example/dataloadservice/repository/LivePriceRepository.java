package com.example.dataloadservice.repository;

import com.example.dataloadservice.model.LivePrice;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;

public interface LivePriceRepository extends CrudRepository<LivePrice,String> {
    @Query("select p.currentPrice from LivePrice p where p.symbol=?1 and p.date=?2 and p.serialNo=?3")
    public Double getPrice(String symbol, Date date, int serialNo);

    @Query("select p.openPrice from LivePrice p where p.symbol=?1 and p.date=?2 and p.serialNo=?3")
    public Double getOpenPrice(String symbol, Date date, int serialNo);

    @Query("select p.prevClosePrice from LivePrice p where p.symbol=?1 and p.date=?2 and p.serialNo=?3")
    public Double getPrevClosePrice(String symbol, Date date, int serialNo);
}
