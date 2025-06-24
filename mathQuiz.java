import java.util.Random;
import java.util.Scanner;

public class mathQuiz {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("welcome to Math Quiz!");
        System.out.println("Please enter your name:");
        String name = sc.nextLine();
        System.out.println("Hello"+" "+name+"!"+" "+"You have 10 seconds per each question.");
        System.out.println("Lets Begin!");
        int score = 0;
        for(int i =1;i<=5;i++){
            score += askQuestion(i);
        }
        System.out.println("\n Quiz completed!");
        System.out.println("Your score is:"+" "+score);
        if(score == 5){
            System.out.println("Excellent Score!");
        }
        else if (score>=3) {
            System.out.println("Good Score!");
        }
        else{
            System.out.println("Keep trying more!");
        }
    }

    public static int askQuestion(int questionNumber){
        Random random = new Random();
        int num1 = random.nextInt(50)+1;
        int num2 = random.nextInt(50)+1;
        char operator = getRandomOperator(random);
        int correctAnswer = calculate(num1,num2,operator);
        System.out.println("Q" + questionNumber + ": What is " + num1 + " " + operator + " " + num2 + "?");
        System.out.print("Answer (you have 10 seconds): ");
        Long startTime = System.currentTimeMillis();
        String userInput = waitForInput(10_000);
        Long endTime = System.currentTimeMillis();

        if (userInput == null) {
            System.out.println("\n Time's up! You didn't answer in time.");
            return 0;
        }
        try {
            int userAnswer = Integer.parseInt(userInput.trim());
            if (userAnswer == correctAnswer) {
                System.out.println("Correct!");
                return 1;
            } else {
                System.out.println("Wrong! Correct answer is " + correctAnswer);
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input! It should be a number.");
        }
        System.out.println("You took " + ((endTime - startTime) / 1000.0) + " seconds.\n");
        return 0;
    }

    public static char getRandomOperator(Random random){
        char[] operator = {'+', '-', '*', '/'};
        return operator[random.nextInt(4)];
    }

    public static int calculate(int num1,int num2, char operator){
        switch (operator) {
            case '+':
                return num1+num2;
            case '-':
                return num1-num2;
            case '*':
                return num1*num2;
            case '/':
                return num2!=0? num1/num2 : 0;
            default:
                return 0;
        }
    }

    public static String waitForInput(long timeoutMillis) {
        long start = System.currentTimeMillis();
        while ((System.currentTimeMillis() - start) < timeoutMillis) {
            if (sc.hasNextLine()) {
                return sc.nextLine();
            }
        }
        return null;
    }
}