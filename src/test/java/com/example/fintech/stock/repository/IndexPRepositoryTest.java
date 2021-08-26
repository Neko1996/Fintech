package com.example.fintech.stock.repository;

import com.example.fintech.stock.model.IndexP;
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
class IndexPRepositoryTest {
    @Autowired
    private IndexRepository indexRepository;

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    Long latest_id= Long.valueOf(1);

    @BeforeEach
    void get_latest_id()throws ParseException {
        Date date = simpleDateFormat.parse("2021-08-25");
        IndexP indexP =new IndexP(date,1.25,2.56,2.31,2.73,2.73,Long.valueOf(1200),"Today");
        indexRepository.save(indexP);
        List<IndexP> indexPS =indexRepository.findAll();
        latest_id= indexPS.get(0).getId();
        System.out.println("latest_id_is:"+latest_id);
    }

    @AfterEach
    void delete_last_test_record(){
        indexRepository.deleteById(latest_id);
    }

    @Test
    void Test_findByDate() throws ParseException{
        Date date0 = simpleDateFormat.parse("2020-01-01");
        IndexP  index0=new IndexP(date0,21.25,23.56,20.31,22.73,22.36,Long.valueOf(1200),"dateTest");
        indexRepository.save(index0);

        Date date1 = simpleDateFormat.parse("2020-01-01");
        IndexP index1=new IndexP(date1,21.25,23.56,20.31,22.73,22.36,Long.valueOf(1200),"dateTest");
        indexRepository.save(index1);

        Date date2 = simpleDateFormat.parse("2021-01-01");
        IndexP index2=new IndexP(date2,21.25,23.56,20.31,22.73,22.36,Long.valueOf(1200),"dateTest");
        indexRepository.save(index2);

        Date date = simpleDateFormat.parse("2020-01-01");
        List<IndexP> stocks=indexRepository.findByDate(date);
        Assert.assertEquals(Long.valueOf(stocks.size()), Long.valueOf(2));
        for(int i=0;i<stocks.size();i++){
            Assert.assertEquals(stocks.get(i).getType(),"dateTest");
            Assert.assertEquals(Double.valueOf(stocks.get(i).getOpen()),Double.valueOf(21.25));
            Assert.assertEquals(Double.valueOf(stocks.get(i).getHigh()),Double.valueOf(23.56));
            Assert.assertEquals(Double.valueOf(stocks.get(i).getLow()),Double.valueOf(20.31));
            Assert.assertEquals(Double.valueOf(stocks.get(i).getClose()),Double.valueOf(22.73));
            Assert.assertEquals(Double.valueOf(stocks.get(i).getAdjclose()),Double.valueOf(22.36));
            Assert.assertEquals(Long.valueOf(stocks.get(i).getVolume()),Long.valueOf(1200));
        }
        for(int i=1;i<=3;i++){
            indexRepository.deleteById(Long.valueOf(latest_id+i));
        }
    }

