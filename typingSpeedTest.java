import java.time.LocalTime;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class typingSpeedTest {
    static String[] easySentences = {
            "The sun is bright today.",
            "I love eating fresh apples.",
            "Coding is fun and exciting.",
            "Java is a great programming language."
    };

    static String[] mediumSentences = {
            "Hard work and dedication lead to success.",
            "The quick brown fox jumps over the lazy dog.",
            "Programming requires patience and creativity.",
            "Artificial intelligence is shaping the future of technology."
    };

    static String[] hardSentences = {
            "Success is not final, failure is not fatal: it is the courage to continue that counts.",
            "The only limit to our realization of tomorrow is our doubts of today.",
            "If you want to achieve greatness, stop asking for permission."
    };

    public static void main(String[] args) throws InterruptedException {
        Scanner scan = new Scanner(System.in);
        Random rand = new Random();

        System.out.println("🎯 Welcome to the Typing Speed Test!");
        System.out.println("Choose difficulty level: (1) Easy (2) Medium (3) Hard");
        int choice = scan.nextInt();
        scan.nextLine(); // Consume newline

        String sentence = "";
        if (choice == 1) {
            sentence = easySentences[rand.nextInt(easySentences.length)];
        } else if (choice == 2) {
            sentence = mediumSentences[rand.nextInt(mediumSentences.length)];
        } else {
            sentence = hardSentences[rand.nextInt(hardSentences.length)];
        }

        // Countdown
        System.out.println("\nGet ready to type...");
        for (int i = 3; i > 0; i--) {
            System.out.println(i);
            TimeUnit.SECONDS.sleep(1);
        }

        System.out.println("\nType this sentence:\n👉 " + sentence);

        double start = LocalTime.now().toNanoOfDay();
        String userTyped = scan.nextLine();
        double end = LocalTime.now().toNanoOfDay();

        double elapsedTime = (end - start) / 1_000_000_000.0; // Convert to seconds
        int numChars = userTyped.length();
        int wpm = (int) ((((double) numChars / 5) / elapsedTime) * 60);

        System.out.println("\n⌛ Time taken: " + elapsedTime + " seconds");
        System.out.println("⚡ Your WPM: " + wpm);

        // Calculate accuracy
        int correctChars = 0;
        int minLength = Math.min(userTyped.length(), sentence.length());
        for (int i = 0; i < minLength; i++) {
            if (userTyped.charAt(i) == sentence.charAt(i)) {
                correctChars++;
            }
        }
        double accuracy = ((double) correctChars / sentence.length()) * 100;
        System.out.printf("🎯 Accuracy: %.2f%%\n", accuracy);

        if (wpm > 80) {
            System.out.println("🚀 Amazing speed! You're a pro typist!");
        } else if (wpm > 50) {
            System.out.println("🔥 Great job! Keep practicing!");
        } else if (wpm > 30) {
            System.out.println("💪 Good effort! Try to improve your speed.");
        } else {
            System.out.println("😅 Keep practicing, you'll get better!");
        }

        scan.close();
    }
}
