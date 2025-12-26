package payroll;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

public class ManageAllowanceFrame extends JFrame implements ActionListener {

    private JTextField txtSearch, txtEmpId, txtFirstName, txtDOB, txtBasicSalary, txtDept;
    private JTextField txtOvertime, txtMedical, txtBonus, txtOther, txtTotal, txtOvertimeRate, txtRPH;
    private JButton btnSearch, btnCalculate, btnSave, btnClear;
    private JTable allowanceTable;
    private DefaultTableModel tableModel;

    private HashMap<Integer, Employee> employeeData;

    public ManageAllowanceFrame() {
        // Frame setup
        setTitle("Payroll System | Manage Employee Allowance");
        setSize(900, 750);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        // ===== BLUE NAVBAR HEADER =====
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(0, 102, 204));
        headerPanel.setBounds(0, 0, 900, 60);

        JLabel headerLabel = new JLabel("MANAGE EMPLOYEE ALLOWANCE", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        headerLabel.setForeground(Color.WHITE);
        headerPanel.add(headerLabel, BorderLayout.CENTER);
        add(headerPanel);

        // ===== MAIN PANEL =====
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setLayout(null);
        panel.setBounds(0, 60, 900, 650);
        add(panel);

        // Dummy employee data
        employeeData = new HashMap<>();
        employeeData.put(101, new Employee(101, "John", "1990-05-10", 50000, "IT"));
        employeeData.put(102, new Employee(102, "Maria", "1993-08-22", 45000, "HR"));
        employeeData.put(103, new Employee(103, "Saurabh", "1995-12-15", 55000, "Finance"));

        // ===== SEARCH SECTION =====
        JLabel lblSearch = new JLabel("Search Employee ID:");
        lblSearch.setBounds(30, 30, 150, 25);
        lblSearch.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        panel.add(lblSearch);

        txtSearch = new JTextField();
        txtSearch.setBounds(170, 30, 120, 28);
        panel.add(txtSearch);

        btnSearch = new JButton("🔍");
        btnSearch.setBounds(300, 30, 50, 28);
        btnSearch.setBackground(new Color(0, 102, 204));
        btnSearch.setForeground(Color.WHITE);
        btnSearch.setFocusPainted(false);
        btnSearch.addActionListener(this);
        panel.add(btnSearch);

        // ===== EMPLOYEE INFO SECTION =====
        int y = 80;
        panel.add(createLabel("Employee ID:", 30, y));
        txtEmpId = createTextField(150, y);
        panel.add(txtEmpId);

        y += 40;
        panel.add(createLabel("First Name:", 30, y));
        txtFirstName = createTextField(150, y);
        panel.add(txtFirstName);

        y += 40;
        panel.add(createLabel("Date of Birth:", 30, y));
        txtDOB = createTextField(150, y);
        panel.add(txtDOB);

        y += 40;
        panel.add(createLabel("Basic Salary:", 30, y));
        txtBasicSalary = createTextField(150, y);
        panel.add(txtBasicSalary);

        y += 40;
        panel.add(createLabel("Department:", 30, y));
        txtDept = createTextField(150, y);
        panel.add(txtDept);

        // ===== ALLOWANCE INPUT SECTION =====
        JLabel lblEnterAmt = new JLabel("Please Enter the Amount:");
        lblEnterAmt.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblEnterAmt.setBounds(450, 80, 250, 25);
        panel.add(lblEnterAmt);

        panel.add(createLabel("Overtime:", 450, 120));
        txtOvertime = createEditableTextField(570, 120);
        panel.add(txtOvertime);

        panel.add(createLabel("Medical:", 450, 160));
        txtMedical = createEditableTextField(570, 160);
        panel.add(txtMedical);

        panel.add(createLabel("Bonus:", 450, 200));
        txtBonus = createEditableTextField(570, 200);
        panel.add(txtBonus);

        panel.add(createLabel("Other:", 450, 240));
        txtOther = createEditableTextField(570, 240);
        panel.add(txtOther);

        // ===== NEW RIGHT-SIDE FIELDS =====
        panel.add(createLabel("Total Overtime Rate:", 450, 280));
        txtOvertimeRate = createEditableTextField(570, 280);
        panel.add(txtOvertimeRate);

        panel.add(createLabel("RPH :", 450, 320));
        txtRPH = createEditableTextField(570, 320);
        panel.add(txtRPH);

        JLabel lblTotal = new JLabel("Total Amount:");
        lblTotal.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblTotal.setBounds(450, 370, 150, 25);
        panel.add(lblTotal);

        txtTotal = new JTextField();
        txtTotal.setBounds(570, 370, 180, 28);
        txtTotal.setEditable(false);
        txtTotal.setFont(new Font("Segoe UI", Font.BOLD, 13));
        panel.add(txtTotal);

        // ===== BUTTONS =====
        btnCalculate = createButton("Calculate", new Color(0, 102, 204), 220, 430);
        btnCalculate.addActionListener(this);
        panel.add(btnCalculate);

        btnSave = createButton("Save", new Color(0, 153, 51), 370, 430);
        btnSave.addActionListener(this);
        panel.add(btnSave);

        btnClear = createButton("Clear", new Color(204, 0, 0), 520, 430);
        btnClear.addActionListener(this);
        panel.add(btnClear);

        // ===== TABLE SECTION =====
        String[] columnNames = {"Emp ID", "Allowance Count", "Name", "Basic Salary", "Rate Per Hour"};
        tableModel = new DefaultTableModel(columnNames, 0);
        allowanceTable = new JTable(tableModel);
        allowanceTable.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        allowanceTable.setRowHeight(25);

        JScrollPane scrollPane = new JScrollPane(allowanceTable);
        scrollPane.setBounds(30, 500, 820, 120);
        panel.add(scrollPane);

        setVisible(true);
    }

