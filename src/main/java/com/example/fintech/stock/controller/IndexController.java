package com.example.fintech.stock.controller;

import com.example.fintech.stock.model.Index;
import com.example.fintech.stock.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/API")
public class IndexController {

    private final IndexService indexService;
    @Autowired
    public IndexController(IndexService indexService){this.indexService=indexService;}

    @GetMapping(value="/indexs/date/{date}",produces = "application/json;charset=UTF-8")
    public List<Index> getStockByDate(@PathVariable("date") String date) throws ParseException {
        return indexService.getByDate(date);
    }

    @GetMapping(value="/indexs/dateintervals",produces = "application/json;charset=UTF-8")
    public List<Index> getStockByDateInterval(String from,String to) throws ParseException {
        return indexService.getByDateIntervals(from,to);
    }

    @GetMapping(value="/indexs/company/{type}",produces = "application/json;charset=UTF-8")
    public List<Index> getStockByName(@PathVariable("type") String type){
        return indexService.findByName(type);
    }

    @GetMapping(value="/indexs/year/{year}",produces = "application/json;charset=UTF-8")
    public List<Index> getStockByYear(@PathVariable("year") String year){
        return indexService.findByYear(year);}
}
