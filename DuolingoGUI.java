import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import javax.swing.*;

public class DuolingoGUI {
    private static final HashMap<String, String> vocabulary = new HashMap<>();
    private static int score = 0;
    private static String currentQuestion;
    private static JLabel questionLabel;
    private static JTextField answerField;
    private static JLabel feedbackLabel;
    private static JLabel scoreLabel;

    public static void main(String[] args) {
        // Initialize vocabulary
        vocabulary.put("hello", "hola");
        vocabulary.put("goodbye", "adiós");
        vocabulary.put("please", "por favor");
        vocabulary.put("thank you", "gracias");
        vocabulary.put("yes", "sí");
        vocabulary.put("no", "no");

        // Setup the GUI
        JFrame frame = new JFrame("Simple Duolingo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        // Top Panel: Question Display
        JPanel topPanel = new JPanel();
        questionLabel = new JLabel("Press 'Next' to start!");
        topPanel.add(questionLabel);

        // Middle Panel: Answer Input
        JPanel middlePanel = new JPanel();
        answerField = new JTextField(15);
        JButton submitButton = new JButton("Submit");
        feedbackLabel = new JLabel(" ");
        middlePanel.add(new JLabel("Your Answer: "));
        middlePanel.add(answerField);
        middlePanel.add(submitButton);

        // Bottom Panel: Feedback and Controls
        JPanel bottomPanel = new JPanel();
        JButton nextButton = new JButton("Next");
        scoreLabel = new JLabel("Score: 0");
        bottomPanel.add(nextButton);
        bottomPanel.add(scoreLabel);

        // Add panels to frame
        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(middlePanel, BorderLayout.CENTER);
        frame.add(feedbackLabel, BorderLayout.SOUTH);
        frame.add(bottomPanel, BorderLayout.PAGE_END);

        // Event Handlers
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userAnswer = answerField.getText().trim().toLowerCase();
                if (userAnswer.equals(vocabulary.get(currentQuestion))) {
                    feedbackLabel.setText("Correct! 🎉");
                    score++;
                } else {
                    feedbackLabel.setText("Wrong! Correct answer: " + vocabulary.get(currentQuestion));
                       }
                scoreLabel.setText("Score: " + score);
                answerField.setText("");
            }
        });

        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentQuestion = getRandomWord();
                questionLabel.setText("What is the Spanish word for: " + currentQuestion + "?");
                feedbackLabel.setText(" ");
                answerField.setText("");
            }
        });

        // Show frame
        frame.setVisible(true);
    }

    private static String getRandomWord() {
        Object[] keys = vocabulary.keySet().toArray();
        int randomIndex = (int) (Math.random() * keys.length);
        return (String) keys[randomIndex];
    }
}