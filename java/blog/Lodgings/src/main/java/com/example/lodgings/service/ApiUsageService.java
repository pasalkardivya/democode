package com.example.lodgings.service;

import com.example.lodgings.entity.ApiUsage;
import com.example.lodgings.repository.ApiUsageRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ApiUsageService {

    private final ApiUsageRepository apiUsageRepository;

    public ApiUsageService(ApiUsageRepository apiUsageRepository) {
        this.apiUsageRepository = apiUsageRepository;
    }

    public void recordUsage(String endpoint, String method, String status, String ipAddress, String apiKey) {
        recordUsage(endpoint, method, status, ipAddress, apiKey, "Unknown");
    }

    public void recordUsage(String endpoint, String method, String status, String ipAddress, String apiKey, String partner) {
        ApiUsage usage = new ApiUsage(endpoint, method, status, ipAddress, apiKey, partner);
        apiUsageRepository.save(usage);
    }

    public long getTotalCalls() {
        return apiUsageRepository.count();
    }

    public Map<String, Long> getCallsPerEndpoint() {
        Map<String, Long> result = new HashMap<>();
        List<Object[]> rows = apiUsageRepository.countByEndpoint();
        for (Object[] row : rows) {
            result.put((String) row[0], (Long) row[1]);
        }
        return result;
    }

    public Map<String, Long> getCallsPerStatus() {
        Map<String, Long> result = new HashMap<>();
        List<Object[]> rows = apiUsageRepository.countByStatus();
        for (Object[] row : rows) {
            result.put((String) row[0], (Long) row[1]);
        }
        return result;
    }

    public long getTodayCalls() {
        LocalDateTime startOfDay = LocalDateTime.of(LocalDate.now(), LocalTime.MIDNIGHT);
        return apiUsageRepository.countByTimestampAfter(startOfDay);
    }

    public long getThisWeekCalls() {
        LocalDateTime startOfWeek = LocalDateTime.now().with(java.time.DayOfWeek.MONDAY).with(LocalTime.MIDNIGHT);
        return apiUsageRepository.countByTimestampAfter(startOfWeek);
    }

    public long getThisMonthCalls() {
        LocalDateTime startOfMonth = LocalDateTime.of(LocalDate.now().withDayOfMonth(1), LocalTime.MIDNIGHT);
        return apiUsageRepository.countByTimestampAfter(startOfMonth);
    }

    public Map<String, Long> getTodayCallsPerEndpoint() {
        Map<String, Long> result = new HashMap<>();
        LocalDateTime startOfDay = LocalDateTime.of(LocalDate.now(), LocalTime.MIDNIGHT);
        String[] endpoints = {"/api/listings", "/api/create", "/api/update", "/api/cancel"};
        for (String ep : endpoints) {
            result.put(ep, apiUsageRepository.countByEndpointAndTimestampAfter(ep, startOfDay));
        }
        return result;
    }

    public List<ApiUsage> getRecentUsage() {
        return apiUsageRepository.findAllByOrderByTimestampDesc();
    }

    public Map<String, Long> getCallsPerPartner() {
        Map<String, Long> result = new HashMap<>();
        List<Object[]> rows = apiUsageRepository.countByPartner();
        for (Object[] row : rows) {
            result.put((String) row[0], (Long) row[1]);
        }
        return result;
    }

    public long getPartnerTodayCalls(String partner) {
        LocalDateTime startOfDay = LocalDateTime.of(LocalDate.now(), LocalTime.MIDNIGHT);
        return apiUsageRepository.countByPartnerAndTimestampAfter(partner, startOfDay);
    }

    public long getPartnerTotalCalls(String partner) {
        return apiUsageRepository.countByPartner(partner);
    }
}
