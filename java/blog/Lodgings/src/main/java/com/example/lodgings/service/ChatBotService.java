package com.example.lodgings.service;

import com.example.lodgings.entity.ChatMessage;
import com.example.lodgings.repository.ChatMessageRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class ChatBotService {

    private final ChatMessageRepository chatMessageRepository;

    private static final Map<String, String> ROOMS = new HashMap<>();
    private static final Map<String, String> MENU = new HashMap<>();
    private static final Map<String, String> EVENTS = new HashMap<>();

    static {
        ROOMS.put("Single", "₹2,499/night - 1 bed, 1 bath, WiFi, TV");
        ROOMS.put("Double", "₹3,999/night - 2 beds, 1 bath, WiFi, TV, Mini bar");
        ROOMS.put("Deluxe", "₹5,999/night - King bed, luxury bath, WiFi, TV, Mini bar, Balcony");
        ROOMS.put("Suite", "₹9,999/night - Living room, bedroom, luxury bath, WiFi, TV, Mini bar, City view");
        ROOMS.put("Penthouse", "₹15,999/night - 2BHK, panoramic view, jacuzzi, butler service, WiFi");
        ROOMS.put("Villa", "₹22,999/night - Private pool, 3BHK, garden, kitchen, WiFi, parking");

        MENU.put("Breakfast", "Pancakes ₹250, French Toast ₹220, Idli ₹180, Smoothie Bowl ₹280");
        MENU.put("Lunch", "Grilled Sandwich ₹350, Chicken Biryani ₹420, Pasta ₹380, Caesar Salad ₹320");
        MENU.put("Dinner", "Grill Platter ₹650, Butter Chicken ₹450, Noodles ₹380, Dal Makhani ₹320");
        MENU.put("Dessert", "Chocolate Lava ₹320, Tiramisu ₹280, Ice Cream ₹180, Cheesecake ₹350");

        EVENTS.put("Birthday", "₹8,999 - Decor, cake, music, 10 guests (extra ₹500/guest)");
        EVENTS.put("Anniversary", "₹12,499 - Candlelight dinner, flowers, cake, 8 guests (extra ₹600/guest)");
        EVENTS.put("Date Night", "₹5,999 - 3-course meal, wine, flower arrangement, 2 guests");
    }

    public ChatBotService(ChatMessageRepository chatMessageRepository) {
        this.chatMessageRepository = chatMessageRepository;
    }

    public Map<String, Object> processMessage(String message, String sessionId) {
        if (sessionId == null || sessionId.isBlank()) {
            sessionId = UUID.randomUUID().toString();
        }

        String reply = generateReply(message.toLowerCase().trim());
        String session = sessionId;

        ChatMessage chatMessage = new ChatMessage("customer", message, reply, session);
        chatMessageRepository.save(chatMessage);

        Map<String, Object> result = new HashMap<>();
        result.put("reply", reply);
        result.put("sessionId", session);
        return result;
    }

    private String generateReply(String msg) {
        if (msg.contains("single") || msg.contains("double") || msg.contains("deluxe")
                || msg.contains("suite") || msg.contains("penthouse") || msg.contains("villa")) {
            for (Map.Entry<String, String> entry : ROOMS.entrySet()) {
                if (msg.contains(entry.getKey().toLowerCase())) {
                    return "**" + entry.getKey() + "**: " + entry.getValue()
                            + "\n\nWant to book this room? Reply: *book " + entry.getKey().toLowerCase() + "*";
                }
            }
        }

        if (msg.contains("room") || msg.contains("stay") || msg.contains("book")) {
            StringBuilder sb = new StringBuilder("We have these room types:\n");
            for (Map.Entry<String, String> e : ROOMS.entrySet()) {
                sb.append("• **").append(e.getKey()).append("** — ").append(e.getValue()).append("\n");
            }
            sb.append("\nReply with a room name (e.g. *deluxe*) to see details, or *book [room]* to book.");
            return sb.toString();
        }

        if (msg.contains("breakfast") || msg.contains("lunch") || msg.contains("dinner") || msg.contains("dessert")) {
            for (Map.Entry<String, String> entry : MENU.entrySet()) {
                if (msg.contains(entry.getKey().toLowerCase())) {
                    return "**" + entry.getKey() + " Menu:**\n" + entry.getValue()
                            + "\n\nWant to order? Reply: *order " + entry.getKey().toLowerCase() + "*";
                }
            }
        }

        if (msg.contains("menu") || msg.contains("food") || msg.contains("eat") || msg.contains("hungry")) {
            return "We serve:\n• **Breakfast** — Pancakes, French Toast, Idli, Smoothie Bowl\n• **Lunch** — Sandwiches, Biryani, Pasta, Salads\n• **Dinner** — Grills, Indian curries, Noodles\n• **Dessert** — Cakes, Tiramisu, Ice Cream\n\nReply with a meal time (e.g. *lunch*) to see full menu & prices.";
        }

        if (msg.contains("birthday") || msg.contains("anniversary") || msg.contains("date") || msg.contains("celebrate") || msg.contains("event") || msg.contains("party")) {
            StringBuilder sb = new StringBuilder("Special Celebration Packages:\n");
            for (Map.Entry<String, String> e : EVENTS.entrySet()) {
                sb.append("• **").append(e.getKey()).append("** — ").append(e.getValue()).append("\n");
            }
            sb.append("\nReply with *book birthday* or *book anniversary* or *book date night* to reserve.");
            return sb.toString();
        }

        if (msg.contains("location") || msg.contains("address") || msg.contains("reach") || msg.contains("map") || msg.contains("lavasa") || msg.contains("where")) {
            return "📍 **Lodgings** is in **Lavasa, Maharashtra**\n\nAddress: Dasve, Lavasa, Pune 412112\n📞 +91 98765 43210\n\n**How to Reach:**\n• By Road: 3 hrs from Mumbai, 1 hr from Pune\n• By Rail: Nearest station — Pune Junction (65 km)\n• By Air: Nearest airport — Pune International Airport (70 km)\n\nCheck-in: 2:00 PM | Check-out: 11:00 AM";
        }

        if (msg.contains("hi") || msg.contains("hello") || msg.contains("hey") || msg.contains("good morning") || msg.contains("good evening")) {
            return "👋 Welcome to **Lodgings & Restaurant**!\n\nI can help you with:\n• 🛏️ **Room Booking** — Say *rooms* to see types & prices\n• 🍽️ **Food Ordering** — Say *menu* to see what we serve\n• 🎉 **Event Booking** — Say *events* for celebration packages\n• 📍 **Location** — Say *location* for directions\n\nHow can I assist you today?";
        }

        return "I'm not sure I understand. Here's what I can help with:\n• *rooms* — Room types & booking\n• *menu* — Food menu & ordering\n• *events* — Event packages\n• *location* — How to reach us\n• *help* — Show this again";
    }

    public List<ChatMessage> getAllConversations() {
        return chatMessageRepository.findAllByOrderByTimestampDesc();
    }

    public long getTotalConversations() {
        return chatMessageRepository.count();
    }
}
