package payroll;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

public class DeductionFrame extends JFrame implements ActionListener {

    private JTextField txtSearch, txtEmpId, txtFirstName, txtSurname, txtDOB, txtBasicSalary, txtDept;
    private JTextField txtDeductionReason, txtDeductionAmount, txtAfterDeduction;
    private JTextField txtDesignation, txtStatus, txtJobTitle;
    private JButton btnSearch, btnCalculate, btnUpdate, btnClear;
    private JTable table;
    private DefaultTableModel tableModel;
    private HashMap<Integer, Employee> employeeData;
    private int recordCount = 0;

    public DeductionFrame() {
        // ===== Frame Setup =====
        setTitle("Payroll System | Employee Deduction");
        setSize(850, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        // ===== HEADER =====
        JPanel header = new JPanel();
        header.setBackground(new Color(0, 102, 204));
        header.setBounds(0, 0, 850, 60);
        header.setLayout(new BorderLayout());

        JLabel title = new JLabel("EMPLOYEE DEDUCTION", SwingConstants.CENTER);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));
        header.add(title, BorderLayout.CENTER);
        add(header);

        // ===== Main Panel =====
        JPanel panel = new JPanel(null);
        panel.setBackground(Color.WHITE);
        panel.setBounds(0, 60, 850, 420);
        add(panel);

        // ===== Dummy Data =====
        employeeData = new HashMap<>();
        employeeData.put(101, new Employee(101, "John", "Smith", "1990-05-10", 50000, "IT", "Software Engineer", "Hired", "Full Stack Developer"));
        employeeData.put(102, new Employee(102, "Priya", "Sharma", "1993-08-22", 45000, "HR", "HR Manager", "On Leave", "Recruitment Head"));
        employeeData.put(103, new Employee(103, "Rakesh", "Patil", "1995-12-15", 55000, "Finance", "Accountant", "Notice Period", "Finance Executive"));

        // ===== Search Section =====
        JLabel lblSearch = new JLabel("Search Employee ID:");
        lblSearch.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblSearch.setBounds(30, 20, 150, 25);
        panel.add(lblSearch);

        txtSearch = new JTextField();
        txtSearch.setBounds(180, 20, 120, 28);
        panel.add(txtSearch);

        btnSearch = createButton("Search", new Color(0, 102, 204), 310, 20);
        btnSearch.setBounds(310, 20, 100, 28);
        btnSearch.addActionListener(this);
        panel.add(btnSearch);

        // ===== Employee Details =====
        int y = 70;

        panel.add(createLabel("Employee ID:", 30, y));
        txtEmpId = createTextField(150, y);
        panel.add(txtEmpId);

        panel.add(createLabel("Date of Birth:", 450, y));
        txtDOB = createTextField(580, y);
        panel.add(txtDOB);

        y += 40;
        panel.add(createLabel("First Name:", 30, y));
        txtFirstName = createTextField(150, y);
        panel.add(txtFirstName);

        panel.add(createLabel("Basic Salary:", 450, y));
        txtBasicSalary = createTextField(580, y);
        panel.add(txtBasicSalary);

        y += 40;
        panel.add(createLabel("Surname:", 30, y));
        txtSurname = createTextField(150, y);
        panel.add(txtSurname);

        panel.add(createLabel("Department:", 450, y));
        txtDept = createTextField(580, y);
        panel.add(txtDept);

        // ===== New Fields =====
        y += 40;
        panel.add(createLabel("Designation:", 30, y));
        txtDesignation = new JTextField();
        txtDesignation.setBounds(150, y, 180, 28);
        txtDesignation.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        panel.add(txtDesignation);

        panel.add(createLabel("Status:", 450, y));
        txtStatus = new JTextField();
        txtStatus.setBounds(580, y, 180, 28);
        txtStatus.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        panel.add(txtStatus);

        y += 40;
        panel.add(createLabel("Job Title:", 30, y));
        txtJobTitle = new JTextField();
        txtJobTitle.setBounds(150, y, 180, 28);
        txtJobTitle.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        panel.add(txtJobTitle);

        // ===== Deduction Section =====
        JLabel lblDeduction = new JLabel("Deduction Details");
        lblDeduction.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblDeduction.setBounds(30, 280, 200, 25);
        panel.add(lblDeduction);

        panel.add(createLabel("Deduction Reason:", 30, 310));
        txtDeductionReason = new JTextField();
        txtDeductionReason.setBounds(180, 310, 200, 28);
        panel.add(txtDeductionReason);

        panel.add(createLabel("Deduction Amount:", 420, 310));
        txtDeductionAmount = new JTextField();
        txtDeductionAmount.setBounds(580, 310, 150, 28);
        panel.add(txtDeductionAmount);

