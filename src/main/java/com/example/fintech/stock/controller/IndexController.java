package com.example.fintech.stock.controller;

import com.example.fintech.stock.model.IndexP;
import com.example.fintech.stock.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.util.List;

@Controller
@RequestMapping("/API")
public class IndexController {

    private final IndexService indexService;
    @Autowired
    public IndexController(IndexService indexService){this.indexService=indexService;}

    @GetMapping(value="/indexs/date/{date}",produces = "application/json;charset=UTF-8")
    public List<IndexP> getIndexByDate(@PathVariable("date") String date) throws ParseException {
        return indexService.getByDate(date);
    }

    @GetMapping(value="/indexs/dateintervals",produces = "application/json;charset=UTF-8")
    public List<IndexP> getIndexByDateInterval(String from, String to) throws ParseException {
        return indexService.getByDateIntervals(from,to);
    }

    @GetMapping(value="/indexs/company/{type}",produces = "application/json;charset=UTF-8")
    public List<IndexP> getIndexByName(@PathVariable("type") String type){
        return indexService.findByName(type);
    }

    @GetMapping(value="/indexs/year/{year}",produces = "application/json;charset=UTF-8")
    public List<IndexP> getIndexByYear(@PathVariable("year") String year){
        return indexService.findByYear(year);}

    @GetMapping(value="/indexs/adjclose/",produces = "application/json;charset=UTF-8")
    public List<IndexP> getIndexDiffAdjclose(){
        return indexService.getAdustedCloseLists();
    }

//    getByDateIntervalsType
    @RequestMapping("/ByConditionIndex")
    public ModelAndView getIndexsByCondition(String time1, String time2, String type) throws ParseException {
        ModelAndView mav=new ModelAndView();
        List<IndexP> indexs=indexService.getByDateIntervalsType(time1,time2,type);
        mav.addObject("indexlist",indexs);
        mav.setViewName("indexList");
        return mav;
    }

    @RequestMapping("/indexdefult")
    public String getDefaultIndexs(Model model) throws ParseException {
//        System.out.println("===Before get indexs===");
        String time1="2012-01-01";
        String time2="2013-01-01";
        String type="DOWJ";
        List<IndexP> indexs=indexService.getByDateIntervalsType(time1,time2,type);
//        System.out.println("========After indexs======"+indexs.size()+"=============");
        model.addAttribute("indexlist",indexs);
        return "indexList";
    }
}
