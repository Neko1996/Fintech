package com.example.fintech.stock.service;

import com.example.fintech.stock.model.Stock;
import com.example.fintech.stock.repository.StockRepository;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@RunWith(SpringRunner.class)
class StockServiceTest {

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private StockService stockService;

    Long latest_id= Long.valueOf(1);

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @BeforeEach
    void get_latest_id()throws ParseException{
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = simpleDateFormat.parse("2021-08-25");
        Stock stock=new Stock(date,21.25,23.56,20.31,20.73,20.73,Long.valueOf(1200),"Today");
        stockService.addStock(stock);

        List<Stock> stocks=stockRepository.findAll();
        latest_id=stocks.get(0).getId();
        System.out.println("latest_id_is:"+latest_id);
    }

    @AfterEach
    void delete_last_test_record(){
        stockRepository.deleteById(latest_id);
    }

    @Test
    void Test_getByDate() throws ParseException {
        Date date0 = simpleDateFormat.parse("2020-01-01");
        Stock stock0=new Stock(date0,21.25,23.56,20.31,22.73,22.36,Long.valueOf(1200),"Test");
        stockService.addStock(stock0);

        Date date1 = simpleDateFormat.parse("2020-01-01");
        Stock stock1=new Stock(date1,21.25,23.56,20.31,22.73,22.36,Long.valueOf(1200),"Test");
        stockService.addStock(stock1);

        Date date2 = simpleDateFormat.parse("2021-01-01");
        Stock stock2=new Stock(date2,21.25,23.56,20.31,22.73,22.36,Long.valueOf(1200),"Test");
        stockService.addStock(stock2);

        List<Stock> stocks=stockService.getByDate("2020-01-01");
//        System.out.println("========================"+stocks.size());
        Assert.assertEquals(Long.valueOf(stocks.size()), Long.valueOf(2));
        for(int i=0;i<2;i++){
            Assert.assertEquals(stocks.get(i).getCname(),"Test");
            Assert.assertEquals(Double.valueOf(stocks.get(i).getOpen()),Double.valueOf(21.25));
            Assert.assertEquals(Double.valueOf(stocks.get(i).getHigh()),Double.valueOf(23.56));
            Assert.assertEquals(Double.valueOf(stocks.get(i).getLow()),Double.valueOf(20.31));
            Assert.assertEquals(Double.valueOf(stocks.get(i).getClose()),Double.valueOf(22.73));
            Assert.assertEquals(Double.valueOf(stocks.get(i).getAdjclose()),Double.valueOf(22.36));
            Assert.assertEquals(Long.valueOf(stocks.get(i).getVolume()),Long.valueOf(1200));
        }
        for(int i=1;i<=3;i++){
            stockRepository.deleteById(Long.valueOf(latest_id+i));
        }
    }


    @Test
    void Test_getByDateIntervals() throws ParseException{
        Date date0 = simpleDateFormat.parse("2000-01-01");
        Stock stock0=new Stock(date0,21.25,23.56,20.31,22.73,22.36,Long.valueOf(1200),"Test");
        stockService.addStock(stock0);

        Date date1 = simpleDateFormat.parse("2002-01-01");
        Stock stock1=new Stock(date1,21.25,23.56,20.31,22.73,22.36,Long.valueOf(1200),"Test");
        stockService.addStock(stock1);

        Date date2 = simpleDateFormat.parse("2021-01-01");
        Stock stock2=new Stock(date2,21.25,23.56,20.31,22.73,22.36,Long.valueOf(1200),"Test");
        stockService.addStock(stock2);

        List<Stock> stocks=stockService.getByDateIntervals("2000-01-01","2002-01-01");
        Assert.assertEquals(Long.valueOf(stocks.size()), Long.valueOf(2));
        for(int i=0;i<stocks.size();i++){
            String time=simpleDateFormat.format(stocks.get(i).getDate());
//            String[] times=time.split("-");
//            Assert.assertEquals(times[0],"2020");
//            Assert.assertThat(time,anyOf('2002-01-01','2000-01-01'));
//            Assert.assertTha

            Assert.assertEquals(stocks.get(i).getCname(),"Test");
            Assert.assertEquals(Double.valueOf(stocks.get(i).getOpen()),Double.valueOf(21.25));
            Assert.assertEquals(Double.valueOf(stocks.get(i).getHigh()),Double.valueOf(23.56));
            Assert.assertEquals(Double.valueOf(stocks.get(i).getLow()),Double.valueOf(20.31));
            Assert.assertEquals(Double.valueOf(stocks.get(i).getClose()),Double.valueOf(22.73));
            Assert.assertEquals(Double.valueOf(stocks.get(i).getAdjclose()),Double.valueOf(22.36));
            Assert.assertEquals(Long.valueOf(stocks.get(i).getVolume()),Long.valueOf(1200));
        }
        for(int i=1;i<=3;i++){
            stockRepository.deleteById(Long.valueOf(latest_id+i));
        }
    }

