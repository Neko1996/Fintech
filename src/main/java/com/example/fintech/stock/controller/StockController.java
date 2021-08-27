package com.example.fintech.stock.controller;

import com.example.fintech.stock.model.Stock;
import com.example.fintech.stock.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/API")
public class StockController {

    private final StockService stockService;

    @Autowired
    public StockController(StockService stockService){this.stockService=stockService;}


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

    @RequestMapping(value = "/Add")
    public ModelAndView addStock(String date,String open,String high,String low,String close,String cname,String adjclose,String volume)throws ParseException{
        ModelAndView mav=new ModelAndView();
        Stock stock=new Stock();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        Date date1=simpleDateFormat.parse(date);
        stock.setDate(date1);
        stock.setOpen(Double.parseDouble(open));
        stock.setHigh(Double.parseDouble(high));
        stock.setLow(Double.parseDouble(low));
        stock.setClose(Double.parseDouble(close));
        stock.setCname(cname);
        stock.setAdjclose(Double.parseDouble(adjclose));
        stock.setVolume(Long.parseLong(volume));
        stockService.addStock(stock);
        mav.addObject("newstock",stock);
        mav.setViewName("addStock");
        return mav;
    }

    @RequestMapping("/index")
    public String helloThymeleaf()  {
        return "index";
    }

    @RequestMapping("/goToAdd")
    public String helloaddstock()  {
        return "addStock";
    }
//    goToCharts
    @RequestMapping("/goToCharts")
    public String hellocharts()  {
        return "charts";
    }

    @RequestMapping("/ByCondition")
    public ModelAndView getStocksByCondition(String time1,String time2,String cname) throws ParseException {
        ModelAndView mav=new ModelAndView();
        List<Stock> stocklists=stockService.getByDateIntervalsAndCname(time1,time2,cname);
        mav.addObject("defaultlist",stocklists);
        mav.setViewName("stockList");
        return mav;
    }

    @RequestMapping("/stockindex")
    public String getDefaultStocks(Model model) throws ParseException {
        String time1="2012-01-01";
        String time2="2013-01-01";
        String cname="FB";
        List<Stock> stocks=stockService.getByDateIntervalsAndCname(time1,time2,cname);
        model.addAttribute("defaultlist",stocks);
        return "stockList";
    }
}
