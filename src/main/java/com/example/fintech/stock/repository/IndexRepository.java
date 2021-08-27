package com.example.fintech.stock.repository;

import com.example.fintech.stock.model.IndexP;
import com.example.fintech.stock.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface IndexRepository extends JpaRepository<IndexP,Long> {
    @Query("SELECT e FROM IndexP e  WHERE e.date=?1")
    List<IndexP> findByDate(Date date);

    @Query("SELECT e FROM IndexP e  WHERE e.date between ?1 and ?2")
    List<IndexP> findByDateIntervals(Date date1, Date date2);

    @Query(value ="SELECT * FROM IndexP e where year(e.date)=?1 order by close desc",nativeQuery=true)
    List<IndexP> findByYear(String year);

    @Query("SELECT e FROM IndexP e where e.volume between ?1 and ?2")
    List<IndexP> findWhereVolume(Long num1, Long num2);

    @Query("SELECT e FROM IndexP e  WHERE e.type=?1 ORDER BY e.date")
    List<IndexP> findByName(String type);

    @Query("SELECT e FROM IndexP e  WHERE e.close <> e.adjclose")
    List<IndexP> getCloseUnequllAdjclose();

    @Query(value = "SELECT * FROM indexp e where e.date>=?1 and e.date<=?2 and e.type=?3 limit 10",nativeQuery = true)
    List<IndexP> findByDateIntervalsAndCname(Date date1, Date date2, String type);
}