    @Test
    void Test_findByYear() throws ParseException{
        Date date0 = simpleDateFormat.parse("2020-01-01");
        Stock stock0=new Stock(date0,21.25,23.56,21.31,22.73,22.36,Long.valueOf(1200),"Test");
        stockService.addStock(stock0);

        Date date1 = simpleDateFormat.parse("2020-01-01");
        Stock stock1=new Stock(date1,21.25,23.56,21.31,22.73,22.36,Long.valueOf(1200),"Test");
        stockService.addStock(stock1);

        Date date2 = simpleDateFormat.parse("2021-01-01");
        Stock stock2=new Stock(date2,21.25,23.56,23.31,22.73,22.73,Long.valueOf(1200),"Test");
        stockService.addStock(stock2);

        List<Stock> stocks=stockService.findByYear("2020");


        for(int i=0;i<stocks.size();i++){
            String time=simpleDateFormat.format(stocks.get(i).getDate());
            String[] times=time.split("-");
            Assert.assertEquals(times[0],"2020");
            Assert.assertEquals(stocks.get(i).getCname(),"Test");
            Assert.assertEquals(Double.valueOf(stocks.get(i).getOpen()),Double.valueOf(21.25));
            Assert.assertEquals(Double.valueOf(stocks.get(i).getHigh()),Double.valueOf(23.56));
            Assert.assertEquals(Double.valueOf(stocks.get(i).getLow()),Double.valueOf(21.31));
            Assert.assertEquals(Double.valueOf(stocks.get(i).getClose()),Double.valueOf(22.73));
            Assert.assertEquals(Double.valueOf(stocks.get(i).getAdjclose()),Double.valueOf(22.36));
            Assert.assertEquals(Long.valueOf(stocks.get(i).getVolume()),Long.valueOf(1200));
        }
        for(int i=1;i<=3;i++){
            stockRepository.deleteById(Long.valueOf(latest_id+i));
        }
    }

    @Test
    void Test_findByName() throws ParseException{
        Date date0 = simpleDateFormat.parse("2020-01-01");
        Stock stock0=new Stock(date0,21.25,23.56,21.31,22.73,22.36,Long.valueOf(1200),"FN");
        stockService.addStock(stock0);

        Date date1 = simpleDateFormat.parse("2020-01-01");
        Stock stock1=new Stock(date1,21.25,23.56,21.31,22.73,22.36,Long.valueOf(1200),"FN");
        stockService.addStock(stock1);

        Date date2 = simpleDateFormat.parse("2021-01-01");
        Stock stock2=new Stock(date2,21.25,23.56,23.31,22.73,22.73,Long.valueOf(1200),"FNB");
        stockService.addStock(stock2);

        List<Stock> stocks=stockService.findByName("FN");
        Assert.assertEquals(Long.valueOf(stocks.size()), Long.valueOf(2));

        for(int i=0;i<stocks.size();i++){
            String time=simpleDateFormat.format(stocks.get(i).getDate());
            String[] times=time.split("-");
            Assert.assertEquals(times[0],"2020");
            Assert.assertEquals(stocks.get(i).getCname(),"FN");
            Assert.assertEquals(Double.valueOf(stocks.get(i).getOpen()),Double.valueOf(21.25));
            Assert.assertEquals(Double.valueOf(stocks.get(i).getHigh()),Double.valueOf(23.56));
            Assert.assertEquals(Double.valueOf(stocks.get(i).getLow()),Double.valueOf(21.31));
            Assert.assertEquals(Double.valueOf(stocks.get(i).getClose()),Double.valueOf(22.73));
            Assert.assertEquals(Double.valueOf(stocks.get(i).getAdjclose()),Double.valueOf(22.36));
            Assert.assertEquals(Long.valueOf(stocks.get(i).getVolume()),Long.valueOf(1200));
        }
        for(int i=1;i<=3;i++){
            stockRepository.deleteById(Long.valueOf(latest_id+i));
        }

    }

