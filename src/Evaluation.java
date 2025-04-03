import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class Evaluation extends JFrame {
    private JPanel panel;
    private JLabel questionLabel;
    private JRadioButton[] optionButtons;
    private int currentQuestion = 0;
    private Map<Integer, Integer[]> doshaCountsMap = new HashMap<>();

    private static final String[] QUESTIONS = {
            "How is your body build?",
            "What is your hair type?",
            "What is the color of your hair?",
            "What best describes your skin?",
            "What best describes your complexion?",
            "What is your body weight?",
            "What is the size and color of your teeth?",
            "What best describes your memory?",
            "How are your sleep patterns?",
            "What is your reaction to adverse conditions?",
            "What best describes your mood?",
            "What is your body temperature?",
            "What among these describes your communication skills?",
            "What best describes your body energy?",
            "What best describes your nature?",
            "What is the quality of your voice?",
            "What is the health of your joints?",
            "What best describes your hunger patterns?",
            "What best describes your mental activity?",

    };

    private static final String[][] OPTIONS = {
            {"Thin and lean", "Medium", "Well built"},
            {"Dry with split ends", "Normal, thin, more hair fall", "Greasy heavy"},
            {"Pale Brown", "Grey", "Black"},
            {"Dry, Rough", "Soft, more sweating, acne", "Moist, greasy"},
            {"Dark, blackish", "Pinkish, shiny", "Glowing, white"},
            {"Low, difficult to put on weight", "Medium, can easily lose or gain weight", "Overweight, difficult to lose weight"},
            {"Very big or small, irregular", "Medium size, yellowish", "Large, shining white"},
            {"Short term bad", "Good memory", "Long term best"},
            {"Interrupted, less", "Moderate", "Sleepy, lazy"},
            {"Anxiety, Worry", "Anger, Aggression", "Calm, sometimes depressive"},
            {"Changes quickly and have frequent mood swings", "Stable, constant", "Changes according to the situation"},
            {"Less than normal, cold hands and feet", "More than normal, face and forehead are hot", "Normal"},
            {"Fast, irrelevant talk, speech unclear", "Good speaker with genuine argumentative skills", "Authoritative, firm"},
            {"Less in the evening, fatigues after less work", "Moderate, get tired after excessive work", "Excellent energy throughout the day with no fatigue"},
            {"Fearful, Jealous", "Good number of friends", "Loves to socialize, longer relationships"},
            {"Rough with broken words", "Fast, commanding", "Soft and deep"},
            {"Weak, noise on movement", "Healthy with optimal strength", "Heavy weight bearing"},
            {"Irregular", "Sudden hunger pangs", "Can skip any meal easily"},
            {"Quick, restless", "Smart, intellect attractive", "Calm, stable"},
    };

    private static final String[] DOSHA_NAMES = {"Pitta", "Kapha", "Vata"};

    public Evaluation() {
        initialize();
    }

    private void initialize() {
        setTitle("Ayurveda Dosha Assessment");
        setSize(450, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setContentPane(panel);
        panel.setLayout(new GridLayout(6, 1));
        panel.setBackground(new Color(219,254,184));


        ImageIcon image = new ImageIcon("C:\\Users\\archi\\IdeaProjects\\MIniProject\\src\\logo.png");
        setIconImage(image.getImage());


        questionLabel = new JLabel();
        panel.add(questionLabel);

        optionButtons = new JRadioButton[3];
        for (int i = 0; i < optionButtons.length; i++) {
            optionButtons[i] = new JRadioButton();
            optionButtons[i].setBackground(new Color(219,254,184));
            panel.add(optionButtons[i]);
            optionButtons[i].setActionCommand(String.valueOf(i + 1));
            optionButtons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    handleOptionButtonClick(e);
                }
            });
        }

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        panel.add(buttonPanel);
        buttonPanel.setBackground(new Color(219,254,184));


        JButton nextButton = new JButton("Next");
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleNextButton();
            }
        });
        nextButton.setBackground(new Color(159,196,144));
        buttonPanel.add(nextButton);

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleSubmitButton();
            }
        });
        submitButton.setBackground(new Color(159,196,144));
        buttonPanel.add(submitButton);

        setQuestion(0);
    }

    private void handleOptionButtonClick(ActionEvent e) {
        int userChoice = Integer.parseInt(e.getActionCommand());
        doshaCountsMap.put(currentQuestion, new Integer[]{userChoice, 0, 0});
    }

    private void handleNextButton() {
        if (currentQuestion < QUESTIONS.length - 1) {
            currentQuestion++;
            setQuestion(currentQuestion);
        } else {
            handleSubmitButton();
        }
    }

    private void handleSubmitButton() {
        int pittaDosha = 0, kaphaDosha = 0, vataDosha = 0;

        for (Integer[] doshaCount : doshaCountsMap.values()) {
            switch (doshaCount[0]) {
                case 1:
                    pittaDosha++;
                    break;
                case 2:
                    kaphaDosha++;
                    break;
                case 3:
                    vataDosha++;
                    break;
            }
        }

        // Display the results to the user
        JOptionPane.showMessageDialog(this,
                "Your dominant dosha type is " + getDominantDosha(pittaDosha, kaphaDosha, vataDosha) + ".",
                "Results",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private String getDominantDosha(int pittaDosha, int kaphaDosha, int vataDosha) {
        int maxCount = Math.max(pittaDosha, Math.max(kaphaDosha, vataDosha));

        if (maxCount == pittaDosha) {
            return DOSHA_NAMES[0];
        } else if (maxCount == kaphaDosha) {
            return DOSHA_NAMES[1];
        } else {
            return DOSHA_NAMES[2];
        }
    }

    private void setQuestion(int questionIndex) {
        questionLabel.setText(QUESTIONS[questionIndex]);

        for (int i = 0; i < optionButtons.length; i++) {
            optionButtons[i].setText(OPTIONS[questionIndex][i]);
            optionButtons[i].setSelected(false);
        }

        doshaCountsMap.put(questionIndex, new Integer[]{0, 0, 0});
    }
}
