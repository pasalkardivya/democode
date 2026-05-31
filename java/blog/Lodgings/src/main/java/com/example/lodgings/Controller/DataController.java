package com.example.lodgings.Controller;

import com.example.lodgings.entity.*;
import com.example.lodgings.repository.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/api/data")
public class DataController {

    private final RoomBookingRepository roomBookingRepo;
    private final FoodOrderRepository foodOrderRepo;
    private final EventBookingRepository eventBookingRepo;
    private final LoginLogRepository loginLogRepo;
    private final CustomerRepository customerRepo;

    public DataController(RoomBookingRepository roomBookingRepo, FoodOrderRepository foodOrderRepo,
                          EventBookingRepository eventBookingRepo, LoginLogRepository loginLogRepo,
                          CustomerRepository customerRepo) {
        this.roomBookingRepo = roomBookingRepo;
        this.foodOrderRepo = foodOrderRepo;
        this.eventBookingRepo = eventBookingRepo;
        this.loginLogRepo = loginLogRepo;
        this.customerRepo = customerRepo;
    }

    // --- LOGIN ---
    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody Map<String, String> body) {
        String email = body.get("email");
        String password = body.get("password");
        String role = body.get("role");

        if ("admin".equals(role)) {
            if (!"pradnya".equals(email) || !"pradnya".equals(password)) {
                return Map.of("success", false, "message", "Invalid admin credentials");
            }
            LoginLog log = new LoginLog();
            log.setEmail("admin@lodgings.com");
            log.setName("Pradnya");
            log.setRole("admin");
            log.setSessionId(UUID.randomUUID().toString());
            loginLogRepo.save(log);
            return Map.of("success", true, "role", "admin", "name", "Pradnya",
                    "email", "admin@lodgings.com", "sessionId", log.getSessionId());
        }

        if (email == null || email.isBlank() || password == null || password.isBlank()) {
            return Map.of("success", false, "message", "Email and password required");
        }

        Optional<Customer> existing = customerRepo.findByEmailAndPassword(email, password);
        if (existing.isEmpty()) {
            return Map.of("success", false, "message", "Invalid email or password. Please register first.");
        }
        Customer customer = existing.get();

        LoginLog log = new LoginLog();
        log.setEmail(email);
        log.setName(customer.getName());
        log.setRole("customer");
        log.setSessionId(UUID.randomUUID().toString());
        loginLogRepo.save(log);

        return Map.of("success", true, "role", "customer", "name", customer.getName(),
                "email", email, "sessionId", log.getSessionId(), "phone", customer.getPhoneNo() != null ? customer.getPhoneNo() : "");
    }

    // --- REGISTER ---
    @PostMapping("/register")
    public Map<String, Object> register(@RequestBody Map<String, String> body) {
        String name = body.get("name");
        String email = body.get("email");
        String phone = body.get("phone");
        String password = body.get("password");

        if (name == null || name.isBlank() || email == null || email.isBlank() || password == null || password.isBlank()) {
            return Map.of("success", false, "message", "Name, email, and password are required");
        }

        if (customerRepo.findByEmail(email).isPresent()) {
            return Map.of("success", false, "message", "Email already registered. Please login.");
        }

        Customer customer = new Customer();
        customer.setName(name);
        customer.setEmail(email);
        customer.setPhoneNo(phone != null ? phone : "");
        customer.setPassword(password);
        customer.setDate(java.time.LocalDate.now().toString());
        customer.setTime(java.time.LocalTime.now().toString());
        customerRepo.save(customer);

        LoginLog log = new LoginLog();
        log.setEmail(email);
        log.setName(name);
        log.setRole("customer");
        log.setSessionId(UUID.randomUUID().toString());
        loginLogRepo.save(log);

        return Map.of("success", true, "role", "customer", "name", name,
                "email", email, "sessionId", log.getSessionId(), "phone", phone != null ? phone : "");
    }

    @PostMapping("/logout")
    public Map<String, String> logout(@RequestBody Map<String, String> body) {
        String sessionId = body.get("sessionId");
        loginLogRepo.findAll().stream()
                .filter(l -> sessionId != null && sessionId.equals(l.getSessionId()))
                .findFirst().ifPresent(l -> { l.setLogoutTime(LocalDateTime.now()); loginLogRepo.save(l); });
        return Map.of("message", "logged out");
    }

    // --- ROOM BOOKINGS ---
    @GetMapping("/bookings")
    public List<RoomBooking> getBookings(@RequestParam(required=false) String email) {
        if (email != null) return roomBookingRepo.findByEmailOrderByCreatedAtDesc(email);
        return roomBookingRepo.findAllByOrderByCreatedAtDesc();
    }

    @PostMapping("/bookings")
    public RoomBooking createBooking(@RequestBody RoomBooking booking) {
        return roomBookingRepo.save(booking);
    }

    @PutMapping("/bookings/{id}")
    public RoomBooking updateBooking(@PathVariable Long id, @RequestBody Map<String, String> body) {
        RoomBooking b = roomBookingRepo.findById(id).orElseThrow();
        if (body.containsKey("status")) b.setStatus(body.get("status"));
        return roomBookingRepo.save(b);
    }

    @DeleteMapping("/bookings/{id}")
    public Map<String, String> deleteBooking(@PathVariable Long id) {
        roomBookingRepo.deleteById(id);
        return Map.of("message", "deleted");
    }

    // --- FOOD ORDERS ---
    @GetMapping("/orders")
    public List<FoodOrder> getOrders(@RequestParam(required=false) String email) {
        if (email != null) return foodOrderRepo.findByEmailOrderByCreatedAtDesc(email);
        return foodOrderRepo.findAllByOrderByCreatedAtDesc();
    }

    @PostMapping("/orders")
    public FoodOrder createOrder(@RequestBody FoodOrder order) {
        return foodOrderRepo.save(order);
    }

    @PutMapping("/orders/{id}")
    public FoodOrder updateOrder(@PathVariable Long id, @RequestBody Map<String, String> body) {
        FoodOrder o = foodOrderRepo.findById(id).orElseThrow();
        if (body.containsKey("status")) o.setStatus(body.get("status"));
        return foodOrderRepo.save(o);
    }

    @DeleteMapping("/orders/{id}")
    public Map<String, String> deleteOrder(@PathVariable Long id) {
        foodOrderRepo.deleteById(id);
        return Map.of("message", "deleted");
    }

    // --- EVENT BOOKINGS ---
    @GetMapping("/events")
    public List<EventBooking> getEvents(@RequestParam(required=false) String email) {
        if (email != null) return eventBookingRepo.findByEmailOrderByCreatedAtDesc(email);
        return eventBookingRepo.findAllByOrderByCreatedAtDesc();
    }

    @PostMapping("/events")
    public EventBooking createEvent(@RequestBody EventBooking event) {
        return eventBookingRepo.save(event);
    }

    @PutMapping("/events/{id}")
    public EventBooking updateEvent(@PathVariable Long id, @RequestBody Map<String, String> body) {
        EventBooking e = eventBookingRepo.findById(id).orElseThrow();
        if (body.containsKey("status")) e.setStatus(body.get("status"));
        return eventBookingRepo.save(e);
    }

    @DeleteMapping("/events/{id}")
    public Map<String, String> deleteEvent(@PathVariable Long id) {
        eventBookingRepo.deleteById(id);
        return Map.of("message", "deleted");
    }

    // --- LOGIN LOGS ---
    @GetMapping("/logs")
    public List<LoginLog> getLogs() {
        return loginLogRepo.findAllByOrderByLoginTimeDesc();
    }

    // --- DASHBOARD STATS ---
    @GetMapping("/stats")
    public Map<String, Object> getStats() {
        Map<String, Object> stats = new HashMap<>();
        stats.put("totalCustomers", customerRepo.count());
        stats.put("totalBookings", roomBookingRepo.count());
        stats.put("totalOrders", foodOrderRepo.count());
        stats.put("totalEvents", eventBookingRepo.count());
        stats.put("totalLogins", loginLogRepo.count());
        stats.put("confirmedBookings", roomBookingRepo.countByStatus("confirmed"));
        stats.put("confirmedOrders", foodOrderRepo.countByStatus("confirmed"));
        return stats;
    }
}
