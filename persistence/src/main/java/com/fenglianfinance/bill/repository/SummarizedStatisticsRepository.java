package com.fenglianfinance.bill.repository;

import com.fenglianfinance.bill.domain.SummarizedStatistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SummarizedStatisticsRepository extends JpaRepository<SummarizedStatistics, Long>, SummarizedStatisticsRepositoryCustom //
{

    @Query(" select s from SummarizedStatistics s where s.summarizedDate>=:start and s.summarizedDate<=:end ")
    public List<SummarizedStatistics> findBySummarizedDate(@Param("start") LocalDate start, @Param("end") LocalDate end);

    @Query(" select s from SummarizedStatistics s where s.summarizedDate>=:start and s.summarizedDate<=:end ")
    public Page<SummarizedStatistics> findBySummarizedDate(@Param("start") LocalDate start, @Param("end") LocalDate end, Pageable page);

}
