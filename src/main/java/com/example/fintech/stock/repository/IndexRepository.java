package com.example.fintech.stock.repository;

import com.example.fintech.stock.model.Index;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface IndexRepository extends JpaRepository<Index,Long> {
    @Query("SELECT e FROM Index e  WHERE e.date=?1")
    List<Index> findByDate(Date date);

    @Query("SELECT e FROM Index e  WHERE e.date between ?1 and ?2")
    List<Index> findByDateIntervals(Date date1,Date date2);

    @Query(value ="SELECT * FROM Index e where year(e.date)=?1 order by close desc",nativeQuery=true)
    List<Index> findByYear(String year);

    @Query("SELECT e FROM Index e where e.volume between ?1 and ?2")
    List<Index> findWhereVolume(Long num1,Long num2);

    @Query("SELECT e FROM Index e  WHERE e.type=?1 ORDER BY e.date")
    List<Index> findByName(String type);

    @Query("SELECT e FROM Index e  WHERE e.close <> e.adjclose")
    List<Index> getCloseUnequllAdjclose();
}
