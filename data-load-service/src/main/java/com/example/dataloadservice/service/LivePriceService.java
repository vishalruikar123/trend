package com.example.dataloadservice.service;

import com.example.dataloadservice.model.LivePrice;
import com.example.dataloadservice.repository.LivePriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Scope("prototype")
public class LivePriceService {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    LivePriceRepository livePriceRepository;

    public LivePrice getPrice(String symbol){
        LivePrice livePrice = new LivePrice();
        try {
            String prefixUrl = "https://www.nseindia.com/marketinfo/companyTracker/ajaxquote.jsp?";
            String symbolParam = "symbol=" + symbol;
            String seriesParam = "&series=EQ";
            String url = prefixUrl + symbolParam + seriesParam;
            String result = restTemplate.getForObject(url, String.class);
            System.out.println(result);

            String[] tokens = result.split(":");
            Double currentPrice = Double.valueOf(tokens[13]);
            Double prevClosePrice = Double.valueOf(tokens[8]);
            Double openPrice = Double.valueOf(tokens[9]);
            Double highPrice = Double.valueOf(tokens[10]);
            Double lowPrice = Double.valueOf(tokens[11]);

            livePrice = new LivePrice();
            livePrice.setSymbol(symbol);
            livePrice.setCurrentPrice(currentPrice);
            livePrice.setPrevClosePrice(prevClosePrice);
            livePrice.setOpenPrice(openPrice);
            livePrice.setHighPrice(highPrice);
            livePrice.setLowPrice(lowPrice);

            return livePrice;

        }catch (Exception ex){
            System.out.println("Error while getting live price"  + ex);
        }

        return livePrice;
    }

    public void savePrice(LivePrice livePrice){
        livePriceRepository.save(livePrice);
    }

}
