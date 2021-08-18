package com.example.fintech.stock.repository;

import com.example.fintech.stock.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface StockRepository extends JpaRepository<Stock,Long> {

    @Query("SELECT e FROM Stock e  WHERE e.date=?1")
    List<Stock> findByDate(Date date);

    @Query("SELECT e FROM Stock e  WHERE e.date between ?1 and ?2")
    List<Stock> findByDateIntervals(Date date1,Date date2);

    @Query(value ="SELECT * FROM stock e where year(e.date)=?1 order by close desc",nativeQuery=true)
    List<Stock> findByYear(String year);

    @Query("SELECT e FROM Stock e ORDER BY e.close ASC")
    List<Stock> findByVolumeOrder();

    @Query("SELECT e FROM Stock e where e.volume between ?1 and ?2")
    List<Stock> findWhereVolume(Long num1,Long num2);

    @Query("SELECT e FROM Stock e  WHERE e.cname=?1 ORDER BY e.volume")
    List<Stock> findByName(String cname);

    @Query("SELECT e FROM Stock e  WHERE e.close <> e.adjclose")
    List<Stock> getCloseUnequllAdjclose();

    @Query("SELECT e FROM Stock e where e.low=(SELECT MIN(low) FROM Stock )")
    List<Stock> findLow();

    
}
