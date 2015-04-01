package com.fenglianfinance.bill.repository;

import com.fenglianfinance.bill.domain.BankCardInfo;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BankCardInfoRepository extends JpaRepository<BankCardInfo, Long> {

    public List<BankCardInfo> findByUserId(Long id);

    public BankCardInfo findByUserIdAndCardId(Long id, String openAccId);

}