    private JLabel createLabel(String text, int x, int y) {
        JLabel lbl = new JLabel(text);
        lbl.setBounds(x, y, 150, 25);
        lbl.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        return lbl;
    }

    private JTextField createTextField(int x, int y) {
        JTextField txt = new JTextField();
        txt.setBounds(x, y, 200, 28);
        txt.setEditable(false);
        txt.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        return txt;
    }

    private JTextField createEditableTextField(int x, int y) {
        JTextField txt = new JTextField();
        txt.setBounds(x, y, 180, 28);
        txt.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        return txt;
    }

    private JButton createButton(String text, Color bg, int x, int y) {
        JButton btn = new JButton(text);
        btn.setBounds(x, y, 120, 35);
        btn.setBackground(bg);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
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
                    txtFirstName.setText(emp.name);
                    txtDOB.setText(emp.dob);
                    txtBasicSalary.setText(String.valueOf(emp.salary));
                    txtDept.setText(emp.dept);
                } else {
                    JOptionPane.showMessageDialog(this, "Employee not found!");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid numeric Employee ID.");
            }
        }

        else if (e.getSource() == btnCalculate) {
            try {
                double overtime = Double.parseDouble(txtOvertime.getText());
                double medical = Double.parseDouble(txtMedical.getText());
                double bonus = Double.parseDouble(txtBonus.getText());
                double other = Double.parseDouble(txtOther.getText());
                double overtimeRate = Double.parseDouble(txtOvertimeRate.getText());
                double rph = Double.parseDouble(txtRPH.getText());

                double total = overtime + medical + bonus + other + overtimeRate + rph;
                txtTotal.setText("₹ " + total);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Enter valid numbers for allowance fields.");
            }
        }

        else if (e.getSource() == btnSave) {
            if (txtEmpId.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please search an employee first!");
                return;
            }
            tableModel.addRow(new Object[]{
                    txtEmpId.getText(),
                    "5", // 5 allowance fields (Overtime, Medical, Bonus, Other, RPH)
                    txtFirstName.getText(),
                    txtBasicSalary.getText(),
                    txtRPH.getText()
            });
            JOptionPane.showMessageDialog(this, "Allowance details saved successfully (dummy).");
        }

        else if (e.getSource() == btnClear) {
            txtSearch.setText("");
            txtEmpId.setText("");
            txtFirstName.setText("");
            txtDOB.setText("");
            txtBasicSalary.setText("");
            txtDept.setText("");
            txtOvertime.setText("");
            txtMedical.setText("");
            txtBonus.setText("");
            txtOther.setText("");
            txtOvertimeRate.setText("");
            txtRPH.setText("");
            txtTotal.setText("");
        }
    }

    // Dummy Employee class
    class Employee {
        int id;
        String name, dob, dept;
        double salary;

        Employee(int id, String name, String dob, double salary, String dept) {
            this.id = id;
            this.name = name;
            this.dob = dob;
            this.salary = salary;
            this.dept = dept;
        }
    }

    public static void main(String[] args) {
        new ManageAllowanceFrame();
    }
}
