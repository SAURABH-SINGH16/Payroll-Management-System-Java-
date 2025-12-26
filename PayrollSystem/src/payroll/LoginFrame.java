package payroll;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class LoginFrame extends JFrame implements ActionListener {

    private JTextField userField;
    private JPasswordField passField;
    private RoundedButton loginBtn, clearBtn;

    public LoginFrame() {

        setTitle("Payroll System | Admin Login");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        JLabel title = new JLabel("ADMIN LOGIN", SwingConstants.CENTER);
        title.setBounds(0, 20, 400, 40);
        title.setFont(new Font("Arial", Font.BOLD, 26));
        title.setForeground(new Color(0, 90, 200));
        add(title);

        JLabel userLbl = new JLabel("Username:");
        userLbl.setBounds(50, 80, 120, 25);
        add(userLbl);

        userField = new JTextField();
        userField.setBounds(150, 80, 180, 25);
        add(userField);

        JLabel passLbl = new JLabel("Password:");
        passLbl.setBounds(50, 120, 120, 25);
        add(passLbl);

        passField = new JPasswordField();
        passField.setBounds(150, 120, 180, 25);
        add(passField);

        // ✅ Rounded Buttons
        loginBtn = new RoundedButton("Login");
        loginBtn.setBounds(150, 170, 80, 35);
        loginBtn.setBackground(new Color(0, 120, 255));
        loginBtn.addActionListener(this);
        add(loginBtn);

        clearBtn = new RoundedButton("Clear");
        clearBtn.setBounds(250, 170, 80, 35);
        clearBtn.setBackground(Color.GRAY);
        clearBtn.addActionListener(this);
        add(clearBtn);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == loginBtn) {
            String user = userField.getText();
            String pass = new String(passField.getPassword());

            if (user.equals("admin") && pass.equals("admin123")) {

                // ✅ Open Dashboard & pass username
                DashboardFrame dashboard = new DashboardFrame(user);
                dashboard.setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this,
                        "Invalid Username or Password!",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }

        if (e.getSource() == clearBtn) {
            userField.setText("");
            passField.setText("");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginFrame());
    }
}


// ✅ Custom Rounded Button Class
class RoundedButton extends JButton {

    public RoundedButton(String text) {
        super(text);
        setFocusPainted(false);
        setForeground(Color.WHITE);
        setBorderPainted(false);
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // Hover Effect
        addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                setBackground(getBackground().darker());
            }

            public void mouseExited(MouseEvent evt) {
                setBackground(getBackground().brighter());
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(getBackground());
        g.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
        super.paintComponent(g);
    }
}
