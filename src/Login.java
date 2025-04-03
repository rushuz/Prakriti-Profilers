import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class Login extends JFrame {
    private HashMap<String, String> userCredentials;

    public Login() {
        initialize();
        initializeUserCredentials();
    }

    private void initialize() {
        setTitle("Login");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setContentPane(panel);
        panel.setLayout(new GridLayout(6,1,8,8));
        ImageIcon image = new ImageIcon("C:\\Users\\archi\\IdeaProjects\\MIniProject\\src\\logo.png");
        setIconImage(image.getImage());
        panel.setBackground(new Color(219,254,184));

        JTextField usernameField = new JTextField();
        panel.add(new JLabel("Username:"));
        panel.add(usernameField);

        JPasswordField passwordField = new JPasswordField();
        panel.add(new JLabel("Password:"));
        panel.add(passwordField);

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleLoginButton(usernameField.getText(), new String(passwordField.getPassword()));
            }
        });
        panel.add(loginButton);
        loginButton.setBackground(new Color(159,196,144));

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleCancelButton();
            }
        });
        panel.add(cancelButton);
        cancelButton.setBackground(new Color(159,196,144));

        setVisible(true);
    }

    private void initializeUserCredentials() {
        userCredentials = new HashMap<>();
        userCredentials.put("Archita", "any1");
        userCredentials.put("Sanjana", "sns1");
        userCredentials.put("Priyal", "ppt1");
        userCredentials.put("Aishwarya", "aaw1");
        userCredentials.put("Shrimay", "ssy1");

    }

    private void handleLoginButton(String username, String password) {
        if (userCredentials.containsKey(username) && userCredentials.get(username).equals(password)) {
            setVisible(false);
            Evaluation doshaAssessmentFrame = new Evaluation();
            doshaAssessmentFrame.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this,
                    "Invalid username or password.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleCancelButton() {
        System.exit(0);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Login();
            }
        });
    }
}
