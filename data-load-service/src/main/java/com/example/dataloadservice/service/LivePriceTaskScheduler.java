package com.example.dataloadservice.service;

import com.example.dataloadservice.model.LivePrice;
import com.example.dataloadservice.model.Stock;
import com.example.dataloadservice.repository.LivePriceRepository;
import com.example.dataloadservice.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Component
public class LivePriceTaskScheduler  {

    @Autowired
    private ApplicationContext applicationContext;

    final ExecutorService downloadExecutorService = Executors.newFixedThreadPool(5);

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private LivePriceRepository livePriceRepository;

    @Scheduled(fixedRate = 60000)
    //@Scheduled(cron = "0 0/1 * * * ?")
    public void downloadLivePrices(){
        Date date = new Date();
        int serialNo = getSerialNumber(date);
        List<Stock> stocks = (List) stockRepository.findAll();
        CountDownLatch cdl = new CountDownLatch(stocks.size());
        for(Stock stock : stocks) {
            DownloadAndPublishTask task = new DownloadAndPublishTask(stock.getSymbol(), date, serialNo, cdl);
            downloadExecutorService.submit(task);
        }
        try {
            cdl.await(1, TimeUnit.MINUTES);
            // Send message
            //priceEventPublisher.publish(date,serialNo);
        }catch (Exception ex){

        }

        System.out.println("Completed At - " + date);
    }

    private int getSerialNumber(Date date){
        int number = 0;
        int hours = date.getHours();
        int minutes = date.getMinutes();
        String value = "" + ( ( hours * 100 ) + minutes );
        return Integer.valueOf(value);
    }

    class DownloadAndPublishTask implements Runnable{
        String symbol;
        Date date;
        int serialNo;
        CountDownLatch cdl;
        DownloadAndPublishTask(String symbol, Date date, int serialNo, CountDownLatch cdl){
            this.symbol = symbol;
            this.date = date;
            this.serialNo = serialNo;
            this.cdl = cdl;
        }
        public void run(){
            LivePriceService livePriceService = applicationContext.getBean(LivePriceService.class);
            LivePrice price = livePriceService.getPrice(this.symbol);
            price.setDate(this.date);
            price.setSerialNo(this.serialNo);
            livePriceService.savePrice(price);

            cdl.countDown();
        }
    }

}
