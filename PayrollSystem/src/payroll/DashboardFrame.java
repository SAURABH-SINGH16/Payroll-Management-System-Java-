package payroll;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class DashboardFrame extends JFrame {

    private String username;

    public DashboardFrame(String user) {
        this.username = user;

        setTitle("Payroll System | Admin Dashboard");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // ===== MAIN PANEL =====
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);
        setContentPane(mainPanel);

        // ===== HEADER =====
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(0, 102, 204));
        header.setPreferredSize(new Dimension(0, 70));

        JLabel title = new JLabel("PAYROLL SYSTEM - ADMIN");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Segoe UI", Font.BOLD, 26));
        title.setBorder(new EmptyBorder(15, 40, 10, 0));

        JLabel logout = new JLabel("LOG OUT", SwingConstants.CENTER);
        logout.setForeground(Color.WHITE);
        logout.setFont(new Font("Segoe UI", Font.BOLD, 14));
        logout.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        logout.setBorder(new EmptyBorder(0, 30, 0, 30));
        logout.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                dispose();
                new LoginFrame();
            }

            public void mouseEntered(MouseEvent e) {
                logout.setForeground(new Color(255, 204, 204));
            }

            public void mouseExited(MouseEvent e) {
                logout.setForeground(Color.WHITE);
            }
        });

        header.add(title, BorderLayout.WEST);
        header.add(logout, BorderLayout.EAST);
        mainPanel.add(header, BorderLayout.NORTH);

        // ===== CENTER =====
        JPanel center = new JPanel();
        center.setBackground(Color.WHITE);
        center.setLayout(new GridLayout(2, 3, 25, 25));
        center.setBorder(new EmptyBorder(50, 80, 50, 80));

        // 1️⃣ Add Employee
        center.add(createButton("ADD NEW EMPLOYEE",
                "Click this button to add new employee",
                new Color(51, 153, 255),
                () -> new AddEmployeeFrame()));

        // 2️⃣ Search Employee
        center.add(createButton("SEARCH EMPLOYEE",
                "Click this button to search and delete employee",
                new Color(0, 153, 0),
                () -> new SearchEmployeeFrame()));

        // 3️⃣ Manage Employee Allowance
        center.add(createButton("MANAGE EMPLOYEE ALLOWANCE",
                "Click this button to manage employee allowance",
                new Color(255, 128, 0),
                () -> new ManageAllowanceFrame()));

        // 4️⃣ Update Employee Salary
        center.add(createButton("UPDATE EMPLOYEE SALARY",
                "Click this button to update the employee salary",
                new Color(51, 102, 153),
                () -> new UpdateSalaryFrame()));

        // 5️⃣ Employee Deduction ✅ (NEW FEATURE)
        center.add(createButton("EMPLOYEE DEDUCTION",
                "Click this button to manage employee deductions",
                new Color(255, 102, 102),
                () -> new DeductionFrame()));

        // 6️⃣ Print Employee Payment Receipt
        center.add(createButton("PRINT EMPLOYEE PAYMENT RECEIPT",
                "Click this button to print employee receipt",
                new Color(204, 0, 255),
                () -> showMessage("Print Employee Payment Receipt")));

        mainPanel.add(center, BorderLayout.CENTER);

        // ===== FOOTER =====
        JPanel footer = new JPanel(new BorderLayout());
        footer.setBackground(new Color(0, 102, 204));
        footer.setPreferredSize(new Dimension(0, 30));

        JLabel leftFooter = new JLabel("Logged in as: " + username);
        leftFooter.setForeground(Color.WHITE);
        leftFooter.setFont(new Font("Segoe UI", Font.PLAIN, 12));

        JLabel rightFooter = new JLabel("📅 " + java.time.LocalTime.now().withNano(0).toString() + "   ");
        rightFooter.setForeground(Color.WHITE);
        rightFooter.setFont(new Font("Segoe UI", Font.PLAIN, 12));

        footer.add(leftFooter, BorderLayout.WEST);
        footer.add(rightFooter, BorderLayout.EAST);
        mainPanel.add(footer, BorderLayout.SOUTH);

        setVisible(true);
    }

    // ===== Reusable button panel =====
    private JPanel createButton(String title, String subtitle, Color color, Runnable onClick) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(color);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        JLabel titleLabel = new JLabel(title, SwingConstants.CENTER);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));

        JLabel subLabel = new JLabel(subtitle, SwingConstants.CENTER);
        subLabel.setForeground(Color.WHITE);
        subLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));

        panel.add(titleLabel, BorderLayout.CENTER);
        panel.add(subLabel, BorderLayout.SOUTH);

        // Hover + click effect
        panel.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                panel.setBackground(color.darker());
            }

            public void mouseExited(MouseEvent e) {
                panel.setBackground(color);
            }

            public void mouseClicked(MouseEvent e) {
                onClick.run();
            }
        });

        return panel;
    }

    // Placeholder for not-yet-implemented features
    private void showMessage(String msg) {
        JOptionPane.showMessageDialog(this, msg + " section coming soon!");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new DashboardFrame("Admin"));
    }
}
