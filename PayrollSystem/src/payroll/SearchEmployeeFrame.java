package payroll;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;

public class SearchEmployeeFrame extends JFrame implements ActionListener {

    private JTextField empIdField;
    private JButton searchBtn, clearBtn, deleteBtn, updateBtn;
    private JPanel resultPanel;
    private JLabel photoLabel;
    private JTextArea resultArea;

    private Map<String, String[]> dummyEmployees;

    public SearchEmployeeFrame() {
        setTitle("Payroll System | Search & Delete Employee");
        setSize(650, 580);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // ===== HEADER =====
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(0, 102, 204));
        JLabel headerLabel = new JLabel("Search & Delete Employee");
        headerLabel.setForeground(Color.WHITE);
        headerLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        headerPanel.add(headerLabel);
        add(headerPanel, BorderLayout.NORTH);

        // ===== MAIN PANEL =====
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setLayout(null);

        JLabel empIdLabel = new JLabel("Enter Employee ID:");
        empIdLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        empIdLabel.setBounds(60, 50, 300, 30);
        mainPanel.add(empIdLabel);

        empIdField = new JTextField();
        empIdField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        empIdField.setBounds(60, 85, 400, 35);
        empIdField.setBorder(BorderFactory.createLineBorder(new Color(0, 102, 204), 2));
        mainPanel.add(empIdField);

        // ===== BUTTONS =====
        searchBtn = new JButton("Search");
        styleButton(searchBtn, new Color(0, 153, 0));
        searchBtn.setBounds(60, 140, 130, 40);
        searchBtn.addActionListener(this);
        mainPanel.add(searchBtn);

        deleteBtn = new JButton("Delete");
        styleButton(deleteBtn, new Color(204, 0, 0));
        deleteBtn.setBounds(200, 140, 120, 40);
        deleteBtn.addActionListener(this);
        deleteBtn.setEnabled(false);
        mainPanel.add(deleteBtn);

        clearBtn = new JButton("Clear");
        styleButton(clearBtn, new Color(100, 100, 100));
        clearBtn.setBounds(330, 140, 120, 40);
        clearBtn.addActionListener(this);
        mainPanel.add(clearBtn);

        updateBtn = new JButton("Update");
        styleButton(updateBtn, new Color(0, 102, 204));
        updateBtn.setBounds(460, 140, 120, 40);
        updateBtn.addActionListener(this);
        updateBtn.setEnabled(false);
        mainPanel.add(updateBtn);

        // ===== RESULT PANEL =====
        resultPanel = new JPanel();
        resultPanel.setLayout(null);
        resultPanel.setBackground(Color.WHITE);
        resultPanel.setBounds(30, 200, 580, 280);
        resultPanel.setVisible(false);
        resultPanel.setBorder(BorderFactory.createTitledBorder("Employee Details"));

        resultArea = new JTextArea();
        resultArea.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        resultArea.setEditable(false);
        resultArea.setBounds(20, 40, 320, 200);
        resultArea.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        resultPanel.add(resultArea);

        photoLabel = new JLabel("Photo", SwingConstants.CENTER);
        photoLabel.setBounds(360, 40, 180, 200);
        photoLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        resultPanel.add(photoLabel);

        mainPanel.add(resultPanel);
        add(mainPanel, BorderLayout.CENTER);

        // ===== Dummy Data =====
        dummyEmployees = new HashMap<>();
        dummyEmployees.put("101", new String[]{
                "John", "Doe", "10/02/1997", "Male", "john@gmail.com", "9876543210",
                "Pune", "411045", "Flat 3B", "IT", "Developer", "Active", "10/02/2023",
                "55000", "Software Engineer"
        });
        dummyEmployees.put("102", new String[]{
                "Priya", "Sharma", "15/04/1994", "Female", "priya@gmail.com", "9988776655",
                "Mumbai", "400001", "Flat 22A", "HR", "Manager", "Active", "15/04/2022",
                "68000", "HR Manager"
        });

        setVisible(true);
    }

    private void styleButton(JButton btn, Color bg) {
        btn.setFont(new Font("Segoe UI", Font.BOLD, 15));
        btn.setBackground(bg);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String empId = empIdField.getText().trim();

        if (e.getSource() == searchBtn) {
            if (empId.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter Employee ID.", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (dummyEmployees.containsKey(empId)) {
                String[] data = dummyEmployees.get(empId);
                resultArea.setText("Name: " + data[0] + " " + data[1] +
                        "\nDOB: " + data[2] + "\nGender: " + data[3] +
                        "\nEmail: " + data[4] + "\nPhone: " + data[5] +
                        "\nAddress: " + data[6] + ", " + data[7] +
                        "\nDept: " + data[9] + "\nDesignation: " + data[10] +
                        "\nStatus: " + data[11] + "\nSalary: ₹" + data[13]);

                resultPanel.setVisible(true);
                deleteBtn.setEnabled(true);
                updateBtn.setEnabled(true);
            } else {
                JOptionPane.showMessageDialog(this, "No employee found with ID: " + empId,
                        "Not Found", JOptionPane.ERROR_MESSAGE);
                resultPanel.setVisible(false);
                deleteBtn.setEnabled(false);
                updateBtn.setEnabled(false);
            }

        } else if (e.getSource() == deleteBtn) {
            if (!dummyEmployees.containsKey(empId)) {
                JOptionPane.showMessageDialog(this, "No such record to delete!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            int confirm = JOptionPane.showConfirmDialog(this,
                    "Are you sure you want to delete this record?",
                    "Confirm Deletion", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                dummyEmployees.remove(empId);
                JOptionPane.showMessageDialog(this, "Employee record deleted successfully!");
                resultArea.setText("");
                resultPanel.setVisible(false);
                deleteBtn.setEnabled(false);
                updateBtn.setEnabled(false);
            }

        } else if (e.getSource() == clearBtn) {
            empIdField.setText("");
            resultArea.setText("");
            resultPanel.setVisible(false);
            deleteBtn.setEnabled(false);
            updateBtn.setEnabled(false);

        } else if (e.getSource() == updateBtn) {
            if (dummyEmployees.containsKey(empId)) {
                new UpdateEmployeeForm(empId, dummyEmployees);
            }
        }
    }

    public static void main(String[] args) {
        new SearchEmployeeFrame();
    }
}
