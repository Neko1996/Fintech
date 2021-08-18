package com.example.fintech.stock.controller;

import com.example.fintech.stock.model.Stock;
import com.example.fintech.stock.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/API")
public class StockController {

    private final StockService stockService;

    @Autowired
    public StockController(StockService stockService){this.stockService=stockService;}

    @GetMapping(produces = "application/json;charset=UTF-8")
    List<Stock> getStocks(){
        return stockService.getStocks();
    }

    @GetMapping(value="/stocks/order",produces = "application/json;charset=UTF-8")
    List<Stock> getStocksByOrder(){
        return stockService.getStockByVolumeOrder();
    }

    @GetMapping(path="{id}")
    public ResponseEntity<Stock> getStock(@PathVariable("id") Long id){
        Optional<Stock> stock = stockService.getStock(id);
        if(stock.isPresent()){
            return new ResponseEntity<Stock>(stock.get(), HttpStatus.OK);
        }
        return new ResponseEntity<Stock>(new Stock(), HttpStatus.NOT_FOUND);
    }

    @GetMapping(value="/stocks/date/{date}",produces = "application/json;charset=UTF-8")
    public List<Stock> getStockByDate(@PathVariable("date") String date) throws ParseException {
        return stockService.getByDate(date);
    }

    @GetMapping(value="/stocks/dateintervals",produces = "application/json;charset=UTF-8")
    public List<Stock> getStockByDateInterval(String from,String to) throws ParseException {
        return stockService.getByDateIntervals(from,to);
    }

    @GetMapping(value="/stocks/company/{cname}",produces = "application/json;charset=UTF-8")
    public List<Stock> getStockByName(@PathVariable("cname") String cname){
        return stockService.findByName(cname);
    }

    @GetMapping(value="/stocks/year/{year}",produces = "application/json;charset=UTF-8")
    public List<Stock> getStockByYear(@PathVariable("year") String year){
        return stockService.findByYear(year);
    }


    @GetMapping(value="/stocks/adjclose/",produces = "application/json;charset=UTF-8")
    public List<Stock> getStockDiffAdjclose(){
        return stockService.getCloseUnequllAdjclose();
    }

    @GetMapping(value="/stocks/minilow/",produces = "application/json;charset=UTF-8")
    public List<Stock> getStockByMinLow(){
        return stockService.findLow();
    }

//    @PathVariable("numleft")
    @GetMapping(value="/stocks/volume",produces = "application/json;charset=UTF-8")
    public List<Stock> getStockByVolume(Long numleft, Long numright){
        System.out.println("numleft:"+numleft);
        System.out.println("numright:"+numright);
        return stockService.findWhereVolume(numleft,numright);
    }

    //public void addStock(@RequestBody Stock stock){stockService.addStock(stock);}
}
