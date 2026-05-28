package com.example.lodgings.Controller;

import com.example.lodgings.entity.Customer;
import com.example.lodgings.exception.ErrorResponse;
import com.example.lodgings.service.ApiUsageService;
import com.example.lodgings.service.CustomerService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Value("${api.partner.key:lodgings-partner-key-2024}")
    private String validApiKey;

    private final CustomerService customerService;
    private final ApiUsageService apiUsageService;

    public ApiController(CustomerService customerService, ApiUsageService apiUsageService) {
        this.customerService = customerService;
        this.apiUsageService = apiUsageService;
    }

    private String extractApiKey(HttpServletRequest request) {
        String apiKey = request.getHeader("X-API-Key");
        if (apiKey == null || apiKey.isBlank()) {
            apiKey = request.getParameter("api_key");
        }
        return apiKey;
    }

    private String extractPartner(HttpServletRequest request) {
        String partner = request.getHeader("X-Partner");
        if (partner == null || partner.isBlank()) {
            partner = "Unknown";
        }
        return partner;
    }

    private boolean isValidApiKey(String apiKey) {
        return validApiKey.equals(apiKey);
    }

    private String getClientIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.isBlank()) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    private ResponseEntity<ErrorResponse> unauthorizedResponse(String apiKey, String endpoint, HttpServletRequest request) {
        apiUsageService.recordUsage(endpoint, request.getMethod(), "FAILED", getClientIp(request), apiKey != null ? apiKey : "none", extractPartner(request));
        ErrorResponse error = new ErrorResponse(
                HttpStatus.UNAUTHORIZED.value(),
                "Unauthorized",
                "Invalid or missing API key. Provide via X-API-Key header or ?api_key= parameter."
        );
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
    }

    @GetMapping("/listings")
    public ResponseEntity<?> getListings(HttpServletRequest request) {
        String apiKey = extractApiKey(request);
        String partner = extractPartner(request);
        if (!isValidApiKey(apiKey)) {
            return unauthorizedResponse(apiKey, "/api/listings", request);
        }
        try {
            List<Customer> customers = customerService.getAllCustomers();
            apiUsageService.recordUsage("/api/listings", "GET", "SUCCESS", getClientIp(request), apiKey, partner);
            return ResponseEntity.ok(customers);
        } catch (Exception e) {
            apiUsageService.recordUsage("/api/listings", "GET", "FAILED", getClientIp(request), apiKey, partner);
            ErrorResponse error = new ErrorResponse(
                    HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal Server Error", "Failed to fetch listings"
            );
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createBooking(@RequestBody Customer customer, HttpServletRequest request) {
        String apiKey = extractApiKey(request);
        String partner = extractPartner(request);
        if (!isValidApiKey(apiKey)) {
            return unauthorizedResponse(apiKey, "/api/create", request);
        }
        try {
            Customer saved = customerService.saveCustomer(customer);
            apiUsageService.recordUsage("/api/create", "POST", "SUCCESS", getClientIp(request), apiKey, partner);
            return ResponseEntity.status(HttpStatus.CREATED).body(saved);
        } catch (Exception e) {
            apiUsageService.recordUsage("/api/create", "POST", "FAILED", getClientIp(request), apiKey, partner);
            ErrorResponse error = new ErrorResponse(
                    HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal Server Error", "Failed to create booking"
            );
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateBooking(@PathVariable Long id, @RequestBody Customer customer, HttpServletRequest request) {
        String apiKey = extractApiKey(request);
        String partner = extractPartner(request);
        if (!isValidApiKey(apiKey)) {
            return unauthorizedResponse(apiKey, "/api/update", request);
        }
        try {
            Customer updated = customerService.updateCustomer(id, customer);
            apiUsageService.recordUsage("/api/update", "PUT", "SUCCESS", getClientIp(request), apiKey, partner);
            return ResponseEntity.ok(updated);
        } catch (Exception e) {
            apiUsageService.recordUsage("/api/update", "PUT", "FAILED", getClientIp(request), apiKey, partner);
            ErrorResponse error = new ErrorResponse(
                    HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal Server Error", e.getMessage()
            );
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    @DeleteMapping("/cancel/{id}")
    public ResponseEntity<?> cancelBooking(@PathVariable Long id, HttpServletRequest request) {
        String apiKey = extractApiKey(request);
        String partner = extractPartner(request);
        if (!isValidApiKey(apiKey)) {
            return unauthorizedResponse(apiKey, "/api/cancel", request);
        }
        try {
            String message = customerService.deleteCustomer(id);
            apiUsageService.recordUsage("/api/cancel", "DELETE", "SUCCESS", getClientIp(request), apiKey, partner);
            return ResponseEntity.ok(Map.of("message", message));
        } catch (Exception e) {
            apiUsageService.recordUsage("/api/cancel", "DELETE", "FAILED", getClientIp(request), apiKey, partner);
            ErrorResponse error = new ErrorResponse(
                    HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal Server Error", e.getMessage()
            );
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }
}
