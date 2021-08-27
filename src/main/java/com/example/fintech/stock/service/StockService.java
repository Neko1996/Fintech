package com.example.fintech.stock.service;

import com.example.fintech.stock.model.Stock;
import com.example.fintech.stock.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
//@Transactional
public class StockService {
    @Autowired
    private StockRepository stockRepository;

    public List<Stock> getByDate(String time) throws ParseException {
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        Date date=simpleDateFormat.parse(time);
        List<Stock> stocks=stockRepository.findByDate(date);
        return stocks;
    }

    public List<Stock> getByDateIntervals(String time1,String time2) throws ParseException{
        System.out.println(time1+" "+time2);
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        Date date1=simpleDateFormat.parse(time1);
        Date date2=simpleDateFormat.parse(time2);
        List<Stock> stocks=stockRepository.findByDateIntervals(date1,date2);
        return stocks;
    }

//    public List<Stock> findWhereVolume(Long num1,Long num2){
//        List<Stock> stocks=stockRepository.findWhereVolume(num1,num2);
//        return stocks;
//    }

    public List<Stock> findByYear(String year){
       List<Stock> stocks=stockRepository.findByYear(year);
       return stocks;
    }

    public List<Stock> findByName(String cname){
        List<Stock> stocks=stockRepository.findByName(cname);
        return stocks;
    }

    public  List<Stock> getCloseUnequllAdjclose(){
        List<Stock> stocks=stockRepository.getCloseUnequllAdjclose();
        return stocks;
    }

    public void addStock(Stock stock){
        stockRepository.save(stock);
    }

    public List<Stock> getByDateIntervalsAndCname(String time1,String time2,String cname) throws ParseException{
        System.out.println(time1+" "+time2);
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        Date date1=simpleDateFormat.parse(time1);
        Date date2=simpleDateFormat.parse(time2);
        List<Stock> stocks=stockRepository.findByDateIntervalsAndCname(date1,date2,cname);
        return stocks;
    }
}
