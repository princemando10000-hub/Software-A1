import java.util.Scanner;

public class MillionaireGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);    
        System.out.print("\nEnter your name: ");
        String playerName = scanner.nextLine();
        
        System.out.println("RULES:");
        System.out.println("• Answer 15 questions to win $1,000,000");
        System.out.println("• Question 5 and 10 are SAFE HAVENS");
        System.out.println("• You can WALK AWAY before any question (except Q1)");
        System.out.println("• Walking away gives you money from your LAST answered question");
        
        // Game data
        String[] questions = new String[15];
        String[][] options = new String[15][4];
        int[] correctAnswers = new int[15];
        int[] prizeMoney = {100, 200, 300, 500, 1000, 2000, 4000, 8000, 16000, 32000, 64000, 125000, 250000, 500000, 1000000};
        int[] milestones = {0, 1000, 32000};
        
        // Easy Questions (1-5)
        questions[0] = "What is the capital of France?";
        options[0] = new String[]{"London", "Berlin", "Paris", "Madrid"};
        correctAnswers[0] = 2; // Paris
        
        questions[1] = "How many continents are there on Earth?";
        options[1] = new String[]{"5", "6", "7", "8"};
        correctAnswers[1] = 2; // 7
        
        questions[2] = "Which animal is known as the 'King of the Jungle'?";
        options[2] = new String[]{"Elephant", "Lion", "Tiger", "Gorilla"};
        correctAnswers[2] = 1; // Lion
        
        questions[3] = "What is the largest ocean on Earth?";
        options[3] = new String[]{"Atlantic", "Indian", "Arctic", "Pacific"};
        correctAnswers[3] = 3; // Pacific
        
        questions[4] = "How many players are on a soccer team?";
        options[4] = new String[]{"9", "10", "11", "12"};
        correctAnswers[4] = 2; // 11
        
        // Medium Questions (6-10)
        questions[5] = "Who painted the Mona Lisa?";
        options[5] = new String[]{"Van Gogh", "Picasso", "Da Vinci", "Rembrandt"};
        correctAnswers[5] = 2; // Da Vinci
        
        questions[6] = "What is the chemical symbol for gold?";
        options[6] = new String[]{"Go", "Gd", "Au", "Ag"};
        correctAnswers[6] = 2; // Au
        
        questions[7] = "In which year did the Titanic sink?";
        options[7] = new String[]{"1905", "1912", "1918", "1923"};
        correctAnswers[7] = 1; // 1912
        
        questions[8] = "What is the hardest natural substance on Earth?";
        options[8] = new String[]{"Iron", "Diamond", "Platinum", "Titanium"};
        correctAnswers[8] = 1; // Diamond
        
        questions[9] = "Who wrote 'Romeo and Juliet'?";
        options[9] = new String[]{"Charles Dickens", "William Shakespeare", "Mark Twain", "Jane Austen"};
        correctAnswers[9] = 1; // Shakespeare
        
        // Hard Questions (11-15)
        questions[10] = "What is the smallest prime number greater than 100?";
        options[10] = new String[]{"101", "103", "107", "109"};
        correctAnswers[10] = 0; // 101
        
        questions[11] = "Which element has the atomic number 79?";
        options[11] = new String[]{"Silver", "Gold", "Platinum", "Lead"};
        correctAnswers[11] = 1; // Gold
        
        questions[12] = "Who was the first woman to win a Nobel Prize?";
        options[12] = new String[]{"Marie Curie", "Rosalind Franklin", "Ada Lovelace", "Florence Nightingale"};
        correctAnswers[12] = 0; // Marie Curie
        
        questions[13] = "What is the longest river in the world?";
        options[13] = new String[]{"Amazon", "Nile", "Yangtze", "Mississippi"};
        correctAnswers[13] = 1; // Nile
        
        questions[14] = "In what year did the Berlin Wall fall?";
        options[14] = new String[]{"1987", "1988", "1989", "1990"};
        correctAnswers[14] = 2; // 1989

        // Game variables
        int currentQuestionIndex = 0;
        int currentWinnings = 0;
        int guaranteedMoney = 0;
        boolean gameActive = true;
        // Main game loop
        while (gameActive && currentQuestionIndex < 15) {
            int questionNum = currentQuestionIndex + 1;
            // For questions 2-15, ask if they want to walk away first
            if (questionNum > 1) {
                System.out.println("\n" + "=".repeat(60));
                System.out.println("CURRENT WINNINGS: $" + currentWinnings);
                if (guaranteedMoney > 0) {
                    System.out.println("SAFE HAVEN: You're guaranteed $" + guaranteedMoney);
                }
                System.out.println("NEXT: QUESTION " + questionNum + " OF 15");
                System.out.println("=".repeat(60));
                
                System.out.println("\n OPTIONS:");
                System.out.println("C) Continue to next question");
                System.out.println("W) Walk away now (take $" + currentWinnings + ")");
                System.out.print("\nYour choice (C/W): ");
                
                String choice = scanner.nextLine().toUpperCase();
                while (!choice.equals("C") && !choice.equals("W")) {
                    System.out.print("Invalid choice. Please enter C or W: ");
                    choice = scanner.nextLine().toUpperCase();
                }
                
                if (choice.equals("W")) {
                    System.out.println("\n You walk away with $" + currentWinnings + "!");
                    gameActive = false;
                    break;
                }
            }

            displayQuestion(questions, options, prizeMoney, currentQuestionIndex, questionNum, guaranteedMoney, milestones);
        
            System.out.print("\nYour answer (A/B/C/D): ");
            String answer = scanner.nextLine().toUpperCase();
            
            while (answer.length() != 1 || answer.charAt(0) < 'A' || answer.charAt(0) > 'D') {
                System.out.print("Invalid answer! Please enter A, B, C, or D: ");
                answer = scanner.nextLine().toUpperCase();
            }
            
            int answerIndex = answer.charAt(0) - 'A';
            
            if (answerIndex == correctAnswers[currentQuestionIndex]) {
                currentWinnings = prizeMoney[currentQuestionIndex];
                System.out.println("\n CORRECT! You now have $" + currentWinnings);
                // Update guaranteed money (milestones)
                if (questionNum == 5) {
                    guaranteedMoney = 1000;
                    System.out.println("\n YOU'VE REACHED THE FIRST MILESTONE! You're guaranteed $1,000!");
                    System.out.println("Even if you fail later, you'll leave with $1,000.");
                } else if (questionNum == 10) {
                    guaranteedMoney = 32000;
                    System.out.println("\n YOU'VE REACHED THE SECOND MILESTONE! You're guaranteed $32,000!");
                    System.out.println("Even if you fail later, you'll leave with $32,000.");
                }
                // Check if they won the game
                if (questionNum == 15) {
                    System.out.println("\n CONGRATULATIONS! YOU ARE A MILLIONAIRE!");
                    gameActive = false;
                } else {
                    System.out.println("\nPress Enter to continue...");
                    scanner.nextLine();
                    currentQuestionIndex++;
                }
            } else {
                // Wrong answer
                char correctLetter = (char)('A' + correctAnswers[currentQuestionIndex]);
                System.out.println("\n WRONG ANSWER!");
                System.out.println("The correct answer was " + correctLetter + ") " + 
                                  options[currentQuestionIndex][correctAnswers[currentQuestionIndex]]);
                
                currentWinnings = guaranteedMoney;
                System.out.println("GAME OVER! You leave with $" + currentWinnings);
                gameActive = false;
            }
        }
        
    }
    // Helper method to display the question
    public static void displayQuestion(String[] questions, String[][] options, int[] prizeMoney, 
                                      int index, int questionNum, int guaranteedMoney, int[] milestones) {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("PRIZE FOR THIS QUESTION: $" + prizeMoney[index]);
        
        // Show guaranteed money message
        if (guaranteedMoney > 0) {
            System.out.println("SAFE HAVEN: You're guaranteed $" + guaranteedMoney);
        } else {
            System.out.println("WARNING: No guaranteed money yet!");
        }
        
        // Show difficulty
        String difficulty;
        if (questionNum <= 5) difficulty = "EASY";
        else if (questionNum <= 10) difficulty = "MEDIUM";
        else difficulty = "HARD";
        
        System.out.println("QUESTION " + questionNum + " OF 15 (" + difficulty + ")");
        System.out.println("=".repeat(60));
        
        // Display question
        System.out.println("\n" + questions[index]);
        System.out.println();
        char letter = 'A';
        for (String option : options[index]) {
            System.out.println(letter + ") " + option);
            letter++;
        }
    }
}