    @Test
    void findByDateIntervals() throws ParseException{
        Date date0 = simpleDateFormat.parse("2000-01-01");
        IndexP index0=new IndexP(date0,21.25,23.56,20.31,22.73,22.36,Long.valueOf(1200),"testDateInterval");
        indexRepository.save(index0);

        Date date1 = simpleDateFormat.parse("2002-01-01");
        IndexP index1=new IndexP(date1,21.25,23.56,20.31,22.73,22.36,Long.valueOf(1200),"testDateInterval");
        indexRepository.save(index1);

        Date date2 = simpleDateFormat.parse("2021-01-01");
        IndexP index2=new IndexP(date2,21.25,23.56,20.31,22.73,22.36,Long.valueOf(1200),"testDateInterval");
        indexRepository.save(index2);

        Date test1 = simpleDateFormat.parse("2000-01-01");
        Date test2 = simpleDateFormat.parse("2002-01-01");
        List<IndexP> indexs=indexRepository.findByDateIntervals(test1,test2);
        Assert.assertEquals(Long.valueOf(indexs.size()), Long.valueOf(2));
        for(int i=0;i<indexs.size();i++){
            String time=simpleDateFormat.format(indexs.get(i).getDate());
            Assert.assertEquals(indexs.get(i).getType(),"testDateInterval");
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
        IndexP index0=new IndexP(date0,21.25,23.56,21.31,22.73,22.36,Long.valueOf(1200),"YearTest");
        indexRepository.save(index0);

        Date date1 = simpleDateFormat.parse("2020-01-01");
        IndexP index1=new IndexP(date1,21.25,23.56,21.31,22.73,22.36,Long.valueOf(1200),"YearTest");
        indexRepository.save(index1);

        Date date2 = simpleDateFormat.parse("2021-01-01");
        IndexP index2=new IndexP(date2,21.25,23.56,23.31,22.73,22.73,Long.valueOf(1200),"YearTest");
        indexRepository.save(index2);

        List<IndexP> indexs=indexRepository.findByYear("2020");


        for(int i=0;i<indexs.size();i++){
            String time=simpleDateFormat.format(indexs.get(i).getDate());
            String[] times=time.split("-");
            Assert.assertEquals(times[0],"2020");
            Assert.assertEquals(indexs.get(i).getType(),"YearTest");
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
    void findWhereVolume() {
    }

    @Test
    void findByName() throws ParseException{
        Date date2 = simpleDateFormat.parse("2021-01-01");
        IndexP index2=new IndexP(date2,21.25,23.56,20.31,22.73,22.36,Long.valueOf(1200),"nameTest");
        indexRepository.save(index2);
        List<IndexP> indexs=indexRepository.findByName("nameTest");
        Assert.assertEquals(Long.valueOf(indexs.size()), Long.valueOf(1));
        for(int i=0;i<indexs.size();i++){
            Assert.assertEquals(indexs.get(i).getType(),"nameTest");
            Assert.assertEquals(Double.valueOf(indexs.get(i).getOpen()),Double.valueOf(21.25));
            Assert.assertEquals(Double.valueOf(indexs.get(i).getHigh()),Double.valueOf(23.56));
            Assert.assertEquals(Double.valueOf(indexs.get(i).getLow()),Double.valueOf(20.31));
            Assert.assertEquals(Double.valueOf(indexs.get(i).getClose()),Double.valueOf(22.73));
            Assert.assertEquals(Double.valueOf(indexs.get(i).getAdjclose()),Double.valueOf(22.36));
            Assert.assertEquals(Long.valueOf(indexs.get(i).getVolume()),Long.valueOf(1200));
        }
        for(int i=1;i<=1;i++){
            indexRepository.deleteById(Long.valueOf(latest_id+i));
        }
    }

    @Test
    void getCloseUnequllAdjclose() throws ParseException{
        Date date0 = simpleDateFormat.parse("2020-01-01");
        IndexP index0=new IndexP(date0,21.25,23.56,21.31,22.73,23.36,Long.valueOf(1200),"Adj");
        indexRepository.save(index0);

        Date date1 = simpleDateFormat.parse("2020-01-01");
        IndexP index1=new IndexP(date1,21.25,23.56,21.31,22.73,23.36,Long.valueOf(1200),"Adj");
        indexRepository.save(index1);

        Date date2 = simpleDateFormat.parse("2020-01-01");
        IndexP index2=new IndexP(date2,21.25,23.56,21.31,22.73,22.73,Long.valueOf(1200),"Adj");
        indexRepository.save(index2);

        List<IndexP> indexs=indexRepository.getCloseUnequllAdjclose();
        Assert.assertEquals(Long.valueOf(indexs.size()), Long.valueOf(2));
        for(int i=0;i<indexs.size();i++){
            String time=simpleDateFormat.format(indexs.get(i).getDate());
            String[] times=time.split("-");
            Assert.assertEquals(times[0],"2020");
            Assert.assertEquals(indexs.get(i).getType(),"Adj");
            Assert.assertEquals(Double.valueOf(indexs.get(i).getOpen()),Double.valueOf(21.25));
            Assert.assertEquals(Double.valueOf(indexs.get(i).getHigh()),Double.valueOf(23.56));
            Assert.assertEquals(Double.valueOf(indexs.get(i).getLow()),Double.valueOf(21.31));
            Assert.assertEquals(Double.valueOf(indexs.get(i).getClose()),Double.valueOf(22.73));
            Assert.assertEquals(Double.valueOf(indexs.get(i).getAdjclose()),Double.valueOf(23.36));
            Assert.assertEquals(Long.valueOf(indexs.get(i).getVolume()),Long.valueOf(1200));
        }
        for(int i=1;i<=3;i++){
            indexRepository.deleteById(Long.valueOf(latest_id+i));
        }
    }
}