package com.example.fintech.stock.service;

import com.example.fintech.stock.model.Stock;
import com.example.fintech.stock.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
//@Transactional
public class StockService {
    @Autowired
    private StockRepository stockRepository;

    public List<Stock>  getStocks(){
        List<Stock> list = stockRepository.findAll();
        return list;
    }

    public Optional<Stock> getStock(Long id){
        Optional<Stock> stock= stockRepository.findById(id);
        return stock;
    }

    public List<Stock> getStockByVolumeOrder(){
        List<Stock> stocks= stockRepository.findByVolumeOrder();
        return stocks;
    }

    public List<Stock> getByDate(String time) throws ParseException {
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        Date date=simpleDateFormat.parse(time);
//        List<Stock> stocks=stockRepository.findByDate(date);
//        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        Date date = LocalDate.parse(time, fmt);
//        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
//        System.out.println(sqlDate.getClass()+" "+sqlDate);

        List<Stock> stocks=stockRepository.findByDate(date);
//        System.out.println("==len=="+stocks.size());
//        for(int i=0;i<stocks.size();i++){
//            Stock temp=(Stock) stocks.get(i);
//            System.out.println(temp.getDate()+" "+temp.getId());
//        }
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

    public List<Stock> findWhereVolume(Long num1,Long num2){
        List<Stock> stocks=stockRepository.findWhereVolume(num1,num2);
        return stocks;
    }

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

    public List<Stock> findLow(){
        List<Stock> stocks=stockRepository.findLow();
        return stocks;
    }


}
