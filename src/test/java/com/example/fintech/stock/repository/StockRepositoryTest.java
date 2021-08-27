package com.example.fintech.stock.repository;

import com.example.fintech.stock.model.Stock;
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

@SpringBootTest
@RunWith(SpringRunner.class)
class StockRepositoryTest {

    @Autowired
    private StockRepository stockRepository;

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    Long latest_id= Long.valueOf(1);

    @BeforeEach
    void get_latest_id()throws ParseException{
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = simpleDateFormat.parse("1111-08-25");
        Stock stock=new Stock(date,1.25,2.56,2.31,2.73,2.73,Long.valueOf(1200),"Today");
        stockRepository.save(stock);
        List<Stock> stocks=stockRepository.findAll();
        latest_id=stocks.get(0).getId();
        System.out.println("latest_id_is:"+latest_id);
    }

    @AfterEach
    void delete_last_test_record(){
        stockRepository.deleteById(latest_id);
    }

    @Test
    void Test_findByDate() throws ParseException{
        Date date0 = simpleDateFormat.parse("2020-01-01");
        Stock stock0=new Stock(date0,21.25,23.56,20.31,22.73,22.36,Long.valueOf(1200),"dateTest");
        stockRepository.save(stock0);

        Date date1 = simpleDateFormat.parse("2020-01-01");
        Stock stock1=new Stock(date1,21.25,23.56,20.31,22.73,22.36,Long.valueOf(1200),"dateTest");
        stockRepository.save(stock1);

        Date date2 = simpleDateFormat.parse("2021-01-01");
        Stock stock2=new Stock(date2,21.25,23.56,20.31,22.73,22.36,Long.valueOf(1200),"dateTest");
        stockRepository.save(stock2);

        Date date = simpleDateFormat.parse("2020-01-01");
        List<Stock> stocks=stockRepository.findByDate(date);
        Assert.assertEquals(Long.valueOf(stocks.size()), Long.valueOf(2));
        for(int i=0;i<stocks.size();i++){
            Assert.assertEquals(stocks.get(i).getCname(),"dateTest");
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
    void Test_findByDateIntervals() throws ParseException{
        Date date0 = simpleDateFormat.parse("2000-01-01");
        Stock stock0=new Stock(date0,21.25,23.56,20.31,22.73,22.36,Long.valueOf(1200),"testDateInterval");
        stockRepository.save(stock0);

        Date date1 = simpleDateFormat.parse("2002-01-01");
        Stock stock1=new Stock(date1,21.25,23.56,20.31,22.73,22.36,Long.valueOf(1200),"testDateInterval");
        stockRepository.save(stock1);

        Date date2 = simpleDateFormat.parse("2021-01-01");
        Stock stock2=new Stock(date2,21.25,23.56,20.31,22.73,22.36,Long.valueOf(1200),"testDateInterval");
        stockRepository.save(stock2);

        Date test1 = simpleDateFormat.parse("2000-01-01");
        Date test2 = simpleDateFormat.parse("2002-01-01");
        List<Stock> stocks=stockRepository.findByDateIntervals(test1,test2);
        Assert.assertEquals(Long.valueOf(stocks.size()), Long.valueOf(2));
        for(int i=0;i<stocks.size();i++){
            String time=simpleDateFormat.format(stocks.get(i).getDate());
            Assert.assertEquals(stocks.get(i).getCname(),"testDateInterval");
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
    void findByYear() throws ParseException{
        Date date0 = simpleDateFormat.parse("2020-01-01");
        Stock stock0=new Stock(date0,21.25,23.56,21.31,22.73,22.36,Long.valueOf(1200),"YearTest");
        stockRepository.save(stock0);

        Date date1 = simpleDateFormat.parse("2020-01-01");
        Stock stock1=new Stock(date1,21.25,23.56,21.31,22.73,22.36,Long.valueOf(1200),"YearTest");
        stockRepository.save(stock1);

        Date date2 = simpleDateFormat.parse("2021-01-01");
        Stock stock2=new Stock(date2,21.25,23.56,23.31,22.73,22.73,Long.valueOf(1200),"YearTest");
        stockRepository.save(stock2);

        List<Stock> stocks=stockRepository.findByYear("2020");


        for(int i=0;i<stocks.size();i++){
            String time=simpleDateFormat.format(stocks.get(i).getDate());
            String[] times=time.split("-");
            Assert.assertEquals(times[0],"2020");
            Assert.assertEquals(stocks.get(i).getCname(),"YearTest");
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
    void Test_findByName() throws ParseException {
        Date date2 = simpleDateFormat.parse("2021-01-01");
        Stock stock2=new Stock(date2,21.25,23.56,20.31,22.73,22.36,Long.valueOf(1200),"nameTest");
        stockRepository.save(stock2);
        List<Stock> stocks=stockRepository.findByName("nameTest");
        Assert.assertEquals(Long.valueOf(stocks.size()), Long.valueOf(1));
        for(int i=0;i<stocks.size();i++){
            Assert.assertEquals(stocks.get(i).getCname(),"nameTest");
            Assert.assertEquals(Double.valueOf(stocks.get(i).getOpen()),Double.valueOf(21.25));
            Assert.assertEquals(Double.valueOf(stocks.get(i).getHigh()),Double.valueOf(23.56));
            Assert.assertEquals(Double.valueOf(stocks.get(i).getLow()),Double.valueOf(20.31));
            Assert.assertEquals(Double.valueOf(stocks.get(i).getClose()),Double.valueOf(22.73));
            Assert.assertEquals(Double.valueOf(stocks.get(i).getAdjclose()),Double.valueOf(22.36));
            Assert.assertEquals(Long.valueOf(stocks.get(i).getVolume()),Long.valueOf(1200));
        }
        for(int i=1;i<=1;i++){
            stockRepository.deleteById(Long.valueOf(latest_id+i));
        }
    }

    @Test
    void Test_getCloseUnequllAdjclose() throws ParseException{
        Date date0 = simpleDateFormat.parse("2020-01-01");
        Stock stock0=new Stock(date0,21.25,23.56,21.31,22.73,23.36,Long.valueOf(1200),"Adj");
        stockRepository.save(stock0);

        Date date1 = simpleDateFormat.parse("2020-01-01");
        Stock stock1=new Stock(date1,21.25,23.56,21.31,22.73,23.36,Long.valueOf(1200),"Adj");
        stockRepository.save(stock1);

        Date date2 = simpleDateFormat.parse("2020-01-01");
        Stock stock2=new Stock(date2,21.25,23.56,21.31,22.73,22.73,Long.valueOf(1200),"Adj");
        stockRepository.save(stock2);

        List<Stock> stocks=stockRepository.getCloseUnequllAdjclose();
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
}