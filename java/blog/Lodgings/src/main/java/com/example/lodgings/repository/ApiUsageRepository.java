package com.example.lodgings.repository;

import com.example.lodgings.entity.ApiUsage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ApiUsageRepository extends JpaRepository<ApiUsage, Long> {

    long countByEndpoint(String endpoint);

    long countByStatus(String status);

    long countByTimestampAfter(LocalDateTime since);

    @Query("SELECT a.endpoint, COUNT(a) FROM ApiUsage a GROUP BY a.endpoint")
    List<Object[]> countByEndpoint();

    @Query("SELECT a.status, COUNT(a) FROM ApiUsage a GROUP BY a.status")
    List<Object[]> countByStatus();

    @Query("SELECT a FROM ApiUsage a ORDER BY a.timestamp DESC")
    List<ApiUsage> findAllByOrderByTimestampDesc();

    long countByEndpointAndTimestampAfter(String endpoint, LocalDateTime since);

    @Query("SELECT FUNCTION('DATE', a.timestamp), COUNT(a) FROM ApiUsage a WHERE a.timestamp >= :since GROUP BY FUNCTION('DATE', a.timestamp) ORDER BY FUNCTION('DATE', a.timestamp)")
    List<Object[]> countByDateSince(@Param("since") LocalDateTime since);

    @Query("SELECT a.partner, COUNT(a) FROM ApiUsage a GROUP BY a.partner")
    List<Object[]> countByPartner();

    long countByPartnerAndTimestampAfter(String partner, LocalDateTime since);

    long countByPartner(String partner);
}
