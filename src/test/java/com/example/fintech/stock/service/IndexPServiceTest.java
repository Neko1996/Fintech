package com.example.fintech.stock.service;

import com.example.fintech.stock.model.IndexP;
import com.example.fintech.stock.repository.IndexRepository;
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
class IndexPServiceTest {
    @Autowired
    private IndexRepository indexRepository;

    @Autowired
    private IndexService indexService;

    Long latest_id= Long.valueOf(1);

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    @BeforeEach
    void get_latest_id() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = simpleDateFormat.parse("2021-08-25");
        IndexP index=new IndexP(date,21.25,23.56,20.31,20.73,20.73,Long.valueOf(1200),"Today");
        indexRepository.save(index);

        List<IndexP> indexs=indexRepository.findAll();
        latest_id=indexs.get(0).getId();
        System.out.println("latest_id_is:"+latest_id);
    }

    @AfterEach
    void delete_last_test_record(){
        indexRepository.deleteById(latest_id);
    }

    @Test
    void getByDateIntervals() throws ParseException{
        Date date0 = simpleDateFormat.parse("2000-01-01");
        IndexP index0=new IndexP(date0,21.25,23.56,20.31,22.73,22.36,Long.valueOf(1200),"Test");
        indexRepository.save(index0);

        Date date1 = simpleDateFormat.parse("2002-01-01");
        IndexP index1=new IndexP(date1,21.25,23.56,20.31,22.73,22.36,Long.valueOf(1200),"Test");
        indexRepository.save(index1);

        Date date2 = simpleDateFormat.parse("2021-01-01");
        IndexP index2=new IndexP(date2,21.25,23.56,20.31,22.73,22.36,Long.valueOf(1200),"Test");
        indexRepository.save(index2);

        List<IndexP> indexs=indexService.getByDateIntervals("2000-01-01","2002-01-01");
        Assert.assertEquals(Long.valueOf(indexs.size()), Long.valueOf(2));
        for(int i=0;i<indexs.size();i++){
            String time=simpleDateFormat.format(indexs.get(i).getDate());
            Assert.assertEquals(indexs.get(i).getType(),"Test");
            Assert.assertEquals(Double.valueOf(indexs.get(i).getOpen()),Double.valueOf(21.25));
            Assert.assertEquals(Double.valueOf(indexs.get(i).getHigh()),Double.valueOf(23.56));
            Assert.assertEquals(Double.valueOf(indexs.get(i).getLow()),Double.valueOf(20.31));
            Assert.assertEquals(Double.valueOf(indexs.get(i).getClose()),Double.valueOf(22.73));
            Assert.assertEquals(Double.valueOf(indexs.get(i).getAdjclose()),Double.valueOf(22.36));
            Assert.assertEquals(Long.valueOf(indexs.get(i).getVolume()),Long.valueOf(1200));
        }
        for(int i=1;i<=3;i++){
            indexRepository.deleteById(Long.valueOf(latest_id+i));
        }
    }

    @Test
    void findByYear() throws ParseException{
        Date date0 = simpleDateFormat.parse("2020-01-01");
        IndexP index0=new IndexP(date0,21.25,23.56,21.31,22.73,22.36,Long.valueOf(1200),"Test");
        indexRepository.save(index0);

        Date date1 = simpleDateFormat.parse("2020-01-01");
        IndexP index1=new IndexP(date1,21.25,23.56,21.31,22.73,22.36,Long.valueOf(1200),"Test");
        indexRepository.save(index1);

        Date date2 = simpleDateFormat.parse("2021-01-01");
        IndexP index2=new IndexP(date2,21.25,23.56,23.31,22.73,22.73,Long.valueOf(1200),"Test");
        indexRepository.save(index2);

        List<IndexP> indexs=indexService.findByYear("2020");


        for(int i=0;i<indexs.size();i++){
            String time=simpleDateFormat.format(indexs.get(i).getDate());
            String[] times=time.split("-");
            Assert.assertEquals(times[0],"2020");
            Assert.assertEquals(indexs.get(i).getType(),"Test");
            Assert.assertEquals(Double.valueOf(indexs.get(i).getOpen()),Double.valueOf(21.25));
            Assert.assertEquals(Double.valueOf(indexs.get(i).getHigh()),Double.valueOf(23.56));
            Assert.assertEquals(Double.valueOf(indexs.get(i).getLow()),Double.valueOf(21.31));
            Assert.assertEquals(Double.valueOf(indexs.get(i).getClose()),Double.valueOf(22.73));
            Assert.assertEquals(Double.valueOf(indexs.get(i).getAdjclose()),Double.valueOf(22.36));
            Assert.assertEquals(Long.valueOf(indexs.get(i).getVolume()),Long.valueOf(1200));
        }
        for(int i=1;i<=3;i++){
            indexRepository.deleteById(Long.valueOf(latest_id+i));
        }
    }

    @Test
    void findByName() throws ParseException{
        Date date0 = simpleDateFormat.parse("2020-01-01");
        IndexP index0=new IndexP(date0,21.25,23.56,21.31,22.73,22.36,Long.valueOf(1200),"FN");
        indexRepository.save(index0);

        Date date1 = simpleDateFormat.parse("2020-01-01");
        IndexP index1=new IndexP(date1,21.25,23.56,21.31,22.73,22.36,Long.valueOf(1200),"FN");
        indexRepository.save(index1);

        Date date2 = simpleDateFormat.parse("2021-01-01");
        IndexP index2=new IndexP(date2,21.25,23.56,23.31,22.73,22.73,Long.valueOf(1200),"FNB");
        indexRepository.save(index2);

        List<IndexP> indexs=indexService.findByName("FN");
        Assert.assertEquals(Long.valueOf(indexs.size()), Long.valueOf(2));

        for(int i=0;i<indexs.size();i++){
            String time=simpleDateFormat.format(indexs.get(i).getDate());
            String[] times=time.split("-");
            Assert.assertEquals(times[0],"2020");
            Assert.assertEquals(indexs.get(i).getType(),"FN");
            Assert.assertEquals(Double.valueOf(indexs.get(i).getOpen()),Double.valueOf(21.25));
            Assert.assertEquals(Double.valueOf(indexs.get(i).getHigh()),Double.valueOf(23.56));
            Assert.assertEquals(Double.valueOf(indexs.get(i).getLow()),Double.valueOf(21.31));
            Assert.assertEquals(Double.valueOf(indexs.get(i).getClose()),Double.valueOf(22.73));
            Assert.assertEquals(Double.valueOf(indexs.get(i).getAdjclose()),Double.valueOf(22.36));
            Assert.assertEquals(Long.valueOf(indexs.get(i).getVolume()),Long.valueOf(1200));
        }
        for(int i=1;i<=3;i++){
            indexRepository.deleteById(Long.valueOf(latest_id+i));
        }
    }

    @Test
    void getByDate() throws ParseException {
        Date date0 = simpleDateFormat.parse("2020-01-01");
        IndexP index0=new IndexP(date0,21.25,23.56,20.31,22.73,22.36,Long.valueOf(1200),"Test");
        indexRepository.save(index0);

        Date date1 = simpleDateFormat.parse("2020-01-01");
        IndexP index1=new IndexP(date1,21.25,23.56,20.31,22.73,22.36,Long.valueOf(1200),"Test");
        indexRepository.save(index1);

        Date date2 = simpleDateFormat.parse("2021-01-01");
        IndexP index2=new IndexP(date2,21.25,23.56,20.31,22.73,22.36,Long.valueOf(1200),"Test");
        indexRepository.save(index2);

        List<IndexP> indexs=indexService.getByDate("2020-01-01");
        Assert.assertEquals(Long.valueOf(indexs.size()), Long.valueOf(2));
        for(int i=0;i<2;i++){
            Assert.assertEquals(indexs.get(i).getType(),"Test");
            Assert.assertEquals(Double.valueOf(indexs.get(i).getOpen()),Double.valueOf(21.25));
            Assert.assertEquals(Double.valueOf(indexs.get(i).getHigh()),Double.valueOf(23.56));
            Assert.assertEquals(Double.valueOf(indexs.get(i).getLow()),Double.valueOf(20.31));
            Assert.assertEquals(Double.valueOf(indexs.get(i).getClose()),Double.valueOf(22.73));
            Assert.assertEquals(Double.valueOf(indexs.get(i).getAdjclose()),Double.valueOf(22.36));
            Assert.assertEquals(Long.valueOf(indexs.get(i).getVolume()),Long.valueOf(1200));
        }
        for(int i=1;i<=3;i++){
            indexRepository.deleteById(Long.valueOf(latest_id+i));
        }
    }

    @Test
    void getAdustedCloseLists() {
    }
}