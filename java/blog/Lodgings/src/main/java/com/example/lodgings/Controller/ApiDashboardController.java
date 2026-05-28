package com.example.lodgings.Controller;

import com.example.lodgings.entity.ApiUsage;
import com.example.lodgings.service.ApiUsageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

@Controller
public class ApiDashboardController {

    private final ApiUsageService apiUsageService;

    public ApiDashboardController(ApiUsageService apiUsageService) {
        this.apiUsageService = apiUsageService;
    }

    @GetMapping("/api-dashboard")
    public String dashboard(Model model) {
        long totalCalls = apiUsageService.getTotalCalls();
        long todayCalls = apiUsageService.getTodayCalls();
        long weekCalls = apiUsageService.getThisWeekCalls();
        long monthCalls = apiUsageService.getThisMonthCalls();

        Map<String, Long> callsPerEndpoint = apiUsageService.getCallsPerEndpoint();
        Map<String, Long> callsPerStatus = apiUsageService.getCallsPerStatus();
        Map<String, Long> todayPerEndpoint = apiUsageService.getTodayCallsPerEndpoint();
        List<ApiUsage> recentUsage = apiUsageService.getRecentUsage();

        model.addAttribute("totalCalls", totalCalls);
        model.addAttribute("todayCalls", todayCalls);
        model.addAttribute("weekCalls", weekCalls);
        model.addAttribute("monthCalls", monthCalls);
        model.addAttribute("callsPerEndpoint", callsPerEndpoint);
        model.addAttribute("callsPerStatus", callsPerStatus);
        model.addAttribute("todayPerEndpoint", todayPerEndpoint);
        model.addAttribute("recentUsage", recentUsage);

        model.addAttribute("partnerStats", apiUsageService.getCallsPerPartner());
        model.addAttribute("zomatoToday", apiUsageService.getPartnerTodayCalls("Zomato"));
        model.addAttribute("swiggyToday", apiUsageService.getPartnerTodayCalls("Swiggy"));
        model.addAttribute("zomatoTotal", apiUsageService.getPartnerTotalCalls("Zomato"));
        model.addAttribute("swiggyTotal", apiUsageService.getPartnerTotalCalls("Swiggy"));

        return "api-dashboard";
    }
}
