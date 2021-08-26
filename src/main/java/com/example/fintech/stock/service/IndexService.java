package com.example.fintech.stock.service;

import com.example.fintech.stock.model.IndexP;
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
    public List<IndexP> getByDateIntervals(String time1, String time2) throws ParseException {
        System.out.println(time1+" "+time2);
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        Date date1=simpleDateFormat.parse(time1);
        Date date2=simpleDateFormat.parse(time2);
        List<IndexP> indexPS =indexRepository.findByDateIntervals(date1,date2);
        return indexPS;
    }

//    public List<Index> findWhereVolume(Long num1,Long num2){
//        List<Index> indexs=indexRepository.findWhereVolume(num1,num2);
//        return indexs;
//    }

    public List<IndexP> findByYear(String year){
        List<IndexP> indexPS =indexRepository.findByYear(year);
        return indexPS;
    }

    public List<IndexP> findByName(String type){
        List<IndexP> indexPS =indexRepository.findByName(type);
        return indexPS;
    }

    public List<IndexP> getByDate(String time) throws ParseException {
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        Date date=simpleDateFormat.parse(time);
        List<IndexP> indexPS =indexRepository.findByDate(date);
        return indexPS;
    }

    public List<IndexP> getAdustedCloseLists(){
        List<IndexP> indexPS =indexRepository.getCloseUnequllAdjclose();
        return indexPS;
    }
}