        // ===== Salary After Deduction =====
        panel.add(createLabel("Salary After Deduction:", 30, 350));
        txtAfterDeduction = new JTextField();
        txtAfterDeduction.setBounds(220, 350, 180, 28);
        txtAfterDeduction.setEditable(false);
        txtAfterDeduction.setFont(new Font("Segoe UI", Font.BOLD, 13));
        panel.add(txtAfterDeduction);

        // ===== Buttons =====
        btnCalculate = createButton("Calculate", new Color(0, 102, 204), 180, 390);
        btnCalculate.addActionListener(this);
        panel.add(btnCalculate);

        btnUpdate = createButton("Update", new Color(0, 153, 51), 330, 390);
        btnUpdate.addActionListener(this);
        panel.add(btnUpdate);

        btnClear = createButton("Clear", new Color(204, 0, 0), 480, 390);
        btnClear.addActionListener(this);
        panel.add(btnClear);

        // ===== Table Panel =====
        String[] columns = {"Emp ID", "Count", "Name", "Basic Salary"};
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        table.setRowHeight(25);
        table.setGridColor(Color.LIGHT_GRAY);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(30, 490, 780, 150);
        add(scrollPane);

        setVisible(true);
    }

    private JLabel createLabel(String text, int x, int y) {
        JLabel lbl = new JLabel(text);
        lbl.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lbl.setBounds(x, y, 150, 25);
        return lbl;
    }

    private JTextField createTextField(int x, int y) {
        JTextField txt = new JTextField();
        txt.setBounds(x, y, 180, 28);
        txt.setEditable(false);
        txt.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        return txt;
    }

    private JButton createButton(String text, Color color, int x, int y) {
        JButton btn = new JButton(text);
        btn.setBounds(x, y, 120, 35);
        btn.setBackground(color);
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setBorder(BorderFactory.createEmptyBorder());

        // Hover effect
        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                btn.setBackground(color.darker());
            }

            public void mouseExited(MouseEvent e) {
                btn.setBackground(color);
            }
        });
        return btn;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnSearch) {
            try {
                int id = Integer.parseInt(txtSearch.getText());
                if (employeeData.containsKey(id)) {
                    Employee emp = employeeData.get(id);
                    txtEmpId.setText(String.valueOf(emp.id));
                    txtFirstName.setText(emp.firstName);
                    txtSurname.setText(emp.lastName);
                    txtDOB.setText(emp.dob);
                    txtBasicSalary.setText(String.valueOf(emp.salary));
                    txtDept.setText(emp.department);

                    // Auto-fill new fields
                    txtDesignation.setText(emp.designation);
                    txtStatus.setText(emp.status);
                    txtJobTitle.setText(emp.jobTitle);

                } else {
                    JOptionPane.showMessageDialog(this, "Employee not found!");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid Employee ID!");
            }
        }

        else if (e.getSource() == btnCalculate) {
            try {
                double basic = Double.parseDouble(txtBasicSalary.getText());
                double deduction = Double.parseDouble(txtDeductionAmount.getText());
                double newSalary = basic - deduction;
                txtAfterDeduction.setText("₹ " + String.format("%.2f", newSalary));
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid input! Please check deduction or salary fields.");
            }
        }

        else if (e.getSource() == btnUpdate) {
            recordCount++;
            String empId = txtEmpId.getText();
            String name = txtFirstName.getText() + " " + txtSurname.getText();
            String basicSalary = txtBasicSalary.getText();

            if (!empId.isEmpty()) {
                tableModel.addRow(new Object[]{empId, recordCount, name, basicSalary});
                JOptionPane.showMessageDialog(this, "Employee deduction updated successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "Please search and select an employee first!");
            }
        }

        else if (e.getSource() == btnClear) {
            txtSearch.setText("");
            txtEmpId.setText("");
            txtFirstName.setText("");
            txtSurname.setText("");
            txtDOB.setText("");
            txtBasicSalary.setText("");
            txtDept.setText("");
            txtDeductionReason.setText("");
            txtDeductionAmount.setText("");
            txtAfterDeduction.setText("");
            txtDesignation.setText("");
            txtStatus.setText("");
            txtJobTitle.setText("");
        }
    }

    // Employee class with new fields
    class Employee {
        int id;
        String firstName, lastName, dob, department;
        double salary;
        String designation, status, jobTitle;

        Employee(int id, String firstName, String lastName, String dob, double salary,
                 String department, String designation, String status, String jobTitle) {
            this.id = id;
            this.firstName = firstName;
            this.lastName = lastName;
            this.dob = dob;
            this.salary = salary;
            this.department = department;
            this.designation = designation;
            this.status = status;
            this.jobTitle = jobTitle;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(DeductionFrame::new);
    }
}
