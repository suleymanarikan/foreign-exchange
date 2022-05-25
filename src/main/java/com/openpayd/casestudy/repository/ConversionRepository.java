package com.openpayd.casestudy.repository;

import com.openpayd.casestudy.domain.Conversion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface ConversionRepository extends JpaRepository<Conversion, Long> {

    Page<Conversion> findConversionByTransactionIdOrTransactionDateOrderByCreatedAt(String transactionId, LocalDate transactionDate, Pageable pageable);


}
