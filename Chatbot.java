import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Chatbot {

    private static final Map<String, List<String>> INTENTS = new HashMap<>();
    private static final Map<String, String> RESPONSES = new HashMap<>();

    static {
        // Define intents and their possible user inputs
        INTENTS.put("greeting", new ArrayList<>(List.of("hello", "hi", "good morning", "good afternoon", "good evening")));
        INTENTS.put("goodbye", new ArrayList<>(List.of("bye", "goodbye", "see you later")));
        INTENTS.put("thanks", new ArrayList<>(List.of("thank you", "thanks")));
        INTENTS.put("help", new ArrayList<>(List.of("help", "can you help me")));
        INTENTS.put("weather", new ArrayList<>(List.of("weather", "what's the weather like", "how's the weather")));
        INTENTS.put("time", new ArrayList<>(List.of("time", "what time is it")));
        INTENTS.put("joke", new ArrayList<>(List.of("tell me a joke", "joke")));

        // Define responses for each intent
        RESPONSES.put("greeting", "Hello! How can I assist you today?");
        RESPONSES.put("goodbye", "Goodbye! Have a great day.");
        RESPONSES.put("thanks", "You're welcome!");
        RESPONSES.put("help", "I can help you with various tasks. Just tell me what you need.");
        RESPONSES.put("weather", "I'm sorry, I don't have access to real-time weather information.");
        RESPONSES.put("time", "The current time is [insert actual time here]");
        RESPONSES.put("joke", "Why don't scientists trust atoms? Because they make up everything!");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Chatbot is ready. Please type your message:");

        while (true) { String userInput = scanner.nextLine().toLowerCase();

            // Detect user intent
            String detectedIntent = detectIntent(userInput);

            // Generate response based on detected intent
            String response = RESPONSES.getOrDefault(detectedIntent, "I'm sorry, I don't understand. Can you rephrase?");

            System.out.println("Chatbot: " + response);

            if (detectedIntent.equals("goodbye")) {
                break;
            }
        }

        scanner.close();
    }

    // Detect user intent based on keywords
    private static String detectIntent(String userInput) {
        for (Map.Entry<String, List<String>> entry : INTENTS.entrySet()) {
            String intent = entry.getKey();
            List<String> keywords = entry.getValue();
            for (String keyword : keywords) {
                if (userInput.contains(keyword)) {
                    return intent;
                }
            }
        }
        return "unknown";
    }
}
