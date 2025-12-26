package payroll;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

public class UpdateSalaryFrame extends JFrame implements ActionListener {

    private JTextField txtSearch, txtEmpId, txtFirstName, txtSurname, txtDOB, txtBasicSalary, txtDept;
    private JTextField txtPercentage, txtAmount, txtTotalSalary, txtUpdatedSalary;
    private JButton btnSearch, btnCalculate, btnUpdate, btnClear;
    private JRadioButton rbPercent, rbAmount;
    private ButtonGroup updateGroup;
    private HashMap<Integer, Employee> employeeData;

    public UpdateSalaryFrame() {
        // ===== Frame Setup =====
        setTitle("Payroll System | Update Employee Salary");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        // ===== HEADER =====
        JPanel header = new JPanel();
        header.setBackground(new Color(0, 102, 204));
        header.setBounds(0, 0, 900, 60);
        header.setLayout(new BorderLayout());

        JLabel title = new JLabel("UPDATE EMPLOYEE SALARY", SwingConstants.CENTER);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        header.add(title, BorderLayout.CENTER);
        add(header);

        // ===== Main Panel =====
        JPanel panel = new JPanel(null);
        panel.setBackground(Color.WHITE);
        panel.setBounds(0, 60, 900, 540);
        add(panel);

        // ===== Dummy Data =====
        employeeData = new HashMap<>();
        employeeData.put(101, new Employee(101, "John", "Smith", "1990-05-10", 50000, "IT"));
        employeeData.put(102, new Employee(102, "Priya", "Sharma", "1993-08-22", 45000, "HR"));
        employeeData.put(103, new Employee(103, "Rakesh", "Patil", "1995-12-15", 55000, "Finance"));

        // ===== Search Section =====
        JLabel lblSearch = new JLabel("Search Employee ID:");
        lblSearch.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        lblSearch.setBounds(100, 20, 160, 28);
        panel.add(lblSearch);

        txtSearch = new JTextField();
        txtSearch.setBounds(260, 20, 160, 32);
        txtSearch.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        panel.add(txtSearch);

        btnSearch = new JButton("🔍 Search");
        btnSearch.setBounds(430, 20, 120, 32);
        btnSearch.setBackground(new Color(0, 102, 204));
        btnSearch.setForeground(Color.WHITE);
        btnSearch.setFocusPainted(false);
        btnSearch.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnSearch.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnSearch.addActionListener(this);
        panel.add(btnSearch);

        // ===== Employee Details Section =====
        int y = 80;

        panel.add(createLabel("Employee ID:", 100, y));
        txtEmpId = createTextField(250, y);
        panel.add(txtEmpId);

        panel.add(createLabel("Date of Birth:", 500, y));
        txtDOB = createTextField(630, y);
        panel.add(txtDOB);

        y += 40;
        panel.add(createLabel("First Name:", 100, y));
        txtFirstName = createTextField(250, y);
        panel.add(txtFirstName);

        panel.add(createLabel("Basic Salary:", 500, y));
        txtBasicSalary = createTextField(630, y);
        panel.add(txtBasicSalary);

        y += 40;
        panel.add(createLabel("Surname:", 100, y));
        txtSurname = createTextField(250, y);
        panel.add(txtSurname);

        panel.add(createLabel("Department:", 500, y));
        txtDept = createTextField(630, y);
        panel.add(txtDept);

        // ===== Update Salary Section =====
        JLabel lblUpdateBy = new JLabel("Update Salary by:");
        lblUpdateBy.setFont(new Font("Segoe UI", Font.BOLD, 15));
        lblUpdateBy.setBounds(100, 210, 200, 25);
        panel.add(lblUpdateBy);

        rbPercent = new JRadioButton("Percentage (%)");
        rbAmount = new JRadioButton("Amount");
        rbPercent.setBackground(Color.WHITE);
        rbAmount.setBackground(Color.WHITE);
        rbPercent.setBounds(260, 210, 150, 25);
        rbAmount.setBounds(430, 210, 100, 25);
        rbPercent.addActionListener(this);
        rbAmount.addActionListener(this);
        updateGroup = new ButtonGroup();
        updateGroup.add(rbPercent);
        updateGroup.add(rbAmount);
        panel.add(rbPercent);
        panel.add(rbAmount);

        // ===== Input Fields =====
        panel.add(createLabel("Percentage:", 100, 260));
        txtPercentage = new JTextField();
        txtPercentage.setBounds(250, 260, 100, 30);
        txtPercentage.setEditable(false);
        panel.add(txtPercentage);

        panel.add(createLabel("Amount:", 400, 260));
        txtAmount = new JTextField();
        txtAmount.setBounds(480, 260, 100, 30);
        txtAmount.setEditable(false);
        panel.add(txtAmount);

        // ===== Salary Summary =====
        panel.add(createLabel("Total Basic Salary:", 100, 310));
        txtTotalSalary = new JTextField();
        txtTotalSalary.setBounds(250, 310, 150, 30);
        txtTotalSalary.setEditable(false);
        txtTotalSalary.setFont(new Font("Segoe UI", Font.BOLD, 13));
        panel.add(txtTotalSalary);

        panel.add(createLabel("Salary After Update:", 430, 310));
        txtUpdatedSalary = new JTextField();
        txtUpdatedSalary.setBounds(580, 310, 150, 30);
        txtUpdatedSalary.setEditable(false);
        txtUpdatedSalary.setFont(new Font("Segoe UI", Font.BOLD, 13));
        panel.add(txtUpdatedSalary);

        // ===== Buttons =====
        btnCalculate = createButton("Calculate", new Color(0, 102, 204), 220, 380);
        btnCalculate.addActionListener(this);
        panel.add(btnCalculate);

        btnUpdate = createButton("Update", new Color(0, 153, 51), 370, 380);
        btnUpdate.addActionListener(this);
        panel.add(btnUpdate);

        btnClear = createButton("Clear", new Color(204, 0, 0), 520, 380);
        btnClear.addActionListener(this);
        panel.add(btnClear);

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
        txt.setBounds(x, y, 200, 30);
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
                    txtTotalSalary.setText("₹ " + emp.salary);
                } else {
                    JOptionPane.showMessageDialog(this, "Employee not found!");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid Employee ID!");
            }
        }

        else if (e.getSource() == rbPercent) {
            txtPercentage.setEditable(true);
            txtAmount.setEditable(false);
            txtAmount.setText("");
        }

        else if (e.getSource() == rbAmount) {
            txtAmount.setEditable(true);
            txtPercentage.setEditable(false);
            txtPercentage.setText("");
        }

        else if (e.getSource() == btnCalculate) {
            try {
                double basic = Double.parseDouble(txtBasicSalary.getText());
                double newSalary = basic;

                if (rbPercent.isSelected()) {
                    double percent = Double.parseDouble(txtPercentage.getText());
                    newSalary = basic + (basic * percent / 100);
                } else if (rbAmount.isSelected()) {
                    double amount = Double.parseDouble(txtAmount.getText());
                    newSalary = basic + amount;
                } else {
                    JOptionPane.showMessageDialog(this, "Please select Percentage or Amount first!");
                    return;
                }

                txtUpdatedSalary.setText("₹ " + String.format("%.2f", newSalary));

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid input! Please check salary or update fields.");
            }
        }

        else if (e.getSource() == btnUpdate) {
            JOptionPane.showMessageDialog(this, "Employee salary updated successfully (dummy).");
        }

        else if (e.getSource() == btnClear) {
            txtSearch.setText("");
            txtEmpId.setText("");
            txtFirstName.setText("");
            txtSurname.setText("");
            txtDOB.setText("");
            txtBasicSalary.setText("");
            txtDept.setText("");
            txtPercentage.setText("");
            txtAmount.setText("");
            txtTotalSalary.setText("");
            txtUpdatedSalary.setText("");
            updateGroup.clearSelection();
            txtPercentage.setEditable(false);
            txtAmount.setEditable(false);
        }
    }

    // Dummy Employee class
    class Employee {
        int id;
        String firstName, lastName, dob, department;
        double salary;

        Employee(int id, String firstName, String lastName, String dob, double salary, String department) {
            this.id = id;
            this.firstName = firstName;
            this.lastName = lastName;
            this.dob = dob;
            this.salary = salary;
            this.department = department;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(UpdateSalaryFrame::new);
    }
}
