package com.fenglianfinance.bill.repository;

import com.fenglianfinance.bill.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface MessageRepository extends JpaRepository<Message, Long>, //
        JpaSpecificationExecutor<Message> {

    @Modifying
    @Query("update Message set read=?2 where to.id =?1 and read=false")
    public void batchUpdateReadStatus(Long userId);

}