    @Test
    void Test_getCloseUnequllAdjclose() throws ParseException{
         //assertfalse
        Date date0 = simpleDateFormat.parse("2020-01-01");
        Stock stock0=new Stock(date0,21.25,23.56,21.31,22.73,23.36,Long.valueOf(1200),"Adj");
        stockService.addStock(stock0);

        Date date1 = simpleDateFormat.parse("2020-01-01");
        Stock stock1=new Stock(date1,21.25,23.56,21.31,22.73,23.36,Long.valueOf(1200),"Adj");
        stockService.addStock(stock1);

        Date date2 = simpleDateFormat.parse("2020-01-01");
        Stock stock2=new Stock(date2,21.25,23.56,21.31,22.73,22.73,Long.valueOf(1200),"Adj");
        stockService.addStock(stock2);

        List<Stock> stocks=stockService.getCloseUnequllAdjclose();
        Assert.assertEquals(Long.valueOf(stocks.size()), Long.valueOf(2));
        for(int i=0;i<stocks.size();i++){
            String time=simpleDateFormat.format(stocks.get(i).getDate());
            String[] times=time.split("-");
            Assert.assertEquals(times[0],"2020");
            Assert.assertEquals(stocks.get(i).getCname(),"Adj");
            Assert.assertEquals(Double.valueOf(stocks.get(i).getOpen()),Double.valueOf(21.25));
            Assert.assertEquals(Double.valueOf(stocks.get(i).getHigh()),Double.valueOf(23.56));
            Assert.assertEquals(Double.valueOf(stocks.get(i).getLow()),Double.valueOf(21.31));
            Assert.assertEquals(Double.valueOf(stocks.get(i).getClose()),Double.valueOf(22.73));
            Assert.assertEquals(Double.valueOf(stocks.get(i).getAdjclose()),Double.valueOf(23.36));
            Assert.assertEquals(Long.valueOf(stocks.get(i).getVolume()),Long.valueOf(1200));
        }
        for(int i=1;i<=3;i++){
            stockRepository.deleteById(Long.valueOf(latest_id+i));
        }
    }

    @Test
    void Test_addStock() throws ParseException{
        Date date0 = simpleDateFormat.parse("2020-01-01");
        Stock stock0=new Stock(date0,21.25,23.56,20.31,22.73,22.36,Long.valueOf(1200),"Test");
        stockService.addStock(stock0);
        Optional<Stock> stocks=stockRepository.findById(latest_id+1);
        String time=simpleDateFormat.format(stocks.get().getDate());
        String[] times=time.split("-");
        Assert.assertEquals(times[0],"2020");
        Assert.assertEquals(stocks.get().getCname(),"Test");
        Assert.assertEquals(Double.valueOf(stocks.get().getOpen()),Double.valueOf(21.25));
        Assert.assertEquals(Double.valueOf(stocks.get().getHigh()),Double.valueOf(23.56));
        Assert.assertEquals(Double.valueOf(stocks.get().getLow()),Double.valueOf(20.31));
        Assert.assertEquals(Double.valueOf(stocks.get().getClose()),Double.valueOf(22.73));
        Assert.assertEquals(Double.valueOf(stocks.get().getAdjclose()),Double.valueOf(22.36));
        Assert.assertEquals(Long.valueOf(stocks.get().getVolume()),Long.valueOf(1200));
        stockRepository.deleteById(Long.valueOf(latest_id+1));
    }
}