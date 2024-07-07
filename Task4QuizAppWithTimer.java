
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Task4QuizAppWithTimer {
    static class Question {
        String question;
        String[] options;
        char answer;

        Question(String question, String[] options, char answer) {
            this.question = question;
            this.options = options;
            this.answer = answer;
        }
    }

    static Question[] quiz = {
        new Question("What is the capital of France?", 
                     new String[]{"A. London", "B. Berlin", "C. Paris", "D. Madrid"}, 'C'),
        new Question("Which planet is known as the Red Planet?", 
                     new String[]{"A. Earth", "B. Mars", "C. Jupiter", "D. Saturn"}, 'B'),
        new Question("Who wrote 'To Kill a Mockingbird'?", 
                     new String[]{"A. Harper Lee", "B. J.K. Rowling", "C. Mark Twain", "D. Charles Dickens"}, 'A')
    };

    static int score = 0;
    static int currentQuestionIndex = 0;
    static boolean answered = false;
    static Timer timer = new Timer();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        for (Question q : quiz) {
            displayQuestion(q);
            startTimer(10, scanner);
            
            while (!answered) {
                // Wait for user to answer or timer to run out
            }
            answered = false;
        }

        displayResult();
        scanner.close();
    }

    static void displayQuestion(Question q) {
        System.out.println("\n" + q.question);
        for (String option : q.options) {
            System.out.println(option);
        }
        System.out.println("You have 10 seconds to answer...");
    }

    static void startTimer(int seconds, Scanner scanner) {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (!answered) {
                    System.out.println("\nTime's up!");
                    checkAnswer(' ');
                    answered = true;
                }
            }
        }, seconds * 1000);

        new Thread(() -> {
            char userAnswer = scanner.next().toUpperCase().charAt(0);
            if (!answered) {
                checkAnswer(userAnswer);
                answered = true;
            }
        }).start();
    }

    static void checkAnswer(char userAnswer) {
        Question currentQuestion = quiz[currentQuestionIndex];
        currentQuestionIndex++;
        if (userAnswer == currentQuestion.answer) {
            System.out.println("Correct!");
            score++;
        } else {
            System.out.println("Wrong! The correct answer is " + currentQuestion.answer);
        }
    }

    static void displayResult() {
        System.out.println("\nYour final score is: " + score + " out of " + quiz.length);
        System.out.println("\nSummary:");
        for (int i = 0; i < quiz.length; i++) {
            Question q = quiz[i];
            System.out.println("Question " + (i + 1) + ": " + q.question);
            System.out.println("Correct answer: " + q.answer);
        }
    }
}