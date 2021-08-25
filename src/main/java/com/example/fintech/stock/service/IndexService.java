package com.example.fintech.stock.service;

import com.example.fintech.stock.model.Index;
import com.example.fintech.stock.repository.IndexRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class IndexService {
    @Autowired
    private IndexRepository indexRepository;
    public List<Index> getByDateIntervals(String time1, String time2) throws ParseException {
        System.out.println(time1+" "+time2);
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        Date date1=simpleDateFormat.parse(time1);
        Date date2=simpleDateFormat.parse(time2);
        List<Index> indexs=indexRepository.findByDateIntervals(date1,date2);
        return indexs;
    }

//    public List<Index> findWhereVolume(Long num1,Long num2){
//        List<Index> indexs=indexRepository.findWhereVolume(num1,num2);
//        return indexs;
//    }

    public List<Index> findByYear(String year){
        List<Index> indexs=indexRepository.findByYear(year);
        return indexs;
    }

    public List<Index> findByName(String type){
        List<Index> indexs=indexRepository.findByName(type);
        return indexs;
    }

    public List<Index> getByDate(String time) throws ParseException {
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        Date date=simpleDateFormat.parse(time);
        List<Index> indexs=indexRepository.findByDate(date);
        return indexs;
    }
}